package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.SmsCouponHistoryMapper;
import com.volvo.mall.admin.model.SmsCouponHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SmsCouponHistoryServiceImplTest {
    @Mock
    private SmsCouponHistoryMapper historyMapper;

    @InjectMocks
    SmsCouponHistoryServiceImpl smsCouponHistoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void list() {
        SmsCouponHistory smsCouponHistory = new SmsCouponHistory();

        when(historyMapper.selectByExample(any())).thenReturn(Collections.singletonList(smsCouponHistory));

        assertNotNull(smsCouponHistoryService.list(1L, 1, "test", 1, 5));
    }
}