package com.quanxiaoha.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quanxiaoha.weblog.common.domain.dos.TagDO;

/**
 * @classname: TagMapper
 * @Description: TODO
 * @CreateTime: 2025-07-10 10:51
 * @Author: pht
 */

public interface TagMapper extends BaseMapper<TagDO> {


    default TagDO selectByTagName(String tagName) {

        //构建查询条件
        LambdaQueryWrapper<TagDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TagDO::getName, tagName);
        return selectOne(queryWrapper);
    }
}
