package com.quanxiaoha.weblog.common.domain.mapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quanxiaoha.weblog.common.domain.dos.CategoryDO;

/**
 * @classname: CategoryMapper
 * @Description: TODO
 * @CreateTime: 2025-07-10 10:51
 * @Author: pht
 */

public interface CategoryMapper extends BaseMapper<CategoryDO> {
    /**
     * 根据用户名查询
     * @param categoryName
     * @return
     */
    default CategoryDO selectByCategoryName(String categoryName) {

        //构建查询条件
        LambdaQueryWrapper<CategoryDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CategoryDO::getName, categoryName);
        return selectOne(queryWrapper);
    }
}
