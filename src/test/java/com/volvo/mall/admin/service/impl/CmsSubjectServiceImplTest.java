package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.CmsSubjectMapper;
import com.volvo.mall.admin.model.CmsSubject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CmsSubjectServiceImplTest {

    @Mock
    CmsSubjectMapper subjectMapper;

    @Spy
    @InjectMocks
    CmsSubjectServiceImpl cmsSubjectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * list所有商品专题
     **/
    @Test
    void testListAll() {
        CmsSubject cmsSubject = new CmsSubject();
        cmsSubject.setId(1L);

        List<CmsSubject> exceptedList = new ArrayList<>();
        exceptedList.add(cmsSubject);

        when(subjectMapper.selectByExample(any())).thenReturn(exceptedList);

        Assertions.assertNotNull(cmsSubjectService.list("test", 1, 5));
    }

    /**
     * 根据关键词，list商品专题
     **/
    @Test
    void testList() {
        CmsSubject cmsSubject = new CmsSubject();
        cmsSubject.setId(1L);

        List<CmsSubject> exceptedList = new ArrayList<>();
        exceptedList.add(cmsSubject);

        when(subjectMapper.selectByExample(any())).thenReturn(exceptedList);

        Assertions.assertNotNull(cmsSubjectService.list("test", 1, 5));
    }
}