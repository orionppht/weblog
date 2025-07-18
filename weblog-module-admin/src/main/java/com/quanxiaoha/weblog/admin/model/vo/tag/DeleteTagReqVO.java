package com.quanxiaoha.weblog.admin.model.vo.tag;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @classname: DeleteCategoryReqVO
 * @Description: TODO
 * @CreateTime: 2025-07-16 16:25
 * @Author: pht
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "删除标签 VO")
public class DeleteTagReqVO {
    @NotNull(message = "标签ID 不能为空")
    private Long id;
}
