package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.SmsHomeNewProductMapper;
import com.volvo.mall.admin.model.SmsHomeNewProduct;
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

class SmsHomeNewProductServiceImplTest {
    @Mock
    SmsHomeNewProductMapper homeNewProductMapper;

    @InjectMocks
    SmsHomeNewProductServiceImpl smsHomeNewProductService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        List<SmsHomeNewProduct> homeNewProductList = new ArrayList<>();
        SmsHomeNewProduct smsHomeNewProduct = new SmsHomeNewProduct();
        homeNewProductList.add(smsHomeNewProduct);

        Assertions.assertEquals(homeNewProductList.size(), smsHomeNewProductService.create(homeNewProductList));
    }

    @Test
    void testUpdateSort() {
        when(homeNewProductMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, smsHomeNewProductService.updateSort(1L, 1));
    }

    @Test
    void testDelete() {
        when(homeNewProductMapper.deleteByExample(any())).thenReturn(1);

        Assertions.assertEquals(1, smsHomeNewProductService.delete(Collections.singletonList(1L)));
    }

    @Test
    void testUpdateRecommendStatus() {
        when(homeNewProductMapper.updateByExampleSelective(any(), any())).thenReturn(1);

        Assertions.assertEquals(1, smsHomeNewProductService
                .updateRecommendStatus(Collections.singletonList(1L), 1));
    }

    @Test
    void testList() {
        List<SmsHomeNewProduct> exceptedList = new ArrayList<>();
        SmsHomeNewProduct smsHomeNewProduct = new SmsHomeNewProduct();
        exceptedList.add(smsHomeNewProduct);

        when(homeNewProductMapper.selectByExample(any())).thenReturn(exceptedList);

        Assertions.assertEquals(exceptedList,
                smsHomeNewProductService.list("test", 1, 5, 1));
    }
}