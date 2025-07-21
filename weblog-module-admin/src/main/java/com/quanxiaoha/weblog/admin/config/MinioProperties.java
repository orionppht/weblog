package com.quanxiaoha.weblog.admin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @classname: MinioProperties
 * @Description: TODO
 * @CreateTime: 2025-07-18 16:24
 * @Author: pht
 */
@ConfigurationProperties(prefix = "minio")
@Component
@Data
public class MinioProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;

}
