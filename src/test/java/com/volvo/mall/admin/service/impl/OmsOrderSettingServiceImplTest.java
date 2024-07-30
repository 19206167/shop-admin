package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.OmsOrderSettingMapper;
import com.volvo.mall.admin.model.OmsOrderSetting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OmsOrderSettingServiceImplTest {
    @Mock
    OmsOrderSettingMapper orderSettingMapper;

    @Spy
    @InjectMocks
    OmsOrderSettingServiceImpl omsOrderSettingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 测试获取OrderSetting item 成功
     **/
    @Test
    void testGetItem() {
        Long id = 1L;
        OmsOrderSetting orderSetting = new OmsOrderSetting();
        orderSetting.setId(1L);

        when(orderSettingMapper.selectByPrimaryKey(any())).thenReturn(orderSetting);

        Assertions.assertEquals(orderSetting, omsOrderSettingService.getItem(id));
    }

    /**
     * 测试更新 OrderSetting 成功
     **/
    @Test
    void testUpdate() {
        Long id = 1L;
        OmsOrderSetting orderSetting = new OmsOrderSetting();
        orderSetting.setId(1L);

        when(orderSettingMapper.updateByPrimaryKey(any())).thenReturn(1);

        Assertions.assertEquals(1, omsOrderSettingService.update(id, orderSetting));
    }
}