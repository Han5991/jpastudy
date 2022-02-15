package com.jpabook.jpastudy.service;

import com.jpabook.jpastudy.domain.Member;
import com.jpabook.jpastudy.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("han");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    void 중복_회원_예외() throws Exception {
        //given
        Member han1 = new Member();
        han1.setName("han");
        Member han2 = new Member();
        han2.setName("han");

        //when
//        memberService.join(han1);
//        try {
//        } catch (IllegalStateException e) {
//            memberService.join(han2);
//            return;
//        }
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(han1);
            memberService.join(han2);
        });

        //then
//        fail("예외가 발생해야 한다.");
    }
}