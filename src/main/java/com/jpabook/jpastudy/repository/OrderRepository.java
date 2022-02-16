package com.jpabook.jpastudy.repository;

import com.jpabook.jpastudy.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager entityManager;

    public void save(Order order) {
        entityManager.persist(order);
    }

    public Order findOen(Long id) {
        return entityManager.find(Order.class, id);
    }

//    public List<Order> findAll(OrderSearch orderSearch){}
}
