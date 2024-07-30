package com.volvo.mall.admin.service.impl;

import com.google.common.collect.ImmutableList;
import com.volvo.mall.admin.dao.UmsAdminRoleRelationDao;
import com.volvo.mall.admin.mapper.UmsAdminMapper;
import com.volvo.mall.admin.mapper.UmsAdminRoleRelationMapper;
import com.volvo.mall.admin.model.UmsAdmin;
import com.volvo.mall.admin.model.UmsAdminRoleRelation;
import com.volvo.mall.admin.model.UmsResource;
import com.volvo.mall.admin.service.UmsAdminService;
import com.volvo.mall.common.service.RedisService;
import com.volvo.mall.common.service.impl.RedisServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UmsAdminCacheServiceImplTest {
    @Mock
    UmsAdminService adminService;

    @InjectMocks
    @Spy
    RedisServiceImpl redisService;

    @Mock
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;

    @Mock
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @InjectMocks
    @Spy
    UmsAdminCacheServiceImpl umsAdminCacheService;

    @Autowired
    RedisTemplate redisTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 测试删除admin成功
     **/
    @Test
    void testDelAdmin() {
        long adminId = 1L;

        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(adminId);
        umsAdmin.setUsername("test");


        Mockito.doNothing().when(umsAdminCacheService).delAdmin(any());

        umsAdminCacheService.delAdmin(adminId);

        Mockito.verify(umsAdminCacheService, Mockito.times(1)).delAdmin(adminId);
    }

    /**
     * 测试删除resourceList成功
     **/
    @Test
    void delResourceList() {
        long adminId = 1L;

        Mockito.doNothing().when(umsAdminCacheService).delResourceList(any());

        umsAdminCacheService.delResourceList(adminId);

        Mockito.verify(umsAdminCacheService, Mockito.times(1)).delResourceList(adminId);
    }

    /**
     * 测试根据角色删除resourceList成功
     **/
    @Test
    void testDelResourceListByRole() {
        long adminId = 1L;

        UmsAdminRoleRelation umsAdminRoleRelation = new UmsAdminRoleRelation();
        umsAdminRoleRelation.setAdminId(1L);
        List<UmsAdminRoleRelation> exceptedList = new ArrayList<>();

        when(adminRoleRelationMapper.selectByExample(any())).thenReturn(exceptedList);

        Mockito.doNothing().when(umsAdminCacheService).delResourceListByRole(any());

        umsAdminCacheService.delResourceListByRole(1L);

        Mockito.verify(umsAdminCacheService, Mockito.times(1)).delResourceListByRole(1L);
    }

    /**
     * 测试根据角色list删除resourceList成功
     **/
    @Test
    void testDelResourceListByRoleIds() {
        long adminId = 1L;

        UmsAdminRoleRelation umsAdminRoleRelation = new UmsAdminRoleRelation();
        umsAdminRoleRelation.setAdminId(1L);
        List<UmsAdminRoleRelation> exceptedList = new ArrayList<>();

        when(adminRoleRelationMapper.selectByExample(any())).thenReturn(exceptedList);

        Mockito.doNothing().when(umsAdminCacheService).delResourceListByRoleIds(any());

        Long[] roleIds = new Long[]{1L, 2L};

        umsAdminCacheService.delResourceListByRoleIds(Arrays.asList((roleIds)));

        Mockito.verify(umsAdminCacheService, Mockito.times(1)).delResourceListByRoleIds(Arrays.asList((roleIds)));
    }

    /**
     * 测试根据resourceId删除resourceList成功
     **/
    @Test
    void delResourceListByResource() {
        long resourceId = 1L;

        when(adminRoleRelationDao.getAdminIdList(any())).thenReturn(Collections.singletonList(1L));

        Mockito.doNothing().when(umsAdminCacheService).delResourceListByResource(any());

        umsAdminCacheService.delResourceListByResource(resourceId);

        Mockito.verify(umsAdminCacheService, Mockito.times(1)).delResourceListByResource(resourceId);
    }

    /**
     * 测试getAdmin
     **/
    @Test
    void getAdmin() {
        String username = "test";

        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(1L);
        umsAdmin.setUsername(username);

        when(redisTemplate.opsForValue().get(any())).thenReturn(umsAdmin);

        Assertions.assertEquals(umsAdmin, umsAdminCacheService.getAdmin(username));
    }

    /**
     * 测试setAdmin
     **/
    @Test
    void setAdmin() {
        String username = "test";

        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(1L);
        umsAdmin.setUsername(username);

        Mockito.doNothing().when(umsAdminCacheService).setAdmin(any());

        umsAdminCacheService.setAdmin(umsAdmin);

        Mockito.verify(umsAdminCacheService, Mockito.times(1)).setAdmin(umsAdmin);
    }

    /**
     * 测试根据adminId获取resourceList成功
     **/
    @Test
    void getResourceList() {
        long adminId = 1L;

        UmsResource umsResource = new UmsResource();
        umsResource.setId(1L);

        when(redisTemplate.opsForValue().get(any())).thenReturn(Collections.singletonList(umsResource));

        Assertions.assertNotNull(umsAdminCacheService.getResourceList(adminId));
    }

    @Test
    void setResourceList() {
        long adminId = 1L;

        UmsResource umsResource = new UmsResource();
        umsResource.setId(1L);

        Mockito.doNothing().when(umsAdminCacheService).setResourceList(any(), any());

        umsAdminCacheService.setResourceList(adminId, Collections.singletonList(umsResource));

        Mockito.verify(umsAdminCacheService, Mockito.times(1)).setResourceList(adminId, Collections.singletonList(umsResource));
    }
}