package com.quanxiaoha.weblog.admin.service;

import com.quanxiaoha.weblog.admin.model.vo.tag.AddTagReqVO;
import com.quanxiaoha.weblog.admin.model.vo.tag.DeleteTagReqVO;
import com.quanxiaoha.weblog.admin.model.vo.tag.FindTagPageListReqVO;
import com.quanxiaoha.weblog.common.utils.PageResponse;
import com.quanxiaoha.weblog.common.utils.Response;

/**
 * @classname: IAdminTagService
 * @Description: TODO
 * @CreateTime: 2025-07-17 16:40
 * @Author: pht
 */

public interface IAdminTagService {
    /**
     * 批量添加标签
     * @param addTagReqVO
     * @return
     */
    Response addTags(AddTagReqVO addTagReqVO);

    /**
     * 查询标签列表
     * @param findTagPageListReqVO
     * @return
     */
    PageResponse findTagList(FindTagPageListReqVO findTagPageListReqVO);


    /**
     * 删除标签
     * @param deleteTagReqVO
     * @return
     */
    Response deleteTag(DeleteTagReqVO deleteTagReqVO);

    /**
     * 查询标签下拉列表
     * @return
     */
    Response findTagSelectList();

}
