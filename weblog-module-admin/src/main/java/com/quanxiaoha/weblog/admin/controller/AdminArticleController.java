package com.quanxiaoha.weblog.admin.controller;

import com.quanxiaoha.weblog.admin.model.vo.article.*;
import com.quanxiaoha.weblog.admin.service.IAdminArticleService;
import com.quanxiaoha.weblog.common.aspect.ApiOperationLog;
import com.quanxiaoha.weblog.common.utils.PageResponse;
import com.quanxiaoha.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @classname: AdminArticleController
 * @Description: TODO
 * @CreateTime: 2025-07-25 10:48
 * @Author: pht
 */
@RestController
@RequestMapping("/admin/article")
@Api(tags = "Admin 文章模块")
public class AdminArticleController {


    @Autowired
    private IAdminArticleService adminArticleService;

    @PostMapping("/publish")
    @ApiOperation(value = "文章发布")
    @ApiOperationLog(description = "文章发布")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response publishArticle(@RequestBody @Validated PublishArticleReqVO publishArticleReqVO) {
        return adminArticleService.publishArticle(publishArticleReqVO);
    }



    @PostMapping("/delete")
    @ApiOperation(value = "删除文章")
    @ApiOperationLog(description = "删除文章")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response deleteArticle(@RequestBody @Validated DeleteArticleReqVO deleteArticleReqVO) {
        return adminArticleService.deleteArticle(deleteArticleReqVO);
    }

    @PostMapping("/list")
    @ApiOperation(value = "文章列表")
    @ApiOperationLog(description = "文章列表")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PageResponse findArticleList(@RequestBody @Validated FindArticlePageListReqVO findArticlePageListReqVO) {
        return adminArticleService.findArticlePageList(findArticlePageListReqVO);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "文章详情")
    @ApiOperationLog(description = "文章详情")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response findArticleDetail(@RequestBody @Validated FindArticleDetailReqVO findArticleDetailReqVO) {
        return adminArticleService.findArticleDetail(findArticleDetailReqVO);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新文章")
    @ApiOperationLog(description = "更新文章")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response updateArticle(@RequestBody @Validated UpdateArticleReqVO updateArticleReqVO) {
        return adminArticleService.updateArticle(updateArticleReqVO);
    }
}
