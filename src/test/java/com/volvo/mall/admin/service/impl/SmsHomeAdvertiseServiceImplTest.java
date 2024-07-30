package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.SmsHomeAdvertiseMapper;
import com.volvo.mall.admin.model.SmsHomeAdvertise;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SmsHomeAdvertiseServiceImplTest {
    @Mock
    SmsHomeAdvertiseMapper advertiseMapper;

    @InjectMocks
    SmsHomeAdvertiseServiceImpl smsHomeAdvertiseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        SmsHomeAdvertise advertise = new SmsHomeAdvertise();

        when(advertiseMapper.insert(any())).thenReturn(1);

        Assertions.assertEquals(1, smsHomeAdvertiseService.create(advertise));
    }

    @Test
    void testDelete() {
        when(advertiseMapper.deleteByExample(any())).thenReturn(1);

        Assertions.assertEquals(1, smsHomeAdvertiseService.delete(Collections.singletonList(1L)));
    }

    @Test
    void testUpdateStatus() {
        when(advertiseMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, smsHomeAdvertiseService.updateStatus(1L, 1));
    }

    @Test
    void testGetItem() {
        SmsHomeAdvertise smsHomeAdvertise = new SmsHomeAdvertise();
        when(advertiseMapper.selectByPrimaryKey(any())).thenReturn(smsHomeAdvertise);

        Assertions.assertEquals(smsHomeAdvertise, smsHomeAdvertiseService.getItem(1L));
    }

    @Test
    void testUpdate() {
        SmsHomeAdvertise smsHomeAdvertise = new SmsHomeAdvertise();

        when(advertiseMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, smsHomeAdvertiseService.update(1L, smsHomeAdvertise));
    }

    @Test
    void testList() {
        SmsHomeAdvertise smsHomeAdvertise = new SmsHomeAdvertise();

        when(advertiseMapper.selectByExample(any())).thenReturn(Collections.singletonList(smsHomeAdvertise));

        Assertions.assertNotNull(smsHomeAdvertiseService.list("Test", 1," 2024-7-29", 1, 5));
    }
}