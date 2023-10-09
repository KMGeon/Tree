//package hellojpa;
//
//import hellojpa.embadedType.Address;
//
//import javax.persistence.*;
//import java.util.List;
//
//public class JpaMain {
//    public static void main(String[] args) {
//        //EntityManagerFactory는 WEB서버 1개당 1개 생성
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//        //JPA의 데이터 변경은 모두 트렌젝션 단위
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        try {
//            Member member = new Member();
//            member.setUserName("김무건");
//            member.setAddress(new Address("ㅎㅎ","ㅎㅎ","ㅎㅎ"));
//
//            member.getFavoriteFoods().add("치킨1");
//            member.getFavoriteFoods().add("치킨2");
//            member.getFavoriteFoods().add("치킨3");
//
//            member.getAddressList().add(new Address("ㅁㅁ","ㅁㅁ","ㅁㅁ"));
//            em.persist(member);
//
//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println(findMember.getFavoriteFoods()+"==");
//            System.out.println(findMember.getAddressList()+"==");
//            for (Address address : findMember.getAddressList()) {
//                System.out.println(address.getCity());
//            }
//            System.out.println(findMember.getAddress()+"==");
//
//            tx.commit();
//        } catch (
//                Exception e) {
//            e.printStackTrace();
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//        emf.close();
//    }
//}