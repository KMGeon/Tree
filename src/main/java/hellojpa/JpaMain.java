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


            Team team = new Team();
            team.setName("team1");
            em.persist(team);

            Member member = new Member();
            member.setUserName("member1");
//            member.changeTeam(team);
            em.persist(member);

            team.addMember(member);
//            team.getMembers().add(member);// 읽기 전용 그래서
            em.flush();//디비로 쿼리 날리고
            em.clear();//캐시 다 삭제

//            Member findMember = em.find(Member.class, member.getMemberId());//여기서 find를 하면 1차 캐시에서 가져옴
//            Team findTeam = findMember.getTeam();

//            List<Member> members = findMember.getTeam().getMembers();//그 pk의 member에 팀을 접근하고 그 팀의 member를 접근??
//            members.forEach(c -> System.out.println(c.getTeam().getTeamId() + "================" + c.getUserName()));


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