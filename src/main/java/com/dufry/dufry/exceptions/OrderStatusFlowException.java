package com.dufry.dufry.exceptions;

public class OrderStatusFlowException  extends RuntimeException {
    public OrderStatusFlowException(String errorMsg) {
        super(errorMsg);
    }
}
