package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.dto.SmsFlashPromotionSessionDetail;
import com.volvo.mall.admin.mapper.SmsFlashPromotionSessionMapper;
import com.volvo.mall.admin.model.SmsFlashPromotionSession;
import com.volvo.mall.admin.service.SmsFlashPromotionProductRelationService;
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

class SmsFlashPromotionSessionServiceImplTest {
    @Mock
    private SmsFlashPromotionSessionMapper promotionSessionMapper;
    @Mock
    private SmsFlashPromotionProductRelationService relationService;

    @InjectMocks
    SmsFlashPromotionSessionServiceImpl smsFlashPromotionSessionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        SmsFlashPromotionSession promotionSession = new SmsFlashPromotionSession();
        promotionSession.setId(1L);

        when(promotionSessionMapper.insert(any())).thenReturn(1);

        Assertions.assertEquals(1, smsFlashPromotionSessionService.create(promotionSession));
    }

    @Test
    void testUpdate() {
        SmsFlashPromotionSession promotionSession = new SmsFlashPromotionSession();
        promotionSession.setId(1L);

        when(promotionSessionMapper.updateByPrimaryKey(any())).thenReturn(1);

        Assertions.assertEquals(1, smsFlashPromotionSessionService.update(1L, promotionSession));
    }

    @Test
    void testUpdateStatus() {
        when(promotionSessionMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, smsFlashPromotionSessionService.updateStatus(1L, 1));
    }

    @Test
    void testDelete() {
        when(promotionSessionMapper.deleteByPrimaryKey(any())).thenReturn(1);

        Assertions.assertEquals(1, smsFlashPromotionSessionService.delete(1L));
    }

    @Test
    void testGetItem() {
        SmsFlashPromotionSession smsFlashPromotionSession = new SmsFlashPromotionSession();

        when(promotionSessionMapper.selectByPrimaryKey(any())).thenReturn(smsFlashPromotionSession);

        Assertions.assertEquals(smsFlashPromotionSession, smsFlashPromotionSession);
    }

    @Test
    void testList() {
        List<SmsFlashPromotionSession> exceptedList = new ArrayList<>();

        SmsFlashPromotionSession smsFlashPromotionSession = new SmsFlashPromotionSession();
        exceptedList.add(smsFlashPromotionSession);

        when(promotionSessionMapper.selectByExample(any())).thenReturn(exceptedList);

        Assertions.assertNotNull(smsFlashPromotionSessionService.list());
    }

    @Test
    void testSelectList() {
        List<SmsFlashPromotionSession> exceptedList = new ArrayList<>();

        SmsFlashPromotionSession smsFlashPromotionSession = new SmsFlashPromotionSession();
        smsFlashPromotionSession.setId(1L);
        exceptedList.add(smsFlashPromotionSession);

        when(promotionSessionMapper.selectByExample(any())).thenReturn(exceptedList);

        Assertions.assertNotNull(smsFlashPromotionSessionService.selectList(1L));
    }
}