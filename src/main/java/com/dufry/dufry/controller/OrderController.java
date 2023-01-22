package com.dufry.dufry.controller;

import com.dufry.dufry.entity.OrderDetail;
import com.dufry.dufry.entity.Product;
import com.dufry.dufry.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderDetailService orderDetailService;

    @PostMapping("/placeOrder")
    public String createOrder(@Validated @RequestBody OrderDetail orderDetail) {

        return  orderDetailService.orderPlacing(orderDetail);
    }

    @GetMapping("/getAllOrders")
    public List<OrderDetail> getAllOrders() {
        return orderDetailService.getAllOrders();
    }

    @PutMapping("/updateOrderStatus/{id}/{status}")
    public OrderDetail updateProduct(@PathVariable Long id,@PathVariable String status) {
        return orderDetailService.updateOrderStatus(id,status);
    }

    @GetMapping("/GetCompletedOrders")
    public List<OrderDetail> getCompletedOrders() {
        return orderDetailService.getCompletedOrders();
    }
}
