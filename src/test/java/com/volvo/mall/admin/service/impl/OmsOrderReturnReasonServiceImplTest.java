package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.OmsOrderReturnReasonMapper;
import com.volvo.mall.admin.model.OmsOrderReturnReason;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OmsOrderReturnReasonServiceImplTest {
    @Mock
    OmsOrderReturnReasonMapper returnReasonMapper;

    @Spy
    @InjectMocks
    OmsOrderReturnReasonServiceImpl omsOrderReturnReasonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 测试创建退货原因
     **/
    @Test
    void testCreate() {
        OmsOrderReturnReason returnReason = new OmsOrderReturnReason();
        returnReason.setId(1L);

        when(returnReasonMapper.insert(any())).thenReturn(1);

        Assertions.assertEquals(1, omsOrderReturnReasonService.create(returnReason));
    }

    /**
     * 测试更新退货原因
     **/
    @Test
    void testUpdate() {
        OmsOrderReturnReason returnReason = new OmsOrderReturnReason();
        returnReason.setId(1L);

        when(returnReasonMapper.updateByPrimaryKey(any())).thenReturn(1);

        Assertions.assertEquals(1, omsOrderReturnReasonService.update(1L, returnReason));
    }

    /**
     * 测试删除退货原因
     **/
    @Test
    void testDelete() {
        OmsOrderReturnReason returnReason = new OmsOrderReturnReason();
        returnReason.setId(1L);

        when(returnReasonMapper.deleteByExample(any())).thenReturn(1);

        Assertions.assertEquals(1, omsOrderReturnReasonService.delete(Collections.singletonList(1L)));
    }

    /**
     * 测试展示退货原因
     **/
    @Test
    void testList() {
        OmsOrderReturnReason returnReason = new OmsOrderReturnReason();
        returnReason.setId(1L);

        when(returnReasonMapper.selectByExample(any())).thenReturn(Collections.singletonList(returnReason));

        Assertions.assertEquals(1, omsOrderReturnReasonService.list(1, 5).size());
    }

    /**
     * 测试更新退货状态
     **/
    @Test
    void testUpdateStatus() {
        Long id = 1L;
        Integer status = 1;

        when(returnReasonMapper.updateByExampleSelective(any(), any())).thenReturn(1);

        Assertions.assertEquals(1, omsOrderReturnReasonService
                .updateStatus(Collections.singletonList(id), status));
    }

    /**
     * 测试根据id获取退货原因
     **/
    @Test
    void testGetItem() {
        OmsOrderReturnReason returnReason = new OmsOrderReturnReason();
        returnReason.setId(1L);

        when(returnReasonMapper.selectByPrimaryKey(any())).thenReturn(returnReason);

        Assertions.assertEquals(returnReason, omsOrderReturnReasonService.getItem(1L));
    }
}