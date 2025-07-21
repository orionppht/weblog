package com.quanxiaoha.weblog.admin.controller;

import com.quanxiaoha.weblog.admin.service.IAdminFileService;
import com.quanxiaoha.weblog.common.aspect.ApiOperationLog;
import com.quanxiaoha.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @classname: AdminFileController
 * @Description: TODO
 * @CreateTime: 2025-07-18 16:48
 * @Author: pht
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "admin 文件模块")
public class AdminFileController {

    @Autowired
    private IAdminFileService adminFileService;

    @PostMapping("/file/upload")
    @ApiOperation(value = "文件上传")
    @ApiOperationLog(description = "文件上传")
    public Response uploadFile(MultipartFile file) {
        return adminFileService.uploadFile(file);
    }
}
