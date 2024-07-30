package com.volvo.mall.admin.service;

import com.volvo.mall.admin.model.CmsPrefrenceArea;

import java.util.List;

/**
 * 优选专区管理Service
 */
public interface CmsPrefrenceAreaService {
    /**
     * 获取所有优选专区
     */
    List<CmsPrefrenceArea> listAll();
}
