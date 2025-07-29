package com.quanxiaoha.weblog.admin.model.vo.article;

import com.quanxiaoha.weblog.common.model.BasePageQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @classname: FindArticlePageListRspVO
 * @Description: TODO
 * @CreateTime: 2025-07-28 17:08
 * @Author: pht
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindArticlePageListRspVO extends BasePageQuery {
    /**
     * 文章 ID
     */
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章封面
     */
    private String cover;

    /**
     * 发布时间
     */
    private LocalDateTime createTime;
}
