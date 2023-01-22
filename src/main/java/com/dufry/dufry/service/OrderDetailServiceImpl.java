package com.dufry.dufry.service;

import com.dufry.dufry.constant.OrderStatus;
import com.dufry.dufry.entity.OrderDetail;
import com.dufry.dufry.entity.Product;
import com.dufry.dufry.exceptions.OrderStatusFlowException;
import com.dufry.dufry.exceptions.ResourceNotFoundException;
import com.dufry.dufry.repository.OrderDetailRepository;
import com.dufry.dufry.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public String orderPlacing(OrderDetail orderDetail) {

        Set<Product> products = new HashSet<>();
        Long totalOrderAmount = 0L;
        orderDetail.getProductCodes().stream().forEach(x->{
            Product product = null;
            product = productRepository.findById(x).orElseThrow(() -> new ResourceNotFoundException("Product not found for the id " +x));
            products.add(product);
        });
        totalOrderAmount = products.stream().map(x -> x.getPrice()).reduce(0L,(price1,price2)->price1+price2);
        orderDetail.setProducts(products);
        OrderStatus orderStatus =  OrderStatus.NEW;
        orderDetail.setOrderStatus(orderStatus.toString());
        orderDetail.setCreated(LocalDateTime.now());
        orderDetail.setTotalOrderAmount(totalOrderAmount);
        orderDetailRepository.save(orderDetail);

        return "Placed new Order";
    }

    @Override
    public OrderDetail updateOrderStatus(Long orderId,String status) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderId).get();

        if(orderDetail==null) {

        } else {
            switch (status.toUpperCase()) {
                case "PAID":
                    if(orderDetail.getOrderStatus().equals(OrderStatus.NEW.toString())) {
                        orderDetail.setOrderStatus(OrderStatus.PAID.toString());
                    } else {
                        throw new OrderStatusFlowException("Order Status Flow Exception");

                    }
                    break;
                case "SENT":
                    if(orderDetail.getOrderStatus().equals(OrderStatus.PAID.toString())) {
                        orderDetail.setOrderStatus(OrderStatus.SENT.toString());
                    } else {
                        throw new OrderStatusFlowException("Order Status Flow Exception");
                    }
                    break;
                case "DELIVERED":
                    if(orderDetail.getOrderStatus().equals(OrderStatus.SENT.toString())) {
                        orderDetail.setOrderStatus(OrderStatus.DELIVERED.toString());
                    } else {
                        throw new OrderStatusFlowException("Order Status Flow Exception");
                    }
                    break;
                case "REIMBURSED":
                    if(orderDetail.getOrderStatus().equals(OrderStatus.NEW.toString())) {
                        orderDetail.setOrderStatus(OrderStatus.REIMBURSED.toString());
                    } else {
                        throw new OrderStatusFlowException("Order Status Flow Exception");
                    }
                    break;
                case "CANCELLED":
                    if(orderDetail.getOrderStatus().equals(OrderStatus.PAID.toString())) {
                        orderDetail.setOrderStatus(OrderStatus.CANCELLED.toString());
                    } else {
                        throw new OrderStatusFlowException("Order Status Flow Exception");
                    }
                    break;

            }
        }
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public String cancelOrder(Long orderId) {

        return null;
    }

    @Override
    public String getOrderStatus(Long orderId) {

        OrderDetail orderDetail = orderDetailRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Product not found for the id " +orderId));

        return orderDetailRepository.findById(orderId).get().getOrderStatus();
    }

    @Override
    public List<OrderDetail> getCompletedOrders() {
        ArrayList<String> list = new ArrayList();
        list.add(OrderStatus.DELIVERED.toString());
        list.add(OrderStatus.CANCELLED.toString());
        list.add(OrderStatus.REIMBURSED.toString());
        return  orderDetailRepository.findAllOrderDetailsByFinalStatus();
    }

    @Override
    public List<OrderDetail> getAllOrders() {
        return  orderDetailRepository.findAll();

    }
}
