package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.SmsHomeRecommendSubjectMapper;
import com.volvo.mall.admin.model.SmsHomeRecommendSubject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SmsHomeRecommendSubjectServiceImplTest {
    @Mock
    SmsHomeRecommendSubjectMapper smsHomeRecommendSubjectMapper;

    @InjectMocks
    SmsHomeRecommendSubjectServiceImpl smsHomeRecommendSubjectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        List<SmsHomeRecommendSubject> recommendSubjectList = new ArrayList<>();
        SmsHomeRecommendSubject smsHomeRecommendSubject = new SmsHomeRecommendSubject();
        recommendSubjectList.add(smsHomeRecommendSubject);

        Assertions.assertEquals(recommendSubjectList.size(), smsHomeRecommendSubjectService.create(recommendSubjectList));
    }

    @Test
    void testUpdateSort() {
        when(smsHomeRecommendSubjectMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        Assertions.assertEquals(1, smsHomeRecommendSubjectService.updateSort(1L, 1));
    }

    @Test
    void testDelete() {
        when(smsHomeRecommendSubjectMapper.deleteByExample(any())).thenReturn(1);

        Assertions.assertEquals(1, smsHomeRecommendSubjectService.delete(Collections.singletonList(1L)));
    }

    @Test
    void testUpdateRecommendStatus() {
        when(smsHomeRecommendSubjectMapper.updateByExampleSelective(any(), any())).thenReturn(1);

        Assertions.assertEquals(1,
                smsHomeRecommendSubjectService.updateRecommendStatus(Collections.singletonList(1L), 1));
    }

    @Test
    void testList() {
        SmsHomeRecommendSubject smsHomeRecommendSubject = new SmsHomeRecommendSubject();

        when(smsHomeRecommendSubjectMapper.selectByExample(any())).thenReturn(Collections.singletonList(smsHomeRecommendSubject));

        Assertions.assertNotNull(smsHomeRecommendSubjectService.list("test", 1, 5, 1));
    }
}