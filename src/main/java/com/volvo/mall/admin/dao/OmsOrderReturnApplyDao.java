package com.volvo.mall.admin.dao;

import com.volvo.mall.admin.dto.OmsOrderReturnApplyResult;
import com.volvo.mall.admin.dto.OmsReturnApplyQueryParam;
import com.volvo.mall.admin.model.OmsOrderReturnApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单退货申请管理自定义Dao
 */
public interface OmsOrderReturnApplyDao {
    /**
     * 查询申请列表
     */
    List<OmsOrderReturnApply> getList(@Param("queryParam") OmsReturnApplyQueryParam queryParam);

    /**
     * 获取申请详情
     */
    OmsOrderReturnApplyResult getDetail(@Param("id")Long id);
}
