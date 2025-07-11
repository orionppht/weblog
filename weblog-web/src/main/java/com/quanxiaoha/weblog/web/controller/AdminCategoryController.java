package com.quanxiaoha.weblog.web.controller;

import com.quanxiaoha.weblog.common.aspect.ApiOperationLog;
import com.quanxiaoha.weblog.common.utils.Response;
import com.quanxiaoha.weblog.web.model.vo.categery.AddCategoryReqVO;
import com.quanxiaoha.weblog.web.service.IAdminCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @classname: AdminCategoryController
 * @Description: TODO
 * @CreateTime: 2025-07-10 15:25
 * @Author: pht
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "admin 分类模块")
public class AdminCategoryController {

    @Autowired
    private IAdminCategoryService adminCategoryService;
    @PostMapping("/category/add")
    @ApiOperation(value = "添加分类")
    @ApiOperationLog(description = "添加分类")
    public Response addCategory(@RequestBody @Validated AddCategoryReqVO addCategoryReqVO)  {
        return adminCategoryService.addCategory(addCategoryReqVO);
    }

}
