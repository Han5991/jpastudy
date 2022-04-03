package com.jpabook.jpastudy.api;

import com.jpabook.jpastudy.api.MemberApiController.Result;
import com.jpabook.jpastudy.domain.Address;
import com.jpabook.jpastudy.domain.Order;
import com.jpabook.jpastudy.domain.OrderStatus;
import com.jpabook.jpastudy.repository.OrderRepository;
import com.jpabook.jpastudy.repository.OrderSearch;
import com.jpabook.jpastudy.repository.order.simplequery.OrderSimpleQueryDto;
import com.jpabook.jpastudy.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * xToOne (ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository; //의존관계 주입

    /**
     * V1. 엔티티 직접 노출
     * - Hibernate5Module 모듈 등록, LAZY=null 처리
     * - 양방향 관계 문제 발생 -> @JsonIgnore
     */
    @GetMapping("v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        all.forEach(order -> {
            order.getMember().getName(); //Lazy 강제 초기화
            order.getDelivery().getAddress(); //Lazy 강제 초기화
        });
        return all;
    }

    /**
     * V2. 엔티티를 조회해서 DTO로 변환(fetch join 사용X)
     * - 단점: 지연로딩으로 쿼리 N번 호출
     */
    @GetMapping("v2/simple-orders")
    public Result<List<SimpleOrderDto>> ordersV2() {
        //ORDER 2개
        // N + 1 -> 1 + 회원 N + 배송 N
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        return new Result<>(
                orders.size(),
                orders.stream().map(SimpleOrderDto::new).collect(toList()));
    }

    /**
     * V3. 엔티티를 조회해서 DTO로 변환(fetch join 사용O)
     * - fetch join으로 쿼리 1번 호출
     * 참고: fetch join에 대한 자세한 내용은 JPA 기본편 참고(정말 중요함)
     * 단점 엔티티에서 긁어왔다 이제 이거를 dto로 바꾸자
     */
    @GetMapping("v3/simple-orders")
    public Result<List<SimpleOrderDto>> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return new Result<>(
                orders.size(),
                orders.stream().map(SimpleOrderDto::new).collect(toList()));
    }

    @GetMapping("v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate; //주문시간
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName(); //LAZY 초기화 : 영속성 엔티티에 없으면 쿼리를 날려서 가져온다
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();  //LAZY 초기화
        }
    }
}
