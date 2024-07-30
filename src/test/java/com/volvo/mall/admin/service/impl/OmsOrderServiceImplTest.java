package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.dao.OmsOrderDao;
import com.volvo.mall.admin.dao.OmsOrderOperateHistoryDao;
import com.volvo.mall.admin.dto.*;
import com.volvo.mall.admin.mapper.OmsOrderMapper;
import com.volvo.mall.admin.mapper.OmsOrderOperateHistoryMapper;
import com.volvo.mall.admin.model.OmsOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OmsOrderServiceImplTest {

    @Mock
    OmsOrderMapper orderMapper;

    @Mock
    OmsOrderDao orderDao;

    @Mock
    OmsOrderOperateHistoryDao orderOperateHistoryDao;

    @Mock
    OmsOrderOperateHistoryMapper orderOperateHistoryMapper;

    @Spy
    @InjectMocks
    OmsOrderServiceImpl omsOrderService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 测试list所有order成功
     **/
    @Test
    void testList() {
        OmsOrder omsOrder = new OmsOrder();
        omsOrder.setId(1L);

        when(orderDao.getList(any())).thenReturn(Collections.singletonList(omsOrder));

        OmsOrderQueryParam omsOrderQueryParam = new OmsOrderQueryParam();
        omsOrderQueryParam.setOrderSn("test");

        Assertions.assertEquals(1, omsOrderService.list(omsOrderQueryParam, 1, 5).size());
    }

    /**
     * 测试发货 成功
     **/
    @Test
    void testDelivery() {
        OmsOrderDeliveryParam omsOrderDeliveryParam = new OmsOrderDeliveryParam();
        omsOrderDeliveryParam.setOrderId(1L);

        when(orderDao.delivery(any())).thenReturn(1);

        Assertions.assertEquals(1, omsOrderService.delivery(Collections.singletonList(omsOrderDeliveryParam)));
    }

    /**
     * 测试关闭订单 成功
     **/
    @Test
    void testClose() {
        Long count = 5L;
        
        List<Long> ids = new ArrayList<>();
        for (long i = 1; i <= count; i++) {
            ids.add(i);
        }

        when(orderMapper.updateByExampleSelective(any(), any())).thenReturn(ids.size());

        Assertions.assertEquals(count, omsOrderService.close(ids, "test"));
    }

    /**
     * 测试删除订单 成功
     **/
    @Test
    void testDelete() {
        Long count = 5L;

        List<Long> ids = new ArrayList<>();
        for (long i = 1; i <= count; i++) {
            ids.add(i);
        }

        when(orderMapper.updateByExampleSelective(any(), any())).thenReturn(ids.size());

        Assertions.assertEquals(count, omsOrderService.delete(ids));
    }

    /**
     * 测试展示订单细节 成功
     **/
    @Test
    void testDetail() {
        Long id = 1L;
        OmsOrderDetail omsOrderDetail = new OmsOrderDetail();
        omsOrderDetail.setId(id);

        when(orderDao.getDetail(any())).thenReturn(omsOrderDetail);

        Assertions.assertEquals(omsOrderDetail, omsOrderService.detail(id));
    }

    /**
     * 测试修改收货人信息 成功
     **/
    @Test
    void testUpdateReceiverInfo() {
        OmsReceiverInfoParam omsReceiverInfoParam = new OmsReceiverInfoParam();
        omsReceiverInfoParam.setReceiverName("test");

        when(orderMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, omsOrderService.updateReceiverInfo(omsReceiverInfoParam));
    }

    /**
     * 测试修改money信息 成功
     **/
    @Test
    void testUpdateMoneyInfo() {
        OmsMoneyInfoParam moneyInfoParam = new OmsMoneyInfoParam();
        moneyInfoParam.setOrderId(1L);

        when(orderMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, omsOrderService.updateMoneyInfo(moneyInfoParam));
    }

    /**
     * 测试修改note信息 成功
     **/
    @Test
    void testUpdateNote() {
        Long id = 1L;
        String note = "test";
        Integer status = 1;

        when(orderMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, omsOrderService.updateNote(id, note, status));
    }
}