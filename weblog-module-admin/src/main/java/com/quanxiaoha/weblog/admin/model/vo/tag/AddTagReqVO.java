package com.quanxiaoha.weblog.admin.model.vo.tag;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @classname: AddTagReqVO
 * @Description: TODO
 * @CreateTime: 2025-07-17 16:24
 * @Author: pht
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "添加标签 VO")
public class AddTagReqVO {
    @NotEmpty(message = "标签集合不能为空")
    private List<String> tags;
}
