package com.dufry.dufry.service;

import com.dufry.dufry.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    String orderPlacing(OrderDetail orderDetail);
    OrderDetail updateOrderStatus(Long orderId,String status);
    String cancelOrder(Long orderId);
    String getOrderStatus(Long orderId);
    List<OrderDetail>  getCompletedOrders();
    List<OrderDetail> getAllOrders();
}
