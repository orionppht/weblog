package com.quanxiaoha.weblog.common.domain.mapper;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.quanxiaoha.weblog.common.config.InsertBatchMapper;
import com.quanxiaoha.weblog.common.domain.dos.ArticleTagRelDO;

import java.util.List;

/**
 * @classname: ArticleTagRelMapper
 * @Description: TODO
 * @CreateTime: 2025-07-24 09:56
 * @Author: pht
 */

public interface ArticleTagRelMapper extends InsertBatchMapper<ArticleTagRelDO> {

    /**
     * 根据文章 ID 删除关联记录
     * @param articleId
     * @return
     */
    default int deleteByArticleId(Long articleId) {
        return delete(Wrappers.<ArticleTagRelDO>lambdaQuery()
                .eq(ArticleTagRelDO::getArticleId, articleId));
    }

    /**
     * 根据文章 ID 来查询
     * @param articleId
     * @return
     */
    default List<ArticleTagRelDO> selectByArticleId(Long articleId) {
        return selectList(Wrappers.<ArticleTagRelDO>lambdaQuery()
                .eq(ArticleTagRelDO::getArticleId, articleId));
    }
}
