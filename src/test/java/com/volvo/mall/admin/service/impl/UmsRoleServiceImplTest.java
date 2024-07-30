package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.dao.UmsRoleDao;
import com.volvo.mall.admin.mapper.UmsRoleMapper;
import com.volvo.mall.admin.mapper.UmsRoleMenuRelationMapper;
import com.volvo.mall.admin.mapper.UmsRoleResourceRelationMapper;
import com.volvo.mall.admin.model.UmsMenu;
import com.volvo.mall.admin.model.UmsResource;
import com.volvo.mall.admin.model.UmsRole;
import com.volvo.mall.admin.service.UmsAdminCacheService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UmsRoleServiceImplTest {

    @Mock
    UmsRoleMapper roleMapper;

    @Mock
    UmsRoleMenuRelationMapper roleMenuRelationMapper;

    @Mock
    UmsRoleResourceRelationMapper roleResourceRelationMapper;

    @Mock
    UmsRoleDao roleDao;

    @Mock
    UmsAdminCacheService adminCacheService;

    @Autowired
    RedisTemplate redisTemplate;

    @Spy
    @InjectMocks
    UmsRoleServiceImpl umsRoleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 创建后台角色成功
     **/
    @Test
    void testCreate() {
        UmsRole role = new UmsRole();
        role.setId(1L);

        when(roleMapper.insert(any())).thenReturn(1);

        Assertions.assertEquals(1, umsRoleService.create(role));
    }

    /**
     * 创建后台角色成功
     **/
    @Test
    void testUpdate() {
        Long id = 1L;

        UmsRole role = new UmsRole();
        role.setId(id);

        when(roleMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, umsRoleService.update(id, role));
    }

    /**
     * 创建后台角色成功
     **/
    @Test
    void testDelete() {
        Long id = 1L;

        when(roleMapper.deleteByExample(any())).thenReturn(1);

        Assertions.assertEquals(1, umsRoleService.delete(Collections.singletonList(id)));
    }

    /**
     * 创建后台角色成功
     **/
    @Test
    void testList() {
        UmsRole role = new UmsRole();
        role.setId(1L);

        List<UmsRole> exceptedList = new ArrayList<>();
        exceptedList.add(role);

        when(roleMapper.selectByExample(any())).thenReturn(exceptedList);

        Assertions.assertEquals(1, umsRoleService.list("test", 1, 5).size());
    }

    /**
     * 根据adminId 获取menuList
     **/
    @Test
    void testGetMenuList() {
        Long id = 1L;

        List<UmsMenu> exceptedList = new ArrayList<>();

        UmsMenu umsMenu = new UmsMenu();
        umsMenu.setId(id);
        exceptedList.add(umsMenu);

        when(roleDao.getMenuList(any())).thenReturn(exceptedList);

        Assertions.assertEquals(1, umsRoleService.getMenuList(id).size());
    }

    /**
     * 根据roleId 获取menuList
     **/
    @Test
    void testListMenu() {
        Long id = 1L;

        List<UmsMenu> exceptedList = new ArrayList<>();

        UmsMenu umsMenu = new UmsMenu();
        umsMenu.setId(id);
        exceptedList.add(umsMenu);

        when(roleDao.getMenuList(any())).thenReturn(exceptedList);

        Assertions.assertEquals(1, umsRoleService.getMenuList(id).size());
    }

    /**
     * list resource成功
     **/
    @Test
    void testListResource() {
        Long id = 1L;

        List<UmsResource> exceptedList = new ArrayList<>();

        UmsResource umsResource = new UmsResource();
        umsResource.setId(id);
        exceptedList.add(umsResource);

        when(roleDao.getResourceListByRoleId(any())).thenReturn(exceptedList);

        Assertions.assertEquals(1, umsRoleService.listResource(id).size());
    }

    /**
     * 分配menu
     **/
    @Test
    void testAllocMenu() {
        Long id = 1L;

        List<Long> ids = new ArrayList<>();
        for (long i = 1; i < 5; i++) {
            ids.add(i);
        }

        Assertions.assertEquals(ids.size(), umsRoleService.allocMenu(id, ids));

    }

    /**
     * 分配resource
     **/
    @Test
    void testAllocResource() {
        Long id = 1L;

        List<Long> ids = new ArrayList<>();
        for (long i = 1; i < 5; i++) {
            ids.add(i);
        }

        Assertions.assertEquals(ids.size(), umsRoleService.allocResource(id, ids));
    }
}