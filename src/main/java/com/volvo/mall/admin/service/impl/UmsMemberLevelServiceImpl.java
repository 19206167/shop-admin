package com.volvo.mall.admin.service.impl;

import com.volvo.mall.admin.mapper.UmsMemberLevelMapper;
import com.volvo.mall.admin.model.UmsMemberLevel;
import com.volvo.mall.admin.model.UmsMemberLevelExample;
import com.volvo.mall.admin.service.UmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员等级管理Service实现类
 */
@Service
public class UmsMemberLevelServiceImpl implements UmsMemberLevelService{
    @Autowired
    private UmsMemberLevelMapper memberLevelMapper;

    @Override
    public List<UmsMemberLevel> list(Integer defaultStatus) {
        UmsMemberLevelExample example = new UmsMemberLevelExample();
        example.createCriteria().andDefaultStatusEqualTo(defaultStatus);
        return memberLevelMapper.selectByExample(example);
    }
}
