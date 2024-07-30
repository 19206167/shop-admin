package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.CmsPrefrenceAreaMapper;
import com.volvo.mall.admin.model.CmsPrefrenceArea;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CmsPrefrenceAreaServiceImplTest {
    @Mock
    CmsPrefrenceAreaMapper prefrenceAreaMapper;

    @Spy
    @InjectMocks
    CmsPrefrenceAreaServiceImpl cmsPrefrenceAreaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * list所有优选商品
     **/
    @Test
    void testListAll() {
        CmsPrefrenceArea cmsPrefrenceArea = new CmsPrefrenceArea();
        cmsPrefrenceArea.setId(1L);

        List<CmsPrefrenceArea> exceptedList = new ArrayList<>();
        exceptedList.add(cmsPrefrenceArea);

        when(prefrenceAreaMapper.selectByExample(any())).thenReturn(exceptedList);

        Assertions.assertNotNull(cmsPrefrenceAreaService.listAll());
    }
}