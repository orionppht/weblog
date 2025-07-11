package com.quanxiaoha.weblog.web.service;

import com.quanxiaoha.weblog.common.utils.Response;
import com.quanxiaoha.weblog.web.model.vo.categery.AddCategoryReqVO;

/**
 * @classname: IAdminCategoryService
 * @Description: TODO
 * @CreateTime: 2025-07-10 14:07
 * @Author: pht
 */

public interface IAdminCategoryService {
    /**
     * 添加分类
     * @param addCategoryReqVO
     * @return
     */
    Response addCategory(AddCategoryReqVO addCategoryReqVO);

    /**
     * 删除分类
     * @param categoryId
     * @return
     */
    Response deleteCategory(Long categoryId);
}
