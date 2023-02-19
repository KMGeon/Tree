package study.datajpa.repository;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import study.datajpa.domain.Member;
import study.datajpa.domain.Team;
import study.datajpa.dto.MemberDto;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @Test
    public void findByUsernameAndAgeGreaterThan() throws Exception {
        //given
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 10);

        //Then
        result.forEach(r -> {
            System.out.println(r.getUsername() + "===================");
        });
        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
    }

    @Test
    public void findHelloBy() throws Exception {
//        memberRepository.findHelloBy()
    }

    @Test
    public void findUser() throws Exception {
        //given
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> reList = memberRepository.findUser("AAA", 10);
        //Then
        AssertionsForClassTypes.assertThat(reList.get(0)).isEqualTo(member1);
    }

    @Test
    public void testQuery() throws Exception {
        //given
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);
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
    public void dtoTest() throws Exception {
        //given
        Team team1 = new Team("team1");
        teamRepository.save(team1);

        Member member1 = new Member("AAA", 10);
        member1.setTeam(team1);
        memberRepository.save(member1);
        //when
        List<String> userStrings = memberRepository.findUsernameList();
        for (String userString : userStrings) {
            System.out.println("userString = " + userString);
        }

        //Then
    }

    @Test
    public void optionTest() throws Exception {
        //given
        Member member1 = new Member("AAA", 10);
        memberRepository.save(member1);
        //when
        Optional<Member> aaa = memberRepository.findOptionByusername("AAA");
        aaa.stream().forEach(c -> System.out.println("c = " + c));
    }


    @Test
    public void paging() throws Exception{
        //given
        memberRepository.save(new Member("member1",10));
        memberRepository.save(new Member("member2",10));
        memberRepository.save(new Member("member3",10));
        memberRepository.save(new Member("member4",10));
        memberRepository.save(new Member("member5",10));
        memberRepository.save(new Member("member6",10));
        memberRepository.save(new Member("member7",10));
        memberRepository.save(new Member("member8",10));
        memberRepository.save(new Member("member9",10));
        memberRepository.save(new Member("member10",10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(1, 5, Sort.by(Sort.Direction.DESC, "username"));//0페이지에서 3개 가져오기

        //when
        Page<Member> memberPage = memberRepository.findByAge(age, pageRequest);
        Page<MemberDto> map = memberPage.map(member -> new MemberDto(member.getId(), member.getUsername(), null));

        memberPage.stream()
                .forEach(c-> System.out.println("c.getUsername() = " + c.getUsername()));

        //Then
//        long totalElements = memberPage.getTotalElements();
//        System.out.println("totalElements = " + totalElements);
    }
}

