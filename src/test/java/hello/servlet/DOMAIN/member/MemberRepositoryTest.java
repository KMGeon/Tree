package hello.servlet.DOMAIN.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearSrore();
    }

    @Test
    void test() {
        //given
        Member member = new Member("Hello" ,20);

        //when
        Member saveMember = memberRepository.save(member);

        //then
        Member findmember = memberRepository.findById(saveMember.getId());
        org.assertj.core.api.Assertions.assertThat(findmember).isEqualTo(saveMember);
    }

    @Test
    void  findAll(){
        //given
        Member member1 = new Member("member1",20);
        Member member2 = new Member("member2",20);

        memberRepository.save(member1);
        memberRepository.save(member2);

//        when
        List<Member>result = memberRepository.findAll();

        //then
        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);

    }
}