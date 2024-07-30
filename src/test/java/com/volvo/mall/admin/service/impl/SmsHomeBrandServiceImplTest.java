package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.SmsHomeBrandMapper;
import com.volvo.mall.admin.model.SmsHomeBrand;
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

class SmsHomeBrandServiceImplTest {
    @Mock
    SmsHomeBrandMapper homeBrandMapper;

    @InjectMocks
    SmsHomeBrandServiceImpl smsHomeBrandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        List<SmsHomeBrand> homeBrandList = new ArrayList<>();
        SmsHomeBrand smsHomeBrand = new SmsHomeBrand();
        homeBrandList.add(smsHomeBrand);

        when(homeBrandMapper.insert(any())).thenReturn(0);
        Assertions.assertEquals(homeBrandList.size(), smsHomeBrandService.create(homeBrandList));
    }

    @Test
    void testUpdateSort() {
        when(homeBrandMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, smsHomeBrandService.updateSort(1L, 1));
    }

    @Test
    void testDelete() {
        when(homeBrandMapper.deleteByExample(any())).thenReturn(1);

        Assertions.assertEquals(1, smsHomeBrandService.delete(Collections.singletonList(1L)));
    }

    @Test
    void testUpdateRecommendStatus() {
        when(homeBrandMapper.updateByExampleSelective(any(), any())).thenReturn(1);

        Assertions.assertEquals(1, smsHomeBrandService
                .updateRecommendStatus(Collections.singletonList(1L), 1));
    }

    @Test
    void testList() {
        SmsHomeBrand smsHomeBrand = new SmsHomeBrand();

        when(homeBrandMapper.selectByExample(any())).thenReturn(Collections.singletonList(smsHomeBrand));

        Assertions.assertEquals(1, smsHomeBrandService.list("test", 1, 1, 5).size());
    }
}