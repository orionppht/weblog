package com.quanxiaoha.weblog.admin.service;

import com.quanxiaoha.weblog.admin.model.vo.article.*;
import com.quanxiaoha.weblog.common.utils.PageResponse;
import com.quanxiaoha.weblog.common.utils.Response;

/**
 * @classname: IAdminArticleService
 * @Description: TODO
 * @CreateTime: 2025-07-24 10:45
 * @Author: pht
 */

public interface IAdminArticleService {
    /**
     * 发布文章
     * @param publishArticleReqVO
     * @return
     */
    Response publishArticle(PublishArticleReqVO publishArticleReqVO);


    /**
     * 删除文章
     * @param deleteArticleReqVO
     * @return
     */
    Response deleteArticle(DeleteArticleReqVO deleteArticleReqVO);


    /**
     * 查询文章列表
     * @param findArticlePageListReqVO
     * @return
     */
    PageResponse findArticlePageList(FindArticlePageListReqVO findArticlePageListReqVO);


    /**
     *
     * @param findArticleDetailReqVO
     * @return
     */
    Response findArticleDetail(FindArticleDetailReqVO findArticleDetailReqVO);

    /**
     * 更新文章
     * @param updateArticleReqVO
     * @return
     */
    Response updateArticle (UpdateArticleReqVO updateArticleReqVO);
}
