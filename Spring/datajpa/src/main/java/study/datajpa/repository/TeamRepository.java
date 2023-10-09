package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.datajpa.domain.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team , Long> {
}
