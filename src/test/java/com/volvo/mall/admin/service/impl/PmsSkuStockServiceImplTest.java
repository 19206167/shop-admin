package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.dao.PmsSkuStockDao;
import com.volvo.mall.admin.mapper.PmsSkuStockMapper;
import com.volvo.mall.admin.model.PmsSkuStock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PmsSkuStockServiceImplTest {
    @Mock
    private PmsSkuStockMapper skuStockMapper;
    @Mock
    private PmsSkuStockDao skuStockDao;

    @InjectMocks
    PmsSkuStockServiceImpl pmsSkuStockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getList() {
        PmsSkuStock pmsSkuStock = new PmsSkuStock();

        when(skuStockMapper.selectByExample(any())).thenReturn(Collections.singletonList(pmsSkuStock));

        Assertions.assertNotNull(pmsSkuStockService.getList(1L, "test"));
    }

    @Test
    void update() {
        PmsSkuStock pmsSkuStock = new PmsSkuStock();
        pmsSkuStock.setId(1L);

        List<PmsSkuStock> exceptedList = new ArrayList<>();
        exceptedList.add(pmsSkuStock);

        when(skuStockDao.replaceList(any())).thenReturn(1);

        Assertions.assertEquals(1, pmsSkuStockService.update(1L, exceptedList));
    }
}