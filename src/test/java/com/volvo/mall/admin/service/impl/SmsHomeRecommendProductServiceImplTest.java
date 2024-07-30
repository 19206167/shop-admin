package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.SmsHomeRecommendProductMapper;
import com.volvo.mall.admin.model.SmsHomeRecommendProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SmsHomeRecommendProductServiceImplTest {
    @Mock
    SmsHomeRecommendProductMapper recommendProductMapper;

    @InjectMocks
    SmsHomeRecommendProductServiceImpl smsHomeRecommendProductService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        List<SmsHomeRecommendProduct> homeRecommendProductList = new ArrayList<>();
        SmsHomeRecommendProduct smsHomeRecommendProduct = new SmsHomeRecommendProduct();
        homeRecommendProductList.add(smsHomeRecommendProduct);

        Assertions.assertEquals(homeRecommendProductList.size(),
                smsHomeRecommendProductService.create(homeRecommendProductList));
    }

    @Test
    void testUpdateSort() {
        when(recommendProductMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, smsHomeRecommendProductService.updateSort(1L, 1));
    }

    @Test
    void testDelete() {
        when(recommendProductMapper.deleteByExample(any())).thenReturn(1);

        Assertions.assertEquals(1,
                smsHomeRecommendProductService.delete(Collections.singletonList(1L)));
    }

    @Test
    void testUpdateRecommendStatus() {
        when(recommendProductMapper.updateByExampleSelective(any(), any())).thenReturn(1);

        Assertions.assertEquals(1,
                smsHomeRecommendProductService.updateRecommendStatus(Collections.singletonList(1L), 1));
    }

    @Test
    void testList() {
        List<SmsHomeRecommendProduct> exceptedList = new ArrayList<>();
        SmsHomeRecommendProduct smsHomeRecommendProduct = new SmsHomeRecommendProduct();
        exceptedList.add(smsHomeRecommendProduct);

        when(recommendProductMapper.selectByExample(any())).thenReturn(exceptedList);

        Assertions.assertNotNull(smsHomeRecommendProductService.list("test", 1, 5, 1));
    }
}