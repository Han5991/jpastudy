package com.jpabook.jpastudy;

import com.jpabook.jpastudy.domain.Member2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class JpastudyApplicationTests {

    @Autowired
    EntityManager entityManager;

    @Test
    void contextLoads() {
        Member2 member1 = new Member2(160L, "B");
        Member2 member2 = new Member2(150L, "A");

        entityManager.persist(member1);
        entityManager.persist(member2);

        System.out.println("================================");

        Member2 member21 = entityManager.find(Member2.class, 150L);
        Member2 member22 = entityManager.find(Member2.class, 150L);
        System.out.println(member22 == member21);
    }

}
