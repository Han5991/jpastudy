package com.jpabook.jpastudy.service;

import com.jpabook.jpastudy.domain.Delivery;
import com.jpabook.jpastudy.domain.Member;
import com.jpabook.jpastudy.domain.Order;
import com.jpabook.jpastudy.domain.OrderItem;
import com.jpabook.jpastudy.domain.item.Item;
import com.jpabook.jpastudy.repository.ItemRepository;
import com.jpabook.jpastudy.repository.MemberRepository;
import com.jpabook.jpastudy.repository.OrderRepository;
import com.jpabook.jpastudy.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);
        //cascade 언제 쓸까 -> 뭔가 참조하는게 프라이빗 아이디일 때 ex) OrderItem -> Order

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId){
        //주문 엔티티 조회
        Order order = orderRepository.findOen(orderId);
        //주문 조회
        order.cancel();
    }

//    검색
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAllByString(orderSearch);
    }
}
