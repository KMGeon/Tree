package study.querydsl.study;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import study.querydsl.HAHA.domain.Board;
import study.querydsl.HAHA.domain.User;
import study.querydsl.study.entity.Member;
import study.querydsl.study.entity.Team;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitMember {

    private final InitMemberService initMemberService;

    @PostConstruct
    public void inti() {
        initMemberService.init();
    }

    @Component
    static class InitMemberService {
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init() {
            User teamA = new User("teamA");
            User teamB = new User("teamB");
            em.persist(teamA);
            em.persist(teamB);

            for (int i = 0; i < 100; i++) {
                User selectedTeam = i % 2 == 0 ? teamA : teamB;
                em.persist(new Board("board"+i,selectedTeam));
            }
        }
//        @Transactional
//        public void init() {
//            Team teamA = new Team("teamA");
//            Team teamB = new Team("teamB");
//            em.persist(teamA);
//            em.persist(teamB);
//
//            for (int i = 0; i < 100; i++) {
//                Team selectedTeam = i % 2 == 0 ? teamA : teamB;
//                em.persist(new Member("member" + i, i, selectedTeam));
//            }
//        }

    }

}
