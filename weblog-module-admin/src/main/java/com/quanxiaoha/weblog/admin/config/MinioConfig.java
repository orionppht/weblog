package com.quanxiaoha.weblog.admin.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @classname: MinioConfig
 * @Description: TODO
 * @CreateTime: 2025-07-18 16:23
 * @Author: pht
 */
@Configuration
public class MinioConfig {
    @Autowired
    private MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() {
        // 构建 Minio 客户端
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}
