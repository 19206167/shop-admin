package com.volvo.mall.admin.dao;

import com.volvo.mall.admin.model.PmsProductFullReduction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品满减自定义Dao
 */
public interface PmsProductFullReductionDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<PmsProductFullReduction> productFullReductionList);
}
