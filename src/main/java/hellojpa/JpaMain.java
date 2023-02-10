package hellojpa;

import javax.persistence.*;
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
            Member member = new Member();
            member.setUserName("김무건");
            em.persist(member);

            Team team = new Team();
            team.setName("김무건");
            em.persist(team);

            member.changTeam(team);

            em.flush();
            em.clear();

            List<Member> query = em.createQuery("select m from Member m join fetch m.team", Member.class)
                    .getResultList();
            tx.commit();
        } catch (
                Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}