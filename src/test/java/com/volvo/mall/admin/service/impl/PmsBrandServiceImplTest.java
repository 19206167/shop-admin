package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.PmsBrandMapper;
import com.volvo.mall.admin.mapper.PmsProductMapper;
import com.volvo.mall.admin.model.PmsBrand;
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

class PmsBrandServiceImplTest {

    @Mock
    PmsBrandMapper brandMapper;

    @Mock
    PmsProductMapper productMapper;

    @InjectMocks
    @Spy
    PmsBrandServiceImpl pmsBrandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 测试展示所有品牌成功
     **/
    @Test
    void listAllBrand() {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setId(1L);

        List<PmsBrand> exceptedList = new ArrayList<>();
        exceptedList.add(pmsBrand);

        when(brandMapper.selectByExample(any())).thenReturn(exceptedList);

        Assertions.assertEquals(1, pmsBrandService.listBrand("test", 1, 1, 5).size());
    }

    @Test
    void createBrand() {
    }

    @Test
    void updateBrand() {
    }

    @Test
    void deleteBrand() {
    }

    @Test
    void testDeleteBrand() {
    }

    @Test
    void listBrand() {
    }

    @Test
    void getBrand() {
    }

    @Test
    void updateShowStatus() {
    }

    @Test
    void updateFactoryStatus() {
    }
}