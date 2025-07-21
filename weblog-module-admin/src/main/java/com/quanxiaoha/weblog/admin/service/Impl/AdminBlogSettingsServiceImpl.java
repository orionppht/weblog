package com.quanxiaoha.weblog.admin.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanxiaoha.weblog.admin.convert.BlogSettingsConvert;
import com.quanxiaoha.weblog.admin.model.vo.setting.FindBlogSettingsRspVO;
import com.quanxiaoha.weblog.admin.model.vo.setting.UpdateBlogSettingsReqVO;
import com.quanxiaoha.weblog.admin.service.IAdminBlogSettingsService;
import com.quanxiaoha.weblog.common.domain.dos.BlogSettingsDO;
import com.quanxiaoha.weblog.common.domain.mapper.BlogSettingsMapper;
import com.quanxiaoha.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @classname: AdminBlogSettingsServiceImpl
 * @Description: TODO
 * @CreateTime: 2025-07-21 14:04
 * @Author: pht
 */
@Service
@Slf4j
public class AdminBlogSettingsServiceImpl extends ServiceImpl<BlogSettingsMapper,BlogSettingsDO> implements IAdminBlogSettingsService {

    @Autowired
    private BlogSettingsMapper blogSettingsMapper;
    @Override
    public Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {

        //vo转do

//        BlogSettingsDO blogSettingsDO = BlogSettingsDO.builder()
//                .id(1L)
//                .logo(updateBlogSettingsReqVO.getLogo())
//                .name(updateBlogSettingsReqVO.getName())
//                .author(updateBlogSettingsReqVO.getAuthor())
//                .introduction(updateBlogSettingsReqVO.getIntroduction())
//                .avatar(updateBlogSettingsReqVO.getAvatar())
//                .githubHomepage(updateBlogSettingsReqVO.getGithubHomepage())
//                .csdnHomepage(updateBlogSettingsReqVO.getCsdnHomepage())
//                .giteeHomepage(updateBlogSettingsReqVO.getGiteeHomepage())
//                .zhihuHomepage(updateBlogSettingsReqVO.getZhihuHomepage())
//                .build();

        BlogSettingsDO blogSettingsDO = BlogSettingsConvert.INSTANCE.convertVO2DO(updateBlogSettingsReqVO);
        blogSettingsDO.setId(1L);
        saveOrUpdate(blogSettingsDO);
        return Response.success();
    }

    @Override
    public Response findBlogSettings() {
        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1L);
        FindBlogSettingsRspVO findBlogSettingsRspVO = BlogSettingsConvert.INSTANCE.convertDO2VO(blogSettingsDO);
        return Response.success(findBlogSettingsRspVO);
    }
}
