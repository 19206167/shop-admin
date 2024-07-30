package com.volvo.mall.admin.service;

import com.volvo.mall.admin.dto.OmsOrderReturnApplyResult;
import com.volvo.mall.admin.dto.OmsReturnApplyQueryParam;
import com.volvo.mall.admin.dto.OmsUpdateStatusParam;
import com.volvo.mall.admin.model.OmsOrderReturnApply;

import java.util.List;

/**
 * 退货申请管理Service
 */
public interface OmsOrderReturnApplyService {
    /**
     * 分页查询申请
     */
    List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * 批量删除申请
     */
    int delete(List<Long> ids);

    /**
     * 修改指定申请状态
     */
    int updateStatus(Long id, OmsUpdateStatusParam statusParam);

    /**
     * 获取指定申请详情
     */
    OmsOrderReturnApplyResult getItem(Long id);
}
