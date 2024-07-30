package com.volvo.mall.admin.dao;

import com.volvo.mall.admin.dto.PmsProductAttributeCategoryItem;

import java.util.List;

/**
 * 商品属性分类管理自定义Dao
 */
public interface PmsProductAttributeCategoryDao {
    /**
     * 获取包含属性的商品属性分类
     */
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}