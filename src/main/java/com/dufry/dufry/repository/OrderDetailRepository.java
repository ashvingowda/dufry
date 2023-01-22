package com.dufry.dufry.repository;

import com.dufry.dufry.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query(
            value = "SELECT * FROM order_detail orDetails WHERE orDetails.order_status in ('DELIVERED','REIMBURSED','CANCELLED')",
            nativeQuery = true)
    List<OrderDetail> findAllOrderDetailsByFinalStatus();
}
