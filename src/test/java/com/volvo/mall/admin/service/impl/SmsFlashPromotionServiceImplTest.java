package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.SmsFlashPromotionMapper;
import com.volvo.mall.admin.model.SmsFlashPromotion;
import com.volvo.mall.admin.model.SmsFlashPromotionProductRelation;
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

class SmsFlashPromotionServiceImplTest {
    @Mock
    SmsFlashPromotionMapper flashPromotionMapper;

    @InjectMocks
    SmsFlashPromotionServiceImpl smsFlashPromotionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        SmsFlashPromotion flashPromotion = new SmsFlashPromotion();

        when(flashPromotionMapper.insert(any())).thenReturn(1);

        Assertions.assertEquals(1, smsFlashPromotionService.create(flashPromotion));
    }

    @Test
    void testUpdate() {
        SmsFlashPromotion flashPromotion = new SmsFlashPromotion();

        when(flashPromotionMapper.updateByPrimaryKey(any())).thenReturn(1);

        Assertions.assertEquals(1, smsFlashPromotionService.update(1L, flashPromotion));
    }

    @Test
    void testDelete() {
        when(flashPromotionMapper.deleteByPrimaryKey(any())).thenReturn(1);

        Assertions.assertEquals(1, smsFlashPromotionService.delete(1L));
    }

    @Test
    void testUpdateStatus() {
        when(flashPromotionMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, smsFlashPromotionService.updateStatus(1L, 1));
    }

    @Test
    void testGetItem() {
        SmsFlashPromotion flashPromotion = new SmsFlashPromotion();

        when(flashPromotionMapper.selectByPrimaryKey(any())).thenReturn(flashPromotion);

        Assertions.assertEquals(flashPromotion, smsFlashPromotionService.getItem(1L));
    }

    @Test
    void testList() {
        SmsFlashPromotion flashPromotion = new SmsFlashPromotion();

        when(flashPromotionMapper.selectByExample(any())).thenReturn(Collections.singletonList(flashPromotion));

        Assertions.assertNotNull(smsFlashPromotionService.list("test", 1, 5));
    }
}