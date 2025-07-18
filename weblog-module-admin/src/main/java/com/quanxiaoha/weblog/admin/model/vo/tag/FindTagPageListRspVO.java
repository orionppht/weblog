package com.quanxiaoha.weblog.admin.model.vo.tag;

import com.quanxiaoha.weblog.common.model.BasePageQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @classname: FindCategoryPageListRspVO
 * @Description: TODO
 * @CreateTime: 2025-07-16 10:42
 * @Author: pht
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindTagPageListRspVO extends BasePageQuery {
    /**
     * 标签 ID
     */
    private Long id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
