package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.dao.SmsFlashPromotionProductRelationDao;
import com.volvo.mall.admin.dto.SmsFlashPromotionProduct;
import com.volvo.mall.admin.mapper.SmsFlashPromotionProductRelationMapper;
import com.volvo.mall.admin.model.SmsFlashPromotionProductRelation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SmsFlashPromotionProductRelationServiceImplTest {

    @Mock
    private SmsFlashPromotionProductRelationMapper relationMapper;
    @Mock
    private SmsFlashPromotionProductRelationDao relationDao;

    @InjectMocks
    SmsFlashPromotionProductRelationServiceImpl smsFlashPromotionProductRelationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        List<SmsFlashPromotionProductRelation> relationList = new ArrayList<>();
        SmsFlashPromotionProductRelation smsFlashPromotionProductRelation =
                new SmsFlashPromotionProductRelation();
        smsFlashPromotionProductRelation.setId(1L);
        relationList.add(smsFlashPromotionProductRelation);

        Assertions.assertEquals(relationList.size(), smsFlashPromotionProductRelationService.create(relationList));
    }

    @Test
    void testUpdate() {
        SmsFlashPromotionProductRelation smsFlashPromotionProductRelation =
                new SmsFlashPromotionProductRelation();
        smsFlashPromotionProductRelation.setId(1L);

        when(relationMapper.updateByPrimaryKey(any())).thenReturn(1);

        Assertions.assertEquals(1, smsFlashPromotionProductRelationService
                .update(1L, smsFlashPromotionProductRelation));
    }

    @Test
    void testDelete() {
        when(relationMapper.deleteByPrimaryKey(any())).thenReturn(1);

        Assertions.assertEquals(1, smsFlashPromotionProductRelationService.delete(1L));
    }

    @Test
    void testGetItem() {
        SmsFlashPromotionProductRelation smsFlashPromotionProductRelation =
                new SmsFlashPromotionProductRelation();
        smsFlashPromotionProductRelation.setId(1L);

        when(relationMapper.selectByPrimaryKey(any())).thenReturn(smsFlashPromotionProductRelation);

        Assertions.assertEquals(smsFlashPromotionProductRelation,
                smsFlashPromotionProductRelationService.getItem(1L));
    }

    @Test
    void testList() {
        List<SmsFlashPromotionProduct> exceptedList = new ArrayList<>();
        SmsFlashPromotionProduct smsFlashPromotionProduct = new SmsFlashPromotionProduct();
        smsFlashPromotionProduct.setId(1L);
        exceptedList.add(smsFlashPromotionProduct);

        when(relationDao.getList(any(), any())).thenReturn(exceptedList);

        Assertions.assertNotNull(smsFlashPromotionProductRelationService
                .list(1L, 1L, 1, 5));
    }

    @Test
    void testGetCount() {
        when(relationMapper.countByExample(any())).thenReturn(1L);

        Assertions.assertEquals(1L, smsFlashPromotionProductRelationService
                .getCount(1L, 1L));
    }
}