package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.CmsPrefrenceAreaMapper;
import com.volvo.mall.admin.model.CmsPrefrenceArea;
import com.volvo.mall.admin.model.CmsPrefrenceAreaExample;
import com.volvo.mall.admin.service.CmsPrefrenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品优选管理Service实现类
 */
@Service
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {
    @Autowired
    private CmsPrefrenceAreaMapper prefrenceAreaMapper;

    @Override
    public List<CmsPrefrenceArea> listAll() {
        return prefrenceAreaMapper.selectByExample(new CmsPrefrenceAreaExample());
    }
}
