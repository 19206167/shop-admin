package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.dao.PmsProductAttributeCategoryDao;
import com.volvo.mall.admin.dto.PmsProductAttributeCategoryItem;
import com.volvo.mall.admin.mapper.PmsProductAttributeCategoryMapper;
import com.volvo.mall.admin.model.PmsProductAttributeCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PmsProductAttributeCategoryServiceImplTest {

    @InjectMocks
    PmsProductAttributeCategoryServiceImpl pmsProductAttributeCategoryService;

    @Mock
    PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Mock
    private PmsProductAttributeCategoryDao productAttributeCategoryDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 创建商品分类
     **/
    @Test
    void testCreate() {
        when(productAttributeCategoryMapper.insertSelective(any())).thenReturn(1);

        Assertions.assertEquals(1, pmsProductAttributeCategoryService.create("test"));
    }

    /**
     * 更新商品分类
     **/
    @Test
    void testUpdate() {
        when(productAttributeCategoryMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, pmsProductAttributeCategoryService.update(1L, "test"));
    }

    /**
     * 删除商品分类
     **/
    @Test
    void testDelete() {
        when(productAttributeCategoryMapper.deleteByPrimaryKey(any())).thenReturn(1);

        Assertions.assertEquals(1, pmsProductAttributeCategoryService.delete(1L));
    }

    /**
     * 得到商品分类
     **/
    @Test
    void testGetItem() {
        PmsProductAttributeCategory pmsProductAttributeCategory = new PmsProductAttributeCategory();
        pmsProductAttributeCategory.setId(1L);
        pmsProductAttributeCategory.setName("test");

        when(productAttributeCategoryMapper.selectByPrimaryKey(any())).thenReturn(pmsProductAttributeCategory);

        Assertions.assertEquals(pmsProductAttributeCategory, pmsProductAttributeCategoryService.getItem(1L));
    }

    /**
     * 得到商品分类list
     **/
    @Test
    void testGetList() {
        PmsProductAttributeCategory pmsProductAttributeCategory = new PmsProductAttributeCategory();
        pmsProductAttributeCategory.setId(1L);
        pmsProductAttributeCategory.setName("test");

        when(productAttributeCategoryMapper.selectByExample(any())).thenReturn(Collections.singletonList(pmsProductAttributeCategory));

        Assertions.assertNotNull(pmsProductAttributeCategoryService.getList(1, 5));
    }

    /**
     * 获取包含属性的商品属性分类
     **/
    @Test
    void testGetListWithAttr() {
        PmsProductAttributeCategoryItem pmsProductAttributeCategoryItem = new PmsProductAttributeCategoryItem();
        pmsProductAttributeCategoryItem.setId(1L);
        pmsProductAttributeCategoryItem.setName("test");

        when(productAttributeCategoryDao.getListWithAttr()).thenReturn(Collections.singletonList(pmsProductAttributeCategoryItem));

        Assertions.assertNotNull(pmsProductAttributeCategoryService.getListWithAttr());
    }
}