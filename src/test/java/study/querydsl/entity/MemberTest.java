package study.querydsl.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional
@Commit
class MemberTest {

    @Autowired
    EntityManager em;

    @BeforeEach
    void setUp(){
        Team teamA = new Team();
        Team teamB = new Team();
        em.persist(teamA);
        em.persist(teamB);

        Member memberA = new Member("member1", 10, teamA);
        Member memberB = new Member("member2", 12, teamA);

        Member memberC = new Member("memberC", 15, teamB);
        Member memberD = new Member("memberD", 16, teamB);
        em.persist(memberA);
        em.persist(memberB);
        em.persist(memberC);
        em.persist(memberD);
    }

    @Test
    public void testEntity() throws Exception {

        //when
        em.flush();
        em.clear();
        //Then
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

        members.forEach(System.out::println);
    }

}