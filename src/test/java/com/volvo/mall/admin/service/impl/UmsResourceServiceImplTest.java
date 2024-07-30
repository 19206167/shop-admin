package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.UmsResourceMapper;
import com.volvo.mall.admin.model.UmsResource;
import com.volvo.mall.admin.service.UmsAdminCacheService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UmsResourceServiceImplTest {
    @Spy
    @InjectMocks
    UmsResourceServiceImpl umsResourceService;

    @Mock
    UmsResourceMapper resourceMapper;

    @Mock
    UmsAdminCacheService adminCacheService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 测试 创建后台资源
     **/
    @Test
    void testCreate() {
        UmsResource umsResource = new UmsResource();
        umsResource.setId(1L);

        when(resourceMapper.insert(any())).thenReturn(1);

        Assertions.assertEquals(1, umsResourceService.create(umsResource));
    }

    /**
     * 测试 更新后台资源
     **/
    @Test
    void testUpdate() {
        Long id = 1L;
        UmsResource umsResource = new UmsResource();
        umsResource.setId(id);

        when(resourceMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, umsResourceService.update(id, umsResource));
    }

    /**
     * 测试 获取某个后台资源getItem
     **/
    @Test
    void testGetItem() {
        Long id = 1L;
        UmsResource umsResource = new UmsResource();
        umsResource.setId(id);

        when(resourceMapper.selectByPrimaryKey(any())).thenReturn(umsResource);

        Assertions.assertEquals(umsResource, umsResourceService.getItem(id));
    }

    /**
     * 测试 删除后台资源成功
     **/
    @Test
    void testDelete() {
        Long id = 1L;

        when(resourceMapper.deleteByPrimaryKey(any())).thenReturn(1);

        Assertions.assertEquals(1, umsResourceService.delete(id));
    }

    /**
     * 测试 展示后台资源成功
     **/
    @Test
    void testList() {
        Long id = 1L;
        UmsResource umsResource = new UmsResource();
        umsResource.setId(id);

        List<UmsResource> exceptedList = new ArrayList<>();
        exceptedList.add(umsResource);

        when(resourceMapper.selectByExample(any())).thenReturn(exceptedList);

        Assertions.assertEquals(1, umsResourceService
                .list(id, "test", "test", 1, 5).size());

    }

    /**
     * 测试 展示所有后台资源成功
     **/
    @Test
    void testListAll() {
        Long id = 1L;
        UmsResource umsResource = new UmsResource();
        umsResource.setId(id);

        List<UmsResource> exceptedList = new ArrayList<>();
        exceptedList.add(umsResource);

        when(resourceMapper.selectByExample(any())).thenReturn(exceptedList);

        Assertions.assertEquals(1, umsResourceService.listAll().size());
    }
}