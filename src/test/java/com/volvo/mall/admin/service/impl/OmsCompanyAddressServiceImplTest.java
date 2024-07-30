package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.OmsCompanyAddressMapper;
import com.volvo.mall.admin.model.OmsCompanyAddress;
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

class OmsCompanyAddressServiceImplTest {
    @Mock
    OmsCompanyAddressMapper companyAddressMapper;

    @Spy
    @InjectMocks
    OmsCompanyAddressServiceImpl omsCompanyAddressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 测试list所有地址
     **/
    @Test
    void testList() {
        OmsCompanyAddress omsCompanyAddress = new OmsCompanyAddress();
        omsCompanyAddress.setId(1L);

        List<OmsCompanyAddress> exceptedList = new ArrayList<>();
        exceptedList.add(omsCompanyAddress);

        when(companyAddressMapper.selectByExample(any())).thenReturn(exceptedList);

        Assertions.assertNotNull(omsCompanyAddressService.list());
    }
}