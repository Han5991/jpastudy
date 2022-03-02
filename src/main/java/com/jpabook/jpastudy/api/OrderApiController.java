package com.jpabook.jpastudy.api;

import com.jpabook.jpastudy.domain.Order;
import com.jpabook.jpastudy.domain.OrderItem;
import com.jpabook.jpastudy.repository.OrderRepository;
import com.jpabook.jpastudy.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class OrderApiController {

    private final OrderRepository orderRepository;

    @GetMapping("v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); //Lazy 강제 초기화
            order.getDelivery().getAddress(); //Lazy 강제 초기화

            order.getOrderItems().stream().forEach(o -> o.getItem().getName());//Lazy 강제 초기화
        }
        return all;
    }
}
