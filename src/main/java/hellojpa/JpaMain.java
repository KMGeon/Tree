package hellojpa;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
            Member member = new Member();
            member.setUserName("김무건");
            member.setAge(10);
            em.persist(member);

            List<Member> list = em.createQuery("select m from Member m where m.userName=:username", Member.class)
                    .getResultList();


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