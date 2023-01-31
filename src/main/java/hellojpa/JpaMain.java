package hellojpa;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //EntityManagerFactory는 WEB서버 1개당 1개 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //JPA의 데이터 변경은 모두 트렌젝션 단위
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUserName("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            List<Member> list = em.createQuery("select m from Member m", Member.class).getResultList();
            list.forEach(System.out::println);


            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}