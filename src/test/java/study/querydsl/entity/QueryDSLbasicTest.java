package study.querydsl.entity;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static study.querydsl.entity.QMember.*;
import static study.querydsl.entity.QTeam.team;


@DataJpaTest
@Transactional
public class QueryDSLbasicTest {
    @Autowired
    EntityManager em;

    private JPAQueryFactory queryFactory;

    @BeforeEach
    void setUp() {
        queryFactory = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 11, teamA);
        Member member2 = new Member("member2", 12, teamA);
        Member member3 = new Member("member3", 31, teamB);
        Member member4 = new Member("member4", 32, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    @DisplayName("test")
    public void startJPQL() throws Exception {
        //MEMBER1을 찾아라
        Member member1 = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();
        assertThat(member1.getUsername()).isEqualTo("member1");
    }

    @Test
    @DisplayName("queryDSL_1")
    public void querydsl() throws Exception {
        //given


        Member member1 = queryFactory.select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        //Then
        assertThat(member1.getUsername()).isEqualTo("member1");
    }

    @Test
    @DisplayName("search")
    public void search() throws Exception {
        //given
        Member member1 = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1")
                        .and(member.age.eq(11)))
                .fetchOne();
        //when
        //Then
        assertThat(member1.getUsername()).isEqualTo("member1");
    }

    @Test
    @DisplayName("like search")
    public void like_search() throws Exception{
        //given
        long member1 = queryFactory
                .selectFrom(member)
                .where(member.username.startsWith("member"),
                        (member.age.eq(11)))
                .fetchCount();
        //when

        //Then
        assertThat(member1).isNotNull();
    }

    @Test
    @DisplayName("resultFetch")
    public void resultFetch() throws Exception{
        //given
        List<Member> fetch1 = queryFactory
                .selectFrom(member)
                .fetch();

//        Member member2 = queryFactory
//                .selectFrom(member)
////                .limit(1).fetchOne();
//                .fetchOne();

//        Member member1 = queryFactory
//                .select(member)
//                .fetchFirst();

        QueryResults<Member> results = queryFactory
                .selectFrom(member)
                .fetchResults();

        List<Member> results1 = results.getResults();

        results1.forEach(System.out::println);

        //Assertions.assertThat().isEqualTo();
    }

    /*
    나이 내일차순
    이름 올림차순
    단 2에서 회원이름이 없으면 마지막에 출력
     */
    @Test
    @DisplayName("sort")
    public void sort() throws Exception{
        //given
        em.persist(new Member(null,100));
        em.persist(new Member("member5",100));
        em.persist(new Member("member6",100));
        //when
        List<Member> fetch = queryFactory
                .selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(),
                        member.username.asc().nullsLast())
                .fetch();
        fetch.forEach(System.out::println);
        //Assertions.assertThat().isEqualTo();
    }

    @Test
    @DisplayName("page")
    public void page() throws Exception{
        //given
        List<Member> fetch = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(0)
                .limit(2)
                .fetch();
        //when
        for (Member fetch1 : fetch) {
            System.out.println("fetch1 = " + fetch1.getUsername());
        }
        //Then
        //Assertions.assertThat().isEqualTo();
    }

    @Test
    @DisplayName("fjdskl")
    public void fdsjkl() throws Exception{
        //given
        List<Member> fetch = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(1)
                .fetch();
        //when
        for (Member fetch1 : fetch) {
            System.out.println("fetch1 = " + fetch1);
        }
        //Then
        //Assertions.assertThat().isEqualTo();
    }

    @Test
    @DisplayName("집합")
    public void aggregation() throws Exception{
        //given
        List<Tuple> fetch = queryFactory
                .select(
                        member.count(),
                        member.age.sum(),
                        member.age.avg()
                ).from(member)
                .fetch();
        //when
        fetch.forEach(System.out::println);
        //Then
        //Assertions.assertThat().isEqualTo();
    }

    @Test
    @DisplayName("group")
    public void group() throws Exception{
        //given
        List<Tuple> fetch = queryFactory
                .select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();
        //when

        //Then
        //Assertions.assertThat().isEqualTo();
    }

    /**
     * 팀 a에 소속된 모든 회원
     * @throws Exception
     */
    @Test
    @DisplayName("join")
    public void join() throws Exception{
        //given
        List<Member> teamA = queryFactory
                .selectFrom(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();
        //when

        //Then
        Assertions.assertThat(teamA)
                .extracting("username")
                .containsExactly("member1","member2");
    }

    @Test
    @DisplayName("theta join")
    public void join2() throws Exception{
        //given
        List<Member> fetch = queryFactory
                .select(member)
                .from(member, team)
                .where(member.username.eq(team.name))
                .fetch();
        //when

        //Then
        //Assertions.assertThat().isEqualTo();
    }
    @Test
    @DisplayName("join_on")
    public void onJoin() throws Exception{
        //given
        List<Tuple> fetch = queryFactory
                .select(member, team)
                .from(member)
                .join(member.team,team)
                .where(member.team.name.eq(team.name))
//                .leftJoin(member.team, team).on(team.name.eq("teamA"))
                .fetch();
        //when
        fetch.forEach(System.out::println);
        //Then
        //Assertions.assertThat().isEqualTo();
    }



    @Test
    @DisplayName("fetch_JOIN")
    public void FETCHJOIN() throws Exception{
        //given
        em.flush();
        em.clear();
        Member member1 = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1"))
                .fetchOne();
        //when

        //Then
        //Assertions.assertThat().isEqualTo();
    }

    /**
     * 나이가 가장 많은 회원 조회
     * @throws Exception
     */
    @Test
    public void subquery() throws Exception{
        //given
//        QMember membersub = new QMember("ma");
//
//        List<Member> fetch = queryFactory
//                .selectFrom(member)
//                .where(member.age.eq(JPAExpressions.select(membersub.age.max())
//                        .from(membersub)))
//                .fetch();


        //when
        //select (select t.name from team) from member m, team t where m.name = t.name
        //Then
        //Assertions.assertThat().isEqualTo();
    }
}