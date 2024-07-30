package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.dao.SmsCouponDao;
import com.volvo.mall.admin.dao.SmsCouponProductCategoryRelationDao;
import com.volvo.mall.admin.dao.SmsCouponProductRelationDao;
import com.volvo.mall.admin.dto.SmsCouponParam;
import com.volvo.mall.admin.mapper.SmsCouponMapper;
import com.volvo.mall.admin.mapper.SmsCouponProductCategoryRelationMapper;
import com.volvo.mall.admin.mapper.SmsCouponProductRelationMapper;
import com.volvo.mall.admin.model.SmsCoupon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SmsCouponServiceImplTest {

    @Mock
    private SmsCouponMapper couponMapper;
    @Mock
    private SmsCouponProductRelationMapper productRelationMapper;
    @Mock
    private SmsCouponProductCategoryRelationMapper productCategoryRelationMapper;
    @Mock
    private SmsCouponProductRelationDao productRelationDao;
    @Mock
    private SmsCouponProductCategoryRelationDao productCategoryRelationDao;
    @Mock
    private SmsCouponDao couponDao;

    @InjectMocks
    SmsCouponServiceImpl smsCouponService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        SmsCouponParam couponParam = new SmsCouponParam();
        couponParam.setUseType(0);

        when(couponMapper.insert(any())).thenReturn(1);

        Assertions.assertEquals(1, smsCouponService.create(couponParam));
    }

    @Test
    void testDelete() {
        when(couponMapper.deleteByPrimaryKey(any())).thenReturn(1);

        Assertions.assertEquals(1, smsCouponService.delete(1L));
    }

    @Test
    void testUpdate() {
        SmsCouponParam couponParam = new SmsCouponParam();
        couponParam.setUseType(0);

        when(couponMapper.updateByPrimaryKey(any())).thenReturn(1);

        Assertions.assertEquals(1, smsCouponService.update(1L, couponParam));
    }

    @Test
    void list() {
        List<SmsCoupon> exceptedList = new ArrayList<>();
        SmsCoupon smsCoupon = new SmsCoupon();

        exceptedList.add(smsCoupon);

        when(couponMapper.selectByExample(any())).thenReturn(exceptedList);

        Assertions.assertNotNull(smsCouponService.list("test", 1, 1, 5));
    }

    @Test
    void getItem() {
        SmsCouponParam couponParam = new SmsCouponParam();
        couponParam.setUseType(0);

        when(couponDao.getItem(any())).thenReturn(couponParam);

        Assertions.assertEquals(couponParam, smsCouponService.getItem(1L));
    }
}