package com.quanxiaoha.weblog.admin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.quanxiaoha.weblog.admin.convert.ArticleDetailConvert;
import com.quanxiaoha.weblog.admin.model.vo.article.*;
import com.quanxiaoha.weblog.admin.service.IAdminArticleService;
import com.quanxiaoha.weblog.common.domain.dos.*;
import com.quanxiaoha.weblog.common.domain.mapper.*;
import com.quanxiaoha.weblog.common.enums.ResponseCodeEnum;
import com.quanxiaoha.weblog.common.exception.BizException;
import com.quanxiaoha.weblog.common.utils.PageResponse;
import com.quanxiaoha.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @classname: AdminArticleServiceImpl
 * @Description: TODO
 * @CreateTime: 2025-07-24 14:48
 * @Author: pht
 */
@Service
@Slf4j
public class AdminArticleServiceImpl implements IAdminArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleContentMapper articleContentMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleCategoryRelMapper articleCategoryRelMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;

    /**
     * 发布文章
     *
     * @param publishArticleReqVO
     * @return
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response publishArticle(PublishArticleReqVO publishArticleReqVO) {
        ArticleDO articleDO = ArticleDO.builder()
                .title(publishArticleReqVO.getTitle())
                .cover(publishArticleReqVO.getCover())
                .summary(publishArticleReqVO.getSummary())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        articleMapper.insert(articleDO);

        ArticleContentDO articleContentDO = ArticleContentDO.builder()
                .articleId(articleDO.getId())
                .content(publishArticleReqVO.getContent())
                .build();
        articleContentMapper.insert(articleContentDO);

        //验证分类是否存在
        CategoryDO categoryDO = categoryMapper.selectById(publishArticleReqVO.getCategoryId());

        if (Objects.isNull(categoryDO)) {
            log.warn("==> 分类不存在，categoryId :{}", publishArticleReqVO.getCategoryId());
            throw new BizException(ResponseCodeEnum.CATEGORY_NOT_EXISTED);
        }
        ArticleCategoryRelDO articleCategoryRelDO = ArticleCategoryRelDO.builder()
                .articleId(articleDO.getId())
                .categoryId(publishArticleReqVO.getCategoryId())
                .build();

        articleCategoryRelMapper.insert(articleCategoryRelDO);

        Long articleId = articleDO.getId();

        List<String> publishTags = publishArticleReqVO.getTags();
        insertTags(articleId, publishTags);


        return Response.success();
    }
    /**
     * 删除文章
     *
     * @param deleteArticleReqVO
     * @return
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response deleteArticle(DeleteArticleReqVO deleteArticleReqVO) {

        Long articleId = deleteArticleReqVO.getId();
        //删除内容
        articleContentMapper.deleteByArticleId(articleId);
        //删除标签关联
        articleTagRelMapper.deleteByArticleId(articleId);
        //删除分类关联
        articleCategoryRelMapper.deleteByArticleId(articleId);

        return Response.success();
    }
    /**
     * 查询文章列表
     *
     * @param findArticlePageListReqVO
     * @return
     */

    @Override
    public PageResponse findArticlePageList(FindArticlePageListReqVO findArticlePageListReqVO) {
        // 获取当前页、以及每页需要展示的数据数量

        Long current = findArticlePageListReqVO.getCurrent();
        Long size = findArticlePageListReqVO.getSize();
        // 分页对象(查询第几页、每页多少数据)

        Page<ArticleDO>page= new Page<>(current, size);

        // 构建查询条件

        LambdaQueryWrapper<ArticleDO> wrapper = new LambdaQueryWrapper<>();

        String title = findArticlePageListReqVO.getTitle();
        LocalDate startDate = findArticlePageListReqVO.getStartDate();
        LocalDate endDate = findArticlePageListReqVO.getEndDate();
        wrapper
                .like(StringUtils.isNotBlank(title), ArticleDO::getTitle, title.trim())
                .ge(Objects.nonNull(startDate), ArticleDO::getCreateTime, startDate)
                .le(Objects.nonNull(endDate), ArticleDO::getCreateTime, endDate)
                .orderByDesc(ArticleDO::getCreateTime);
        // 执行分页查询


        Page<ArticleDO> articleDOPage = articleMapper.selectPage(page, wrapper);
        List<ArticleDO> articleDOS = articleDOPage.getRecords();

        // DO 转 VO
        List<FindArticlePageListRspVO>vos = null;
        if(!CollectionUtils.isEmpty(articleDOS)){
            vos = articleDOS.stream().map(p -> FindArticlePageListRspVO.builder()
                    .id(p.getId())
                    .title(p.getTitle())
                    .cover(p.getCover())
                    .createTime(p.getCreateTime())
                    .build()).collect(Collectors.toList());
        }

        return PageResponse.success(articleDOPage,vos);
    }

    @Override
    public Response findArticleDetail(FindArticleDetailReqVO findArticleDetailReqVO) {

        Long articleId = findArticleDetailReqVO.getId();
        ArticleDO articleDO = articleMapper.selectById(articleId);
        if(Objects.isNull(articleDO)){
            log.warn("==> 查询的文章不存在，articleId: {}", articleId);
            throw new BizException(ResponseCodeEnum.ARTICLE_NOT_FOUND);
        }

        ArticleContentDO articleContentDO = articleContentMapper.selectByArticleId(articleId);

        ArticleCategoryRelDO articleCategoryRelDO = articleCategoryRelMapper.selectByArticleId(articleId);

        List<ArticleTagRelDO> articleTagRelDOS = articleTagRelMapper.selectByArticleId(articleId);
        List<Long>tagIds=articleTagRelDOS.stream().map(ArticleTagRelDO::getTagId).collect(Collectors.toList());

        //do转vo
        FindArticleDetailRspVO findArticleDetailRspVO = ArticleDetailConvert.INSTANCE.convertDO2VO(articleDO);
        findArticleDetailRspVO.setTagIds(tagIds);
        findArticleDetailRspVO.setCategoryId(articleCategoryRelDO.getCategoryId());
        findArticleDetailRspVO.setContent(articleContentDO.getContent());
        return Response.success(findArticleDetailRspVO);
    }

    @Override
    public Response updateArticle(UpdateArticleReqVO updateArticleReqVO) {
        Long articleId = updateArticleReqVO.getId();
        ArticleDO articleDO= ArticleDO.builder()
                .id(articleId)
                .title(updateArticleReqVO.getTitle())
                .cover(updateArticleReqVO.getCover())
                .summary(updateArticleReqVO.getSummary())
                .updateTime(LocalDateTime.now())
                .build();
        int count = articleMapper.updateById(articleDO);
        if (count == 0) {
            log.warn("==>文章不存在，articleId：{}",articleId);
            throw new BizException(ResponseCodeEnum.ARTICLE_NOT_FOUND);
        }


        ArticleContentDO articleContentDO = ArticleContentDO.builder()
                .articleId(articleId)
                .content(updateArticleReqVO.getContent())
                .build();
        articleContentMapper.updateByArticleId(articleContentDO);

        Long categoryId = updateArticleReqVO.getCategoryId();
        CategoryDO categoryDO = categoryMapper.selectById(categoryId);
        if (Objects.isNull(categoryDO)) {
            log.warn("==>此分类不存在，categoryId:{}", categoryId);
            throw new BizException(ResponseCodeEnum.CATEGORY_NOT_EXISTED);
        }

        // 先删除该文章关联的分类记录，再插入新的关联关系
        articleCategoryRelMapper.deleteByArticleId(articleId);
        ArticleCategoryRelDO articleCategoryRelDO = ArticleCategoryRelDO.builder()
                .articleId(articleId)
                .categoryId(categoryId)
                .build();
        articleCategoryRelMapper.insert(articleCategoryRelDO);

        // 4. 保存文章关联的标签集合
        // 先删除该文章对应的标签

        articleTagRelMapper.deleteByArticleId(articleId);
        List<String> publishTags = updateArticleReqVO.getTags();
        insertTags( articleId,publishTags);
        return Response.success();
    }

    private void insertTags(Long articleId, List<String> publishTags) {
        // 筛选提交的标签（表中不存在的标签）
        List<String> notExistTags = null;
        // 筛选提交的标签（表中已存在的标签）
        List<String> existedTags = null;

        // 查询出所有标签
        List<TagDO> tagDOS = tagMapper.selectList(null);

        // 如果表中还没有添加任何标签
        if (CollectionUtils.isEmpty(tagDOS)) {
            notExistTags = publishTags;
        } else {
            List<String> tagIds = tagDOS.stream().map(tagDO -> String.valueOf(tagDO.getId())).collect(Collectors.toList());
            // 表中已添加相关标签，则需要筛选
            // 通过标签 ID 来筛选，包含对应 ID 则表示提交的标签是表中存在的
            existedTags = publishTags.stream().filter(publishTag -> tagIds.contains(publishTag)).collect(Collectors.toList());
            // 否则则是不存在的
            notExistTags = publishTags.stream().filter(publishTag -> !tagIds.contains(publishTag)).collect(Collectors.toList());

            // 补充逻辑：
            // 还有一种可能：按字符串名称提交上来的标签，也有可能是表中已存在的，比如表中已经有了 Java 标签，用户提交了个 java 小写的标签，需要内部装换为 Java 标签
            Map<String, Long> tagNameIdMap = tagDOS.stream().collect(Collectors.toMap(tagDO -> tagDO.getName().toLowerCase(), TagDO::getId));

            // 使用迭代器进行安全的删除操作
            Iterator<String> iterator = notExistTags.iterator();
            while (iterator.hasNext()) {
                String notExistTag = iterator.next();
                // 转小写, 若 Map 中相同的 key，则表示该新标签是重复标签
                if (tagNameIdMap.containsKey(notExistTag.toLowerCase())) {
                    // 从不存在的标签集合中清除
                    iterator.remove();
                    // 并将对应的 ID 添加到已存在的标签集合
                    existedTags.add(String.valueOf(tagNameIdMap.get(notExistTag.toLowerCase())));
                }
            }
        }

        // 将提交的上来的，已存在于表中的标签，文章-标签关联关系入库
        if (!CollectionUtils.isEmpty(existedTags)) {
            List<ArticleTagRelDO> articleTagRelDOS = Lists.newArrayList();
            existedTags.forEach(tagId -> {
                ArticleTagRelDO articleTagRelDO = ArticleTagRelDO.builder()
                        .articleId(articleId)
                        .tagId(Long.valueOf(tagId))
                        .build();
                articleTagRelDOS.add(articleTagRelDO);
            });
            // 批量插入
            articleTagRelMapper.insertBatchSomeColumn(articleTagRelDOS);
        }

        // 将提交的上来的，不存在于表中的标签，入库保存
        if (!CollectionUtils.isEmpty(notExistTags)) {
            // 需要先将标签入库，拿到对应标签 ID 后，再把文章-标签关联关系入库
            List<ArticleTagRelDO> articleTagRelDOS = Lists.newArrayList();
            notExistTags.forEach(tagName -> {
                TagDO tagDO = TagDO.builder()
                        .name(tagName)
                        .createTime(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .build();

                tagMapper.insert(tagDO);

                // 拿到保存的标签 ID
                Long tagId = tagDO.getId();

                // 文章-标签关联关系
                ArticleTagRelDO articleTagRelDO = ArticleTagRelDO.builder()
                        .articleId(articleId)
                        .tagId(tagId)
                        .build();
                articleTagRelDOS.add(articleTagRelDO);
            });
            // 批量插入
            articleTagRelMapper.insertBatchSomeColumn(articleTagRelDOS);
        }
    }
}
