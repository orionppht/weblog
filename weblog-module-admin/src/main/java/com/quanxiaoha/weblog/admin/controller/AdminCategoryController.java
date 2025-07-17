package com.quanxiaoha.weblog.admin.controller;

import com.quanxiaoha.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.quanxiaoha.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.quanxiaoha.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.quanxiaoha.weblog.admin.service.IAdminCategoryService;
import com.quanxiaoha.weblog.common.aspect.ApiOperationLog;
import com.quanxiaoha.weblog.common.utils.PageResponse;
import com.quanxiaoha.weblog.common.utils.Response;
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


    @PostMapping("/category/list")
    @ApiOperation(value = "查询分类列表")
    @ApiOperationLog(description = "查询分类列表")
    public PageResponse findCategoryList(@RequestBody FindCategoryPageListReqVO findCategoryPageListReqVO){
        return adminCategoryService.findCategoryList(findCategoryPageListReqVO);
    }

    @PostMapping("/category/delete")
    @ApiOperation(value = "删除分类")
    @ApiOperationLog(description = "删除分类")
    public Response deleteCategory(@RequestBody DeleteCategoryReqVO deleteCategoryReqVO) {
        return adminCategoryService.deleteCategory(deleteCategoryReqVO);
    }

    @PostMapping("/category/select/list")
    @ApiOperation(value = "查询下拉分类列表")
    @ApiOperationLog(description = "查询下拉分类列表")
    public Response findCategorySelectList() {
        return adminCategoryService.findCategorySelectList();
    }
}
