package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.dao.PmsProductAttributeDao;
import com.volvo.mall.admin.dto.PmsProductAttributeParam;
import com.volvo.mall.admin.dto.ProductAttrInfo;
import com.volvo.mall.admin.mapper.PmsProductAttributeCategoryMapper;
import com.volvo.mall.admin.mapper.PmsProductAttributeMapper;
import com.volvo.mall.admin.model.PmsProductAttribute;
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

class PmsProductAttributeServiceImplTest {
    @InjectMocks
    PmsProductAttributeServiceImpl pmsProductAttributeService;

    @Mock
    private PmsProductAttributeMapper productAttributeMapper;
    @Mock
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;
    @Mock
    private PmsProductAttributeDao productAttributeDao;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 测试获取 product Attribute List
     **/
    @Test
    void testGetList() {
        PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();
        pmsProductAttribute.setId(1L);

        when(productAttributeMapper.selectByExample(any())).thenReturn(Collections.singletonList(pmsProductAttribute));

        Assertions.assertNotNull(pmsProductAttributeService.getList(1L, 1, 1, 5));
    }

    /**
     * 测试创建 product Attribute
     **/
    @Test
    void testCreate() {
        PmsProductAttributeParam pmsProductAttributeParam = new PmsProductAttributeParam();
        pmsProductAttributeParam.setProductAttributeCategoryId(1L);
        pmsProductAttributeParam.setName("test");
        pmsProductAttributeParam.setType(1);

        when(productAttributeMapper.insertSelective(any())).thenReturn(1);

        PmsProductAttributeCategory pmsProductAttributeCategory = new PmsProductAttributeCategory();
        pmsProductAttributeCategory.setParamCount(1);

        when(productAttributeCategoryMapper.selectByPrimaryKey(any())).thenReturn(pmsProductAttributeCategory);

        Assertions.assertEquals(1, pmsProductAttributeService.create(pmsProductAttributeParam));
    }

    @Test
    void testUpdate() {
        PmsProductAttributeParam pmsProductAttributeParam = new PmsProductAttributeParam();
        pmsProductAttributeParam.setProductAttributeCategoryId(1L);
        pmsProductAttributeParam.setName("test");
        pmsProductAttributeParam.setType(1);

        when(productAttributeMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, pmsProductAttributeService.update(1L, pmsProductAttributeParam));
    }

    @Test
    void testGetItem() {
        PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();
        pmsProductAttribute.setId(1L);

        when(productAttributeMapper.selectByPrimaryKey(any())).thenReturn(pmsProductAttribute);

        Assertions.assertEquals(pmsProductAttribute, pmsProductAttributeService.getItem(1L));
    }

    @Test
    void testDelete() {
        PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();
        pmsProductAttribute.setId(1L);
        pmsProductAttribute.setType(1);
        pmsProductAttribute.setProductAttributeCategoryId(1L);

        PmsProductAttributeCategory pmsProductAttributeCategory = new PmsProductAttributeCategory();
        pmsProductAttributeCategory.setParamCount(1);
        pmsProductAttributeCategory.setParamCount(0);

        when(productAttributeMapper.deleteByExample(any())).thenReturn(2);

        when(productAttributeCategoryMapper.selectByPrimaryKey(any())).thenReturn(pmsProductAttributeCategory);

        when(productAttributeMapper.selectByPrimaryKey(any())).thenReturn(pmsProductAttribute);

        Assertions.assertEquals(2, pmsProductAttributeService.delete(Collections.singletonList(1L)));
    }

    @Test
    void testGetProductAttrInfo() {
        ProductAttrInfo productAttrInfo = new ProductAttrInfo();
        productAttrInfo.setAttributeId(1L);

        when(productAttributeDao.getProductAttrInfo(any())).thenReturn(Collections.singletonList(productAttrInfo));

        Assertions.assertNotNull(pmsProductAttributeService.getProductAttrInfo(1L));
    }
}