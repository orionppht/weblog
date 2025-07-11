package com.quanxiaoha.weblog.web.service.Impl;

import com.quanxiaoha.weblog.common.domain.dos.CategoryDO;
import com.quanxiaoha.weblog.common.domain.mapper.CategoryMapper;
import com.quanxiaoha.weblog.common.exception.BizException;
import com.quanxiaoha.weblog.common.utils.Response;
import com.quanxiaoha.weblog.web.model.vo.categery.AddCategoryReqVO;
import com.quanxiaoha.weblog.web.service.IAdminCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quanxiaoha.weblog.common.enums.ResponseCodeEnum;
import java.util.Objects;

/**
 * @classname: AdminCategoryServiceImpl
 * @Description: TODO
 * @CreateTime: 2025-07-10 14:28
 * @Author: pht
 */
@Slf4j
@Service
public class AdminCategoryServiceImpl implements IAdminCategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 添加分类
     * @param addCategoryReqVO
     * @return
     */
    @Override
    public Response addCategory(AddCategoryReqVO addCategoryReqVO) {
        String categoryName = addCategoryReqVO.getName();

        CategoryDO categoryDO = categoryMapper.selectByCategoryName(categoryName);

        if(Objects.nonNull(categoryDO)){
            log.warn("分类名称：{}，此分类已存在", categoryName);
            throw new BizException(ResponseCodeEnum.CATEGORY_NAME_IS_EXISTED);
        }
        categoryMapper.insert(CategoryDO.builder()
                .name(categoryName)
                .build());
        return Response.success();
    }

    @Override
    public Response deleteCategory(Long categoryId) {
        categoryMapper.deleteById(categoryId);
        log.info("删除分类成功，分类ID：{}", categoryId);

        return Response.success();
    }
}
