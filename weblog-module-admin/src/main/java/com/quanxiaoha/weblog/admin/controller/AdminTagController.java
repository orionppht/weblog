package com.quanxiaoha.weblog.admin.controller;

import com.quanxiaoha.weblog.admin.model.vo.tag.AddTagReqVO;
import com.quanxiaoha.weblog.admin.model.vo.tag.DeleteTagReqVO;
import com.quanxiaoha.weblog.admin.model.vo.tag.FindTagPageListReqVO;
import com.quanxiaoha.weblog.admin.service.IAdminTagService;
import com.quanxiaoha.weblog.common.aspect.ApiOperationLog;
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
 * @classname: AdminTagController
 * @Description: TODO
 * @CreateTime: 2025-07-17 16:27
 * @Author: pht
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "admin 标签模块")
public class AdminTagController {

    @Autowired
    private IAdminTagService adminTagService;


    @PostMapping ("/tag/add")
    @ApiOperation(value = "添加标签")
    @ApiOperationLog(description = "添加标签")
    public Response addTags(@RequestBody @Validated AddTagReqVO addTagReqVO)  {
        return adminTagService.addTags(addTagReqVO);
    }

    @PostMapping ("/tag/list")
    @ApiOperation(value = "查询标签列表")
    @ApiOperationLog(description = "查询标签列表")
    public Response findTagList(@RequestBody @Validated FindTagPageListReqVO findTagPageListReqVO)  {
        return adminTagService.findTagList(findTagPageListReqVO);
    }


    @PostMapping ("/tag/delete")
    @ApiOperation(value = "删除标签")
    @ApiOperationLog(description = "删除标签")
    public Response deleteTag(@RequestBody @Validated DeleteTagReqVO deleteTagReqVO)  {
        return adminTagService.deleteTag(deleteTagReqVO);
    }


    @PostMapping ("/tag/select/list")
    @ApiOperation(value = "查询标签下拉列表")
    @ApiOperationLog(description = "查询标签下拉列表")
    public Response findTagSelectList()  {
        return adminTagService.findTagSelectList();
    }

}
