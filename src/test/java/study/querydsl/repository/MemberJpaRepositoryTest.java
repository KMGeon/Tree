package study.querydsl.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {
    @Autowired
    private EntityManager em;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Test
    @DisplayName("test")
    public void test1() throws Exception {
        //given
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);
        //when
        Member findMember = memberJpaRepository.findById(member.getId()).get();
        //Then
        assertThat(findMember).isEqualTo(member);

        List<Member> all = memberJpaRepository.findAll();
        assertThat(all).containsExactly(member);

        List<Member> member1 = memberJpaRepository.findByUsername("member1");
        assertThat(member1).containsExactly(member);
    }

    @Test
    @DisplayName("querydsl 변경 테스트")
    public void querydslTest() throws Exception {
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        List<Member> querydsl = memberJpaRepository.findAll_Querydsl();
        assertThat(querydsl).extracting("username")
                .containsExactly("member1");

        List<Member> findByUsername = memberJpaRepository.findByUsername("member1");
        assertThat(findByUsername)
                .extracting("age")
                .containsExactly(10);
    }

    @Test
    @DisplayName("build최적화 테스트")
    public void searchTest() throws Exception{
        //given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        //when
        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setAgeGoe(35);
        condition.setAgeLoe(40);
        condition.setTeamName("teamB");

        List<MemberTeamDto> teamDtos = memberJpaRepository.search(condition);
        //Then
        assertThat(teamDtos)
                .extracting("username")
                .containsExactly("member4");
    }
}