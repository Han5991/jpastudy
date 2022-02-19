package com.jpabook.jpastudy.repository;

import com.jpabook.jpastudy.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private String memberName; //회원 이름
    private OrderStatus orderStatus;//ORDER CANCELS
}
