package jpaBook.jpaShop.service;

import jpaBook.jpaShop.domain.Member;
import jpaBook.jpaShop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true) // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행되어야 함
@RequiredArgsConstructor
public class MemberService {


    // 변경 할 필요 없음
    // @RequiredArgsConstructor 의해 final 넣으면 컴파일 시점에 체크 가능 (초기화 안되면 컴파일 에러)
    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional // readOnly=false (default)
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
        
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
