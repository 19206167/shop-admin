package com.volvo.mall.admin.dao;

import com.volvo.mall.admin.dto.OmsOrderDeliveryParam;
import com.volvo.mall.admin.dto.OmsOrderDetail;
import com.volvo.mall.admin.dto.OmsOrderQueryParam;
import com.volvo.mall.admin.model.OmsOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单查询自定义Dao
 */
public interface OmsOrderDao {
    /**
     * 条件查询订单
     */
    List<OmsOrder> getList(@Param("queryParam") OmsOrderQueryParam queryParam);

    /**
     * 批量发货
     */
    int delivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * 获取订单详情
     */
    OmsOrderDetail getDetail(@Param("id") Long id);
}
