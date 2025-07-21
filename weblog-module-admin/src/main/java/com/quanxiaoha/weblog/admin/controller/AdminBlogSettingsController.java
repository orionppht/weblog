package com.quanxiaoha.weblog.admin.controller;

import com.quanxiaoha.weblog.admin.model.vo.setting.UpdateBlogSettingsReqVO;
import com.quanxiaoha.weblog.admin.service.IAdminBlogSettingsService;
import com.quanxiaoha.weblog.common.aspect.ApiOperationLog;
import com.quanxiaoha.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.simpleframework.xml.core.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @classname: AdminBlogSettingsController
 * @Description: TODO
 * @CreateTime: 2025-07-21 14:15
 * @Author: pht
 */
@RestController
@RequestMapping("/admin/blog/settings")
@Api(tags = "Admin 博客设置模块")
public class AdminBlogSettingsController {

    @Autowired
    private IAdminBlogSettingsService adminBlogSettingsService;

    @ApiOperation(value = "修改博客设置")
    @ApiOperationLog(description = "修改博客设置")
    @PostMapping("/update")
    public Response updateBlogSettings(@RequestBody @Validate UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        return adminBlogSettingsService.updateBlogSettings(updateBlogSettingsReqVO);
    }


    @ApiOperation(value = "查询博客设置")
    @ApiOperationLog(description = "查询博客设置")
    @PostMapping("/detail")
    public Response findBlogSettings() {
        return adminBlogSettingsService.findBlogSettings();
    }
}
