package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.dto.UmsMenuNode;
import com.volvo.mall.admin.mapper.UmsMenuMapper;
import com.volvo.mall.admin.model.UmsMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UmsMenuServiceImplTest {
    @Mock
    UmsMenuMapper menuMapper;

    @Spy
    @InjectMocks
    UmsMenuServiceImpl umsMenuService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 创建后台菜单成功
     * 1. 有parent
     * 2. 没有parent
     **/
    @Test
    void testCreate() {
        UmsMenu umsMenu0 = new UmsMenu();
        umsMenu0.setId(1L);
        umsMenu0.setParentId(0L);
        umsMenu0.setLevel(1);

        UmsMenu umsMenu1 = new UmsMenu();
        umsMenu1.setId(2L);
        umsMenu1.setParentId(1L);

        when(menuMapper.insert(any())).thenReturn(1);
        when(menuMapper.selectByPrimaryKey(any())).thenReturn(umsMenu0);

        Assertions.assertEquals(1, umsMenuService.create(umsMenu0));
    }

    /**
     * 更新后台菜单成功
     **/
    @Test
    void testUpdate() {
        UmsMenu umsMenu0 = new UmsMenu();
        umsMenu0.setId(1L);
        umsMenu0.setParentId(0L);
        umsMenu0.setLevel(1);

        when(menuMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, menuMapper.updateByPrimaryKeySelective(umsMenu0));
    }

    /**
     * 获取后台菜单Item成功
     **/
    @Test
    void testGetItem() {
        Long id = 1L;

        UmsMenu umsMenu0 = new UmsMenu();
        umsMenu0.setId(1L);
        umsMenu0.setParentId(0L);
        umsMenu0.setLevel(1);

        when(menuMapper.selectByPrimaryKey(any())).thenReturn(umsMenu0);

        Assertions.assertEquals(umsMenu0, umsMenuService.getItem(id));
    }

    /**
     * 删除后台菜单成功
     **/
    @Test
    void testDelete() {
        Long id = 1L;

        when(menuMapper.deleteByPrimaryKey(any())).thenReturn(1);

        Assertions.assertEquals(1, umsMenuService.delete(id));
    }

    /**
     * list后台菜单成功
     **/
    @Test
    void testList() {
        UmsMenu umsMenu0 = new UmsMenu();
        umsMenu0.setId(1L);
        umsMenu0.setParentId(0L);
        umsMenu0.setLevel(1);

        when(menuMapper.selectByExample(any())).thenReturn(Collections.singletonList(umsMenu0));

        Assertions.assertNotNull(umsMenuService.list(0L, 1, 5));
    }

    /**
     * 树状list后台菜单成功
     **/
    @Test
    void testTreeList() {
        UmsMenu umsMenu0 = new UmsMenu();
        umsMenu0.setId(1L);
        umsMenu0.setParentId(0L);
        umsMenu0.setLevel(1);

        UmsMenu umsMenu1 = new UmsMenu();
        umsMenu1.setId(2L);
        umsMenu1.setParentId(1L);

        List<UmsMenu> exceptedList = new ArrayList<>();
        exceptedList.add(umsMenu0);
        exceptedList.add(umsMenu1);

        when(menuMapper.selectByExample(any())).thenReturn(exceptedList);

        List<UmsMenuNode> umsMenuNodes = umsMenuService.treeList();
        System.out.println(umsMenuNodes);

        Assertions.assertNotNull(umsMenuNodes);
    }

    /**
     * 更新后台菜单隐藏性成功
     **/
    @Test
    void updateHidden() {
        Long id = 1L;

        Integer hidden = 0;

        when(menuMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, umsMenuService.updateHidden(id, hidden));
    }
}