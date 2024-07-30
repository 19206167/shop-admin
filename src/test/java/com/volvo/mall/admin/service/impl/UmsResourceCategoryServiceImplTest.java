package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.UmsResourceCategoryMapper;
import com.volvo.mall.admin.model.UmsResourceCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UmsResourceCategoryServiceImplTest {
    @Mock
    UmsResourceCategoryMapper resourceCategoryMapper;

    @InjectMocks
    @Spy
    UmsResourceCategoryServiceImpl umsResourceCategoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 测试list全部UmsResourceCategory
     **/
    @Test
    void testListAll() {
        UmsResourceCategory umsResourceCategory = new UmsResourceCategory();
        umsResourceCategory.setId(1L);

        List<UmsResourceCategory> exceptedList = new ArrayList<>();
        exceptedList.add(umsResourceCategory);

        when(resourceCategoryMapper.selectByExample(any())).thenReturn(exceptedList);

        Assertions.assertEquals(1, umsResourceCategoryService.listAll().size());
    }

    /**
     * 测试创建新的UmsResourceCategory
     **/
    @Test
    void testCreate() {
        UmsResourceCategory umsResourceCategory = new UmsResourceCategory();
        umsResourceCategory.setId(1L);

        when(resourceCategoryMapper.insert(any())).thenReturn(1);

        Assertions.assertEquals(1, umsResourceCategoryService.create(umsResourceCategory));
    }

    /**
     * 测试更新UmsResourceCategory
     **/
    @Test
    void testUpdate() {
        Long id = 1L;

        UmsResourceCategory umsResourceCategory = new UmsResourceCategory();
        umsResourceCategory.setId(id);

        when(resourceCategoryMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, umsResourceCategoryService.update(id, umsResourceCategory));
    }

    /**
     * 测试删除UmsResourceCategory
     **/
    @Test
    void testDelete() {
        Long id = 1L;

        when(resourceCategoryMapper.deleteByPrimaryKey(any())).thenReturn(1);

        Assertions.assertEquals(1, umsResourceCategoryService.delete(id));
    }
}