package com.quanxiaoha.weblog.admin.service;

import com.quanxiaoha.weblog.admin.model.vo.setting.UpdateBlogSettingsReqVO;
import com.quanxiaoha.weblog.common.utils.Response;

/**
 * @classname: IAdminBlogSettingsService
 * @Description: TODO
 * @CreateTime: 2025-07-21 14:03
 * @Author: pht
 */

public interface IAdminBlogSettingsService {
    /**
     * 修改博客基础信息
     * @param updateBlogSettingsReqVO
     * @return
     */
    Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO);


    Response findBlogSettings();
}
