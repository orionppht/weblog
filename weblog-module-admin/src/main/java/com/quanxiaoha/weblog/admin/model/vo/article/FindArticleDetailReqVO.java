package com.quanxiaoha.weblog.admin.model.vo.article;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @classname: FindArticleDetailReqVO
 * @Description: TODO
 * @CreateTime: 2025-07-29 10:15
 * @Author: pht
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询文章详情入参 VO")
public class FindArticleDetailReqVO {
    /**
     * 文章 ID
     */
    @NotNull(message = "文章 ID 不能为空")
    private Long id;

}
