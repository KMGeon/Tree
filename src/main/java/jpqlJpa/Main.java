package jpqlJpa;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("김무건");
            member.setAge(27);
            member.setMemberType(MemberType.ADMIN);

            Team team = new Team();
            team.setName("팀 아이디");
            em.persist(team);

            member.changeTeam(team);
            em.persist(member);


            em.flush();
            em.clear();

            List<String> resultList = em.createQuery("select upper('a') from Member m", String.class).getResultList();
            for (String s : resultList) {
                System.out.println("========"+s);
            }

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