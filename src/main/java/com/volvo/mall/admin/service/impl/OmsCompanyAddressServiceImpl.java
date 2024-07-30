package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.OmsCompanyAddressMapper;
import com.volvo.mall.admin.model.OmsCompanyAddress;
import com.volvo.mall.admin.model.OmsCompanyAddressExample;
import com.volvo.mall.admin.service.OmsCompanyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址管理Service实现类
 */
@Service
public class OmsCompanyAddressServiceImpl implements OmsCompanyAddressService {
    @Autowired
    private OmsCompanyAddressMapper companyAddressMapper;

    @Override
    public List<OmsCompanyAddress> list() {
        return companyAddressMapper.selectByExample(new OmsCompanyAddressExample());
    }
}
