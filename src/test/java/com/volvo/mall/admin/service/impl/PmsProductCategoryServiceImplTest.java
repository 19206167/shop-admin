package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.dao.PmsProductCategoryAttributeRelationDao;
import com.volvo.mall.admin.dao.PmsProductCategoryDao;
import com.volvo.mall.admin.dto.PmsProductCategoryParam;
import com.volvo.mall.admin.dto.PmsProductCategoryWithChildrenItem;
import com.volvo.mall.admin.mapper.PmsProductCategoryAttributeRelationMapper;
import com.volvo.mall.admin.mapper.PmsProductCategoryMapper;
import com.volvo.mall.admin.mapper.PmsProductMapper;
import com.volvo.mall.admin.model.PmsProductCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PmsProductCategoryServiceImplTest {

    @Mock
    private PmsProductCategoryMapper productCategoryMapper;
    @Mock
    private PmsProductMapper productMapper;
    @Mock
    private PmsProductCategoryAttributeRelationDao productCategoryAttributeRelationDao;
    @Mock
    private PmsProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper;
    @Mock
    private PmsProductCategoryDao productCategoryDao;

    @InjectMocks
    PmsProductCategoryServiceImpl pmsProductCategoryService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        PmsProductCategoryParam pmsProductCategoryParam = new PmsProductCategoryParam();

        pmsProductCategoryParam.setName("Test");
        pmsProductCategoryParam.setParentId(0L);
        pmsProductCategoryParam.setProductAttributeIdList(Collections.singletonList(1L));

        when(productCategoryMapper.insertSelective(any())).thenReturn(1);

        Assertions.assertEquals(1, pmsProductCategoryService.create(pmsProductCategoryParam));
    }

    @Test
    void testUpdate() {
        PmsProductCategoryParam pmsProductCategoryParam = new PmsProductCategoryParam();

        pmsProductCategoryParam.setName("Test");
        pmsProductCategoryParam.setParentId(0L);
        pmsProductCategoryParam.setProductAttributeIdList(Collections.singletonList(1L));

        when(productCategoryMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, pmsProductCategoryService.update(1L, pmsProductCategoryParam));
    }

    @Test
    void testGetList() {
        PmsProductCategory pmsProductCategory = new PmsProductCategory();
        pmsProductCategory.setId(1L);

        when(productCategoryMapper.selectByExample(any())).thenReturn(Collections.singletonList(pmsProductCategory));

        Assertions.assertNotNull(pmsProductCategoryService.getList(1L, 1, 5));
    }

    @Test
    void testDelete() {
        when(productCategoryMapper.deleteByPrimaryKey(any())).thenReturn(1);

        Assertions.assertEquals(1, pmsProductCategoryService.delete(1L));
    }

    @Test
    void getItem() {
        PmsProductCategory pmsProductCategory = new PmsProductCategory();
        pmsProductCategory.setId(1L);

        when(productCategoryMapper.selectByPrimaryKey(any())).thenReturn(pmsProductCategory);

        Assertions.assertEquals(pmsProductCategory, pmsProductCategoryService.getItem(1L));
    }

    @Test
    void updateNavStatus() {
        when(productCategoryMapper.updateByExampleSelective(any(), any())).thenReturn(1);

        assertEquals(1, pmsProductCategoryService.updateNavStatus(Collections.singletonList(1L), 0));
    }

    @Test
    void updateShowStatus() {
        when(productCategoryMapper.updateByExampleSelective(any(), any())).thenReturn(1);

        assertEquals(1, pmsProductCategoryService.updateShowStatus(Collections.singletonList(1L), 0));
    }

    @Test
    void listWithChildren() {
        List<PmsProductCategoryWithChildrenItem> exceptedList = new ArrayList<>();
        PmsProductCategoryWithChildrenItem pmsProductCategoryWithChildrenItem = new PmsProductCategoryWithChildrenItem();
        pmsProductCategoryWithChildrenItem.setId(1L);

        exceptedList.add(pmsProductCategoryWithChildrenItem);
        when(productCategoryDao.listWithChildren()).thenReturn(exceptedList);

        Assertions.assertNotNull(pmsProductCategoryService.listWithChildren());
    }
}