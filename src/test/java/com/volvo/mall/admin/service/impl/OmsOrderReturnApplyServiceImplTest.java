package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.dao.OmsOrderReturnApplyDao;
import com.volvo.mall.admin.dto.OmsOrderReturnApplyResult;
import com.volvo.mall.admin.dto.OmsReturnApplyQueryParam;
import com.volvo.mall.admin.dto.OmsUpdateStatusParam;
import com.volvo.mall.admin.mapper.OmsOrderReturnApplyMapper;
import com.volvo.mall.admin.model.OmsOrderReturnApply;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OmsOrderReturnApplyServiceImplTest {

    @Mock
    OmsOrderReturnApplyDao returnApplyDao;

    @Mock
    OmsOrderReturnApplyMapper returnApplyMapper;

    @Spy
    @InjectMocks
    OmsOrderReturnApplyServiceImpl omsOrderReturnApplyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 测试展示所有退货订单成功
     **/
    @Test
    void testList() {
        OmsReturnApplyQueryParam omsReturnApplyQueryParam = new OmsReturnApplyQueryParam();
        omsReturnApplyQueryParam.setId(1L);

        OmsOrderReturnApply omsOrderReturnApply = new OmsOrderReturnApply();
        omsOrderReturnApply.setId(1L);

        List<OmsOrderReturnApply> exceptedList = new ArrayList<>();
        exceptedList.add(omsOrderReturnApply);

        when(returnApplyDao.getList(any())).thenReturn(exceptedList);

        Assertions.assertEquals(1, omsOrderReturnApplyService.list(omsReturnApplyQueryParam, 1, 5).size());
    }

    /**
     * 测试根据id删除退货订单
     **/
    @Test
    void testDelete() {
        List<Long> ids = new ArrayList<>();

        for (long i = 0; i < 5L; i++) {
            ids.add(i);
        }

        when(returnApplyMapper.deleteByExample(any())).thenReturn(ids.size());

        Assertions.assertEquals(ids.size(), omsOrderReturnApplyService.delete(ids));
    }

    /**
     * 测试更新退货订单状态 1
     **/
    @Test
    void testUpdateStatus1() {
        OmsUpdateStatusParam statusParam = new OmsUpdateStatusParam();
        statusParam.setStatus(1);

        when(returnApplyMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, omsOrderReturnApplyService.updateStatus(1L, statusParam));
    }

    /**
     * 测试更新退货订单状态 2
     **/
    @Test
    void testUpdateStatus2() {
        OmsUpdateStatusParam statusParam = new OmsUpdateStatusParam();
        statusParam.setStatus(2);

        when(returnApplyMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, omsOrderReturnApplyService.updateStatus(1L, statusParam));
    }

    /**
     * 测试更新退货订单状态 3
     **/
    @Test
    void testUpdateStatus3() {
        OmsUpdateStatusParam statusParam = new OmsUpdateStatusParam();
        statusParam.setStatus(3);

        when(returnApplyMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, omsOrderReturnApplyService.updateStatus(1L, statusParam));
    }

    /**
     * 测试根据id获取退货订单
     **/
    @Test
    void testGetItem() {
        Long id = 1L;

        OmsOrderReturnApplyResult omsOrderReturnApplyResult = new OmsOrderReturnApplyResult();
        omsOrderReturnApplyResult.setId(1L);

        when(returnApplyDao.getDetail(any())).thenReturn(omsOrderReturnApplyResult);

        Assertions.assertEquals(id, omsOrderReturnApplyService.getItem(id).getId());
    }
}