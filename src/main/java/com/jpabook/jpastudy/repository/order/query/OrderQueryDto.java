package com.jpabook.jpastudy.repository.order.query;

import com.jpabook.jpastudy.domain.Address;
import com.jpabook.jpastudy.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemQueryDto> orderItems;

    public OrderQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }

//    public OrderQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address, List<ORderItemQueryDto> orderItems) {
//        this.orderId = orderId;
//        this.name = name;
//        this.orderDate = orderDate;
//        this.orderStatus = orderStatus;
//        this.address = address;
//        this.orderItems = orderItems;
//    }
}
