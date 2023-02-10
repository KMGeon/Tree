package jpqlJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

            Team team = new Team();
            team.setName("팀 아이디");
            em.persist(team);

            member.changeTeam(team);
            em.persist(member);


            em.flush();
            em.clear();
            String query = "select m from Member m left outer join m.team t";
            List<Member> list = em.createQuery(query, Member.class)
                    .getResultList();

            System.out.println(list.size() + "=-==============================");
            for (Member member1 : list) {
                System.out.println("-================" + member1.toString());
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