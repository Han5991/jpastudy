package com.jpabook.jpastudy.repository.order.simplequery;

import com.jpabook.jpastudy.domain.Address;
import com.jpabook.jpastudy.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderSimpleQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate; //주문시간
    private OrderStatus orderStatus;
    private Address address;
}
