package com.jpabook.jpastudy.repository.order.query;

import com.jpabook.jpastudy.domain.Address;
import com.jpabook.jpastudy.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderFlatDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate; //주문시간
    private OrderStatus orderStatus;
    private Address address;

    private String itemName;//상품 명
    private int orderPrice; //주문 가격
    private int count;      //주문 수량
}
