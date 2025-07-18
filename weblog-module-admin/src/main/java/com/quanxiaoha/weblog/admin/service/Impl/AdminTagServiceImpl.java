package com.quanxiaoha.weblog.admin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanxiaoha.weblog.admin.model.vo.tag.AddTagReqVO;
import com.quanxiaoha.weblog.admin.model.vo.tag.DeleteTagReqVO;
import com.quanxiaoha.weblog.admin.model.vo.tag.FindTagPageListReqVO;
import com.quanxiaoha.weblog.admin.model.vo.tag.FindTagPageListRspVO;
import com.quanxiaoha.weblog.admin.service.IAdminTagService;
import com.quanxiaoha.weblog.common.domain.dos.TagDO;
import com.quanxiaoha.weblog.common.domain.mapper.TagMapper;
import com.quanxiaoha.weblog.common.model.vo.SelectRspVO;
import com.quanxiaoha.weblog.common.utils.PageResponse;
import com.quanxiaoha.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @classname: AdminTagServiceImpl
 * @Description: TODO
 * @CreateTime: 2025-07-17 16:42
 * @Author: pht
 */
@Slf4j
@Service
public class AdminTagServiceImpl extends ServiceImpl<TagMapper, TagDO> implements IAdminTagService {

    @Autowired
    private TagMapper tagMapper;

    /**
     * 添加标签集合
     *
     * @param addTagReqVO
     * @return
     */
    @Override
    public Response addTags(AddTagReqVO addTagReqVO) {
        List<TagDO> tagDOS = addTagReqVO.getTags()
                .stream().map(tagName -> TagDO.builder()
                        .name(tagName.trim())
                        .createTime(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());

        try {
            saveBatch(tagDOS);
        } catch (Exception e) {
            log.warn("该标签已存在", e);
        }


        return Response.success();
    }

    @Override
    public PageResponse findTagList(FindTagPageListReqVO findTagPageListReqVO) {
        Long current = findTagPageListReqVO.getCurrent();
        Long size = findTagPageListReqVO.getSize();
        Page<TagDO> page = new Page<>(current, size);

        LambdaQueryWrapper<TagDO> wrapper = new LambdaQueryWrapper<>();

        String name = findTagPageListReqVO.getName();
        LocalDate startDate = findTagPageListReqVO.getStartDate();
        LocalDate endDate = findTagPageListReqVO.getEndDate();
        wrapper
                .like(StringUtils.isNotBlank(name), TagDO::getName, name.trim())
                .ge(Objects.nonNull(startDate), TagDO::getCreateTime, startDate)
                .le(Objects.nonNull(endDate), TagDO::getCreateTime, endDate)
                .orderByDesc(TagDO::getCreateTime);

        //执行分页查询
        Page<TagDO> tagDOPage = tagMapper.selectPage(page, wrapper);
        List<TagDO> tagDOS = tagDOPage.getRecords();
        List<FindTagPageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
            vos = tagDOS.stream().map(tagDO -> FindTagPageListRspVO.builder()
                    .id(tagDO.getId())
                    .name(tagDO.getName())
                    .createTime(tagDO.getCreateTime())
                    .build()).collect(Collectors.toList());
        }
        return PageResponse.success(tagDOPage, vos);

    }

    @Override
    public Response deleteTag(DeleteTagReqVO deleteTagReqVO) {
        Long id = deleteTagReqVO.getId();
        tagMapper.deleteById(id);
        log.info("删除标签成功，标签ID：{}", id);
        return Response.success();
    }

    @Override
    public Response findTagSelectList() {
        List<TagDO> tagDOS = tagMapper.selectList(null);
        List<SelectRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
            vos = tagDOS.stream().map(tagDO -> SelectRspVO.builder()
                    .label(tagDO.getName())
                    .value(tagDO.getId())
                    .build()).collect(Collectors.toList());
        }
        return Response.success(vos);
    }


}
