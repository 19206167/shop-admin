package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.dao.*;
import com.volvo.mall.admin.dto.PmsProductParam;
import com.volvo.mall.admin.dto.PmsProductQueryParam;
import com.volvo.mall.admin.dto.PmsProductResult;
import com.volvo.mall.admin.mapper.*;
import com.volvo.mall.admin.model.PmsProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PmsProductServiceImplTest {

    @InjectMocks
    PmsProductServiceImpl pmsProductService;
    @Mock
    private PmsProductMapper productMapper;
    @Mock
    private PmsMemberPriceDao memberPriceDao;
    @Mock
    private PmsMemberPriceMapper memberPriceMapper;
    @Mock
    private PmsProductLadderDao productLadderDao;
    @Mock
    private PmsProductLadderMapper productLadderMapper;
    @Mock
    private PmsProductFullReductionDao productFullReductionDao;
    @Mock
    private PmsProductFullReductionMapper productFullReductionMapper;
    @Mock
    private PmsSkuStockDao skuStockDao;
    @Mock
    private PmsSkuStockMapper skuStockMapper;
    @Mock
    private PmsProductAttributeValueDao productAttributeValueDao;
    @Mock
    private PmsProductAttributeValueMapper productAttributeValueMapper;
    @Mock
    private CmsSubjectProductRelationDao subjectProductRelationDao;
    @Mock
    private CmsSubjectProductRelationMapper subjectProductRelationMapper;
    @Mock
    private CmsPreferenceAreaProductRelationDao prefrenceAreaProductRelationDao;
    @Mock
    private CmsPrefrenceAreaProductRelationMapper prefrenceAreaProductRelationMapper;
    @Mock
    private PmsProductDao productDao;
    @Mock
    private PmsProductVertifyRecordDao productVertifyRecordDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        PmsProductParam productParam = new PmsProductParam();

        pmsProductService.create(productParam);

        Assertions.assertEquals(1, pmsProductService.create(productParam));
    }

    @Test
    void testGetUpdateInfo() {
        PmsProductResult pmsProductResult = new PmsProductResult();
        pmsProductResult.setId(1L);

        when(productDao.getUpdateInfo(any())).thenReturn(pmsProductResult);

        Assertions.assertEquals(pmsProductResult, pmsProductService.getUpdateInfo(1L));
    }

    @Test
    void testUpdate() {
        PmsProductParam productParam = new PmsProductParam();

        Assertions.assertEquals(1, pmsProductService.update(1L, productParam));
    }

    @Test
    void testList0() {
        PmsProductQueryParam productQueryParam = new PmsProductQueryParam();

        PmsProduct product = new PmsProduct();

        when(productMapper.selectByExample(any())).thenReturn(Collections.singletonList(product));

        Assertions.assertNotNull(pmsProductService.list(productQueryParam, 1, 5));
    }

    @Test
    void updateVerifyStatus() {
        when(productMapper.updateByExampleSelective(any(), any())).thenReturn(1);

        Assertions.assertEquals(1, pmsProductService.updateVerifyStatus(Collections.singletonList(1L), 1, "test"));
    }

    @Test
    void updatePublishStatus() {
        when(productMapper.updateByExampleSelective(any(), any())).thenReturn(1);

        Assertions.assertEquals(1, pmsProductService.updatePublishStatus(Collections.singletonList(1L), 1));
    }

    @Test
    void updateRecommendStatus() {
        when(productMapper.updateByExampleSelective(any(), any())).thenReturn(1);

        Assertions.assertEquals(1, pmsProductService.updateRecommendStatus(Collections.singletonList(1L), 1));
    }

    @Test
    void updateNewStatus() {
        when(productMapper.updateByExampleSelective(any(), any())).thenReturn(1);

        Assertions.assertEquals(1, pmsProductService.updateNewStatus(Collections.singletonList(1L), 1));
    }

    @Test
    void updateDeleteStatus() {
        when(productMapper.updateByExampleSelective(any(), any())).thenReturn(1);

        Assertions.assertEquals(1, pmsProductService.updateDeleteStatus(Collections.singletonList(1L), 1));
    }

    @Test
    void testList1() {
        PmsProduct product = new PmsProduct();

        when(productMapper.selectByExample(any())).thenReturn(Collections.singletonList(product));

        Assertions.assertNotNull(pmsProductService.list("test"));
    }
}