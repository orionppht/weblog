package com.quanxiaoha.weblog.admin.service;

import com.quanxiaoha.weblog.common.utils.Response;
import org.springframework.web.multipart.MultipartFile;

/**
 * @classname: IAdminFileService
 * @Description: TODO
 * @CreateTime: 2025-07-18 16:44
 * @Author: pht
 */

public interface IAdminFileService {
    /**
     * 文件上传
     * @param file
     * @return
     */
    Response uploadFile(MultipartFile file);
}
