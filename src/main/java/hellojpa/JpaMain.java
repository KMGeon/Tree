package hellojpa;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
            member.setUserName("C");

            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
/*
엔티티 메니저는 쓰레드간에 공유 X  (사용하고 버려야 한다.)
JPA의 모든 데이터 변경은 트랜잭션 안에서 실행을 해야된다.
            /*
            어떻게 자바 객체에서 값만 바꿨는데 UPDATE가 되지??
            JPA통해서 ENTITY를 가져오면 관리 변경이 됬는지 안됬는지 체크 , 커밋을 할때 값이 변경이 되면
            바로 UPDATE 쿼리가 날라감
             */


/*
JPQL

 */