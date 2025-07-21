package com.quanxiaoha.weblog.admin.convert;

import com.quanxiaoha.weblog.admin.model.vo.setting.FindBlogSettingsRspVO;
import com.quanxiaoha.weblog.admin.model.vo.setting.UpdateBlogSettingsReqVO;
import com.quanxiaoha.weblog.common.domain.dos.BlogSettingsDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @classname: BlogSettingsConvert
 * @Description: TODO
 * @CreateTime: 2025-07-21 15:19
 * @Author: pht
 */
@Mapper
public interface BlogSettingsConvert {
    /**
     * 初始化 convert 实例
     */
    BlogSettingsConvert INSTANCE = Mappers.getMapper(BlogSettingsConvert.class);

    /**
     * 将 VO 转化为 DO
     * @param bean
     * @return
     */
    BlogSettingsDO convertVO2DO(UpdateBlogSettingsReqVO bean);

    /**
     * 将 DO 转化为 VO
     * @param bean
     * @return
     */
    FindBlogSettingsRspVO convertDO2VO(BlogSettingsDO bean);
}
