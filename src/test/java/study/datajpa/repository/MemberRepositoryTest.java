package study.datajpa.repository;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.datajpa.domain.Member;
import study.datajpa.domain.Team;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;
    @Test
    public void findByUsernameAndAgeGreaterThan() throws Exception{
        //given
        Member member1 = new Member("AAA",10);
        Member member2 = new Member("BBB",20);
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 10);

        //Then
        result.forEach(r-> {
            System.out.println(r.getUsername()+"===================");
        });
        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
    }
    @Test
    public void findHelloBy() throws Exception{
//        memberRepository.findHelloBy()
    }
    @Test
    public void findUser() throws Exception{
        //given
        Member member1 = new Member("AAA",10);
        Member member2 = new Member("BBB",20);
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member>reList = memberRepository.findUser("AAA",10);
        //Then
        AssertionsForClassTypes.assertThat(reList.get(0)).isEqualTo(member1);
    }

    @Test
    public void testQuery() throws Exception{
        //given
        Member member1 = new Member("AAA",10);
        Member member2 = new Member("BBB",20);
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<String> list = memberRepository.findUsernameList();
        for (String s : list) {
            System.out.println("s = " + s);
        }
        //Then
    }

    @Test
    public void dtoTest() throws Exception{
        //given
        Team team1 = new Team("team1");
        teamRepository.save(team1);

        Member member1 = new Member("AAA",10);
        member1.setTeam(team1);
        memberRepository.save(member1);
        //when
        List<String>userStrings = memberRepository.findUsernameList();
        for (String userString : userStrings) {
            System.out.println("userString = " + userString);
        }

        //Then
    }

}