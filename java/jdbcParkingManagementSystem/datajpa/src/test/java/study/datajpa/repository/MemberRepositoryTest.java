package study.datajpa.repository;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.domain.Member;
import study.datajpa.domain.Team;
import study.datajpa.dto.MemberDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @PersistenceContext
    EntityManager entityManager;

    
    
    
    @Before("")
    public void test() throws Exception{
        //given
        System.out.println(" = " + "--------------------");
        //when
        
        //Then
    }


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

    @Test
    public void builkUpdate() throws Exception{
        //given
        memberRepository.save(new Member("member1",10));
        memberRepository.save(new Member("member2",19));
        memberRepository.save(new Member("member3",20));
        memberRepository.save(new Member("member4",21));
        memberRepository.save(new Member("member5",40));

        //when
        //벌크 동작을 하고 영속성 컨텍스트에 반영 x 바로 디비에만 값 변경
        int resultCount = memberRepository.builkAgePlus(20);
//        entityManager.flush();
//        entityManager.clear();

        List<Member> member5 = memberRepository.findByUsername("member5");

        Member member1 = member5.get(0);


        System.out.println("=============== " + member1.getAge());
        for (Member member : member5) {
            System.out.println("member======= " + member);
        }

//        int i = memberRepository.updateByMember("member5", 999);
//        System.out.println("iiiiiiiiiiiiiiiiiiiii = " + i);//1
//        List<Member> all = memberRepository.findAll();
//        for (Member member : all) {
//            System.out.println("maaaaaaaaaaaa= " + member);//바뀜
//        }

        //Then
        assertThat(resultCount).isEqualTo(3);
    }
    @Test
    @Transactional
    public void updateOneMember() throws Exception{
        //given
        memberRepository.save(new Member("김무건",10));
        //when
        int updateOneMemberAge = memberRepository.updateOneMemberAge("김무건",99);
        System.out.println("updateOneMemberAge = " + updateOneMemberAge);
        //Then
        List<Member> all = memberRepository.findAll();
        for (Member member1 : all) {
            System.out.println("member1 = " + member1);
        }
    }

    @Test
    @Transactional
    public void findMemberLazy() throws Exception{
        //given
        Team teamA = new Team("teamA");
        Team teamb = new Team("teamb");
        teamRepository.save(teamA);
        teamRepository.save(teamb);

        Member member1 = new Member("member1", 11, teamA);
        Member member2 = new Member("member2", 22, teamb);
        memberRepository.save(member1);
        memberRepository.save(member2);

        entityManager.flush();
        entityManager.clear();

        //when

        //select member만 가져옴
        List<Member> list = memberRepository.findFetchJoin();

        for (Member member : list) {
            System.out.println("member = " + member);
            //지연로딩이여서 -> 팀을 가져올 때 없어서 가져옴 
            System.out.println("member.getTeam().getClass() = " + member.getTeam().getClass());
            System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
        }
        //Then
    }

    @Test
    @Transactional
    public void findByAge() throws Exception{
        //given
        memberRepository.save(new Member("김무건" , 10));
        //when
        List<Member> memberList = memberRepository.findByAge(10);
        //Then
        assertThat(memberList.get(0).getUsername()).isEqualTo("김무건");
    }

    @Test
    @Transactional
    public void queryHint() throws Exception{
        //given
        Member member = memberRepository.save(new Member("김무건", 11));
        entityManager.flush();
        entityManager.clear();
        //when
        Member only = memberRepository.findReadOnlyByUsername("김무건");
        only.setUsername("바뀜");
        entityManager.flush();
        //Then
    }
}

