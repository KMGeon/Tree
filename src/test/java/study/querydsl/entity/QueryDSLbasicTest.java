package study.querydsl.entity;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberDto;
import study.querydsl.dto.QMemberDto;
import study.querydsl.dto.UserDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.*;
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
        Member member2 = new Member("member2", 22, teamA);
        Member member3 = new Member("member3", 33, teamB);
        Member member4 = new Member("member4", 44, teamB);
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
    public void like_search() throws Exception {
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
    public void resultFetch() throws Exception {
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
    public void sort() throws Exception {
        //given
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));
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
    public void page() throws Exception {
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
    public void fdsjkl() throws Exception {
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
    public void aggregation() throws Exception {
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
    public void group() throws Exception {
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
     *
     * @throws Exception
     */
    @Test
    @DisplayName("join")
    public void join() throws Exception {
        //given
        List<Member> teamA = queryFactory
                .selectFrom(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();
        //when

        //Then
        assertThat(teamA)
                .extracting("username")
                .containsExactly("member1", "member2");
    }

    @Test
    @DisplayName("theta join")
    public void join2() throws Exception {
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
    public void onJoin() throws Exception {
        //given
        List<Tuple> fetch = queryFactory
                .select(member, team)
                .from(member)
                .join(member.team, team)
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
    public void FETCHJOIN() throws Exception {
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
     *
     * @throws Exception
     */
    @Test
    public void subquery() throws Exception {
        //given
        QMember membersub = new QMember("ma");

        List<Member> fetch = queryFactory
                .selectFrom(member)
                .where(member.age.eq(select(membersub.age.max())
                        .from(membersub)))
                .fetch();

    }

    @PersistenceUnit //entitymanaget factory를 만든다.
    EntityManagerFactory emf;

    @Test
    public void NO_FETCHJION() throws Exception {
        //given
        em.flush();
        em.clear();
        //when
        Member member1 = queryFactory.selectFrom(member)
                .join(member.team, team).fetchJoin()
                .where(member.username.eq("member1"))
                .fetchOne();
        //Then
        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(member1.getUsername());
        assertThat(loaded).as("패치조인 미적용").isTrue();
        //Assertions.assertThat().isEqualTo();
    }

    //TODO: 서브 쿼리에 대해서 알아보자

    /**
     * 나이가 가장 많은 회원 조회
     */
    @Test
    @DisplayName("서브쿼리에 대해서 알아보자")
    public void subQueryTest() throws Exception {
        //when
        QMember memberSub = new QMember("memberSub");
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(
                        select(memberSub.age.max())
                                .from(memberSub)
                )).fetch();
        //Then

        assertThat(result)
                .extracting("age")//List의 컬럼 값을 기대한다.
                .containsExactly(44);
    }

    @Test
    @DisplayName("나이가 평균 이상인 회원을 조회")
    public void subQueryGoe() throws Exception {
        //given

        //when
        QMember memberSub = new QMember("memberSub");
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.goe(
                        select(memberSub.age.avg())
                                .from(memberSub)
                )).fetch();
        //Then
        result.forEach(i -> {
            System.out.println("i = " + i);
        });

        assertThat(result)
                .extracting("age")//List의 컬럼 값을 기대한다.
                .containsExactly(33, 44);
    }

    @Test
    public void subQueryIn() throws Exception {
        //when
        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.in(
                        select(memberSub.age)
                                .from(memberSub)
                                .where(memberSub.age.gt(11))
                )).fetch();
        //Then
        result.forEach(i -> {
            System.out.println("i = " + i);
        });

        assertThat(result)
                .extracting("age")//List의 컬럼 값을 기대한다.
                .containsExactly(22, 33, 44);
    }

    @Test
    @DisplayName("select 서브쿼리")
    public void selectSubQuery() throws Exception {
        //when
        QMember memberSub = new QMember("memberSub");

        List<Tuple> fetch = queryFactory.select(member.username,
                        select(memberSub.age.avg())
                                .from(memberSub))
                .from(member)
                .fetch();

        fetch.forEach(f -> {
            System.out.println("f = " + f);
        });

        //Then
        //Assertions.assertThat().isEqualTo();
    }

    //TODO: CASE
    @Test
    @DisplayName("case")
    public void basicCase() throws Exception {
        //given

        //when
        List<String> fetch = queryFactory.select(member.age
                        .when(11).then("열한살")
                        .when(22).then("22")
                        .otherwise("기타"))
                .from(member)
                .fetch();

        fetch.forEach(f -> {
            System.out.println("f = " + f);
        });
        //Then
        //Assertions.assertThat().isEqualTo();
    }

    @Test
    @DisplayName("complexCase")
    public void complexCase() throws Exception {
        //when
        List<String> fetch = queryFactory
                .select(new CaseBuilder()
                        .when(member.age.between(0, 22)).then("하하")
                        .otherwise("기타"))
                .from(member)
                .fetch();
        //Then
        //Assertions.assertThat().isEqualTo();
    }

    //TODO: 중급 문법
    @Test
    @DisplayName("querydsl")
    public void QUERYDSL() throws Exception {
        List<Tuple> fetch = queryFactory.select(member.username, member.age)
                .from(member)
                .fetch();

        for (Tuple tuple : fetch) {
            System.out.println("tuple = " + tuple.get(member.username));
        }
    }

    //Todo
    public void findDtoBtJQPL() {
//        em.createQuery("select new study.querydsl.dto.MemberDto(m.age,m.username)from Member m", MemberDto.class)
    }

    @Test
    @DisplayName("SETTER를 이용해서 DTO 조회")
    public void dto_find_setter() throws Exception {
        //given
        List<MemberDto> fetch = queryFactory
                .select(Projections.bean(MemberDto.class,
                        member.username
                        , member.age))
                .from(member)
                .fetch();
        //when
        fetch.forEach(member -> {
            System.out.println("member = " + member);
        });
    }

    @Test
    @DisplayName("필드를 이용해서 DTO 조회")
    public void dto_find_filed() throws Exception {
        //given
        List<MemberDto> fetch = queryFactory
                .select(Projections.fields(MemberDto.class,
                        member.username
                        , member.age))
                .from(member)
                .fetch();
        //when
        fetch.forEach(member -> {
            System.out.println("member = " + member);
        });
    }

    @Test
    @DisplayName("생성자를 이용해서 DTO 조회")
    public void dto_find_construct() throws Exception {
        //given
//        List<MemberDto> fetch = queryFactory
//                .select(Projections.constructor(MemberDto.class,
//                        member.username
//                        , member.age))//setter로 주입한다.
//                .from(member)
//                .fetch();
//        //when
//        fetch.forEach(member->{
//            System.out.println("member = " + member);
//        });

        QMember memberSub = new QMember("memberSub");
        List<UserDto> fetch = queryFactory
                .select(Projections.fields(UserDto.class,
                        member.username.as("username"),
                        ExpressionUtils.as(
                                JPAExpressions.select(memberSub.age.max())
                                        .from(memberSub), "age"
                        )))
                .from(member)
                .fetch();

        fetch.forEach(project -> {
            System.out.println("project = " + project);
        });
    }

    @Test
    @DisplayName("QueryProjections")
    public void QueryProjections() throws Exception {
        //when
        List<MemberDto> fetch = queryFactory
                .select(new QMemberDto(member.username, member.age))
                .from(member)
                .fetch();
        //Then
        fetch.forEach(memberDto -> {
            System.out.println("memberDto = " + memberDto);
        });
    }

    @Test
    @DisplayName("booleanBuilder")
    public void booleanBuilder() throws Exception {
        String usernameParam = "member1";
        Integer ageParam = 10;

        List<Member> result = searchMember(usernameParam, ageParam);
        result.forEach(member1 -> {
            System.out.println("member1 = " + member1);
        });
    }

    private List<Member> searchMember(String usernameParam, Integer ageParam) {
        BooleanBuilder builder = new BooleanBuilder();
        if (usernameParam != null) {
            builder.and(member.username.eq(usernameParam));
        }
        if (ageParam != null) {
            builder.and(member.age.eq(ageParam));
        }
        return queryFactory
                .selectFrom(member)
                .where(builder)
                .fetch();
    }

    @Test
    public void dynamicQuery() {
        String username = "member1";
        Integer age = 10;

        List<Member> result = searchMember2(username, age);

        //Then
        //Assertions.assertThat().isEqualTo();
    }

    private List<Member> searchMember2(String username, Integer age) {
        return queryFactory
                .selectFrom(member)
//                .where(usernameEq(username), ageEq(age))
                .where(allEq(username, age))
                .fetch();
    }

    private BooleanExpression ageEq(Integer age) {
        return age != null ? member.age.eq(age) : null;
    }

    private BooleanExpression usernameEq(String username) {
        return username != null ? member.username.eq(username) : null;
    }

    private Predicate allEq(String username, Integer age) {
        return usernameEq(username).and(ageEq(age));
    }
}

