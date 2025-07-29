package com.quanxiaoha.weblog.common.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @classname: InsertBatchMapper
 * @Description: TODO
 * @CreateTime: 2025-07-25 14:02
 * @Author: pht
 */

public interface InsertBatchMapper<T> extends BaseMapper<T> {
    // 批量插入
    int insertBatchSomeColumn(@Param("list") List<T> batchList);
}
