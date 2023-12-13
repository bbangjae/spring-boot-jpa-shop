package jpaBook.jpaShop.service;

import jakarta.persistence.EntityManager;
import jpaBook.jpaShop.domain.Member;
import jpaBook.jpaShop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;
    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findOne(savedId));

    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");
        Member member1 = new Member();
        member1.setName("kim");

        // when
        Long savedId = memberService.join(member);

        // then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member1));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}