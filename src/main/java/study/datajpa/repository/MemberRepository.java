package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import study.datajpa.domain.Member;
import study.datajpa.dto.MemberDto;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

//    @Query("select m from Member m left join fetch m.team")


//    @Override
//    @EntityGraph(attributePaths = "{team}")
//    List<Member> findAll();

    List<Member> findByUsername(String username);

    List<Member>findByUsernameAndAgeGreaterThan(String username , int age);

    List<Member>findHelloBy(); //member전체 조회

    @Query("select m from Member m where m.username = :username and m.age= :age")
    List<Member>findUser(@Param("username")String username , @Param("age")int age);

    @Query("select m.username from Member m")
    List<String>findUsernameList();

    @Query("select new study.datajpa.dto.MemberDto(m.id , m.username , t.name) from Member m join m.team t")
    List<MemberDto>findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    List<Member>findListByUsername(String username);

    Member findMemberByUsername(String username);

    @Query("select m from Member m where m.username = :username")
    Optional<Member>findOptionByusername(@Param("username") String username);

    Page<Member> findByAge(int age , Pageable pageable);

    //벌크 수정 쿼리
    //벌크 연산은 영속성 컨텍스트에 반영이 되지 않고 바로 디비에 적용이 된다. 그래서 flush , clear를 해야되지만 true로 설정하면 이것을 한다.
    @Modifying(clearAutomatically = true)// -> excuteUpdate 실행
    @Query("update  Member  m set m.age=m.age+1 where m.age >=:age")
    int builkAgePlus(@Param("age")int age);

//    @Modifying
    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = :num where  m.username = :username")
    int updateOneMemberAge(@Param("username") String username ,  @Param("num") int num);


    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findFetchJoin();

    List<Member>findByAge(@Param("age")int age);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly" ,value = "true"))
    Member findReadOnlyByUsername(String username);
}
