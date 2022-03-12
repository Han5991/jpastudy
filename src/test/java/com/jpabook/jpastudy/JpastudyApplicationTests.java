package com.jpabook.jpastudy;

import com.jpabook.jpastudy.domain.Member2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootTest
@Transactional(readOnly = true)
class JpastudyApplicationTests {

    @Autowired
    EntityManager entityManager;

    @Test
    @Rollback(value = false)
    void contextLoads() {

//        EntityTransaction tx = entityManager.getTransaction();

        Member2 member2 = new Member2();
        member2.setUsername("1");
        Member2 member3 = new Member2();
        member3.setUsername("2");

        entityManager.persist(member2);
        entityManager.persist(member3);

//        tx.commit();
    }

}
