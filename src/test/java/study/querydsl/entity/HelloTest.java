package study.querydsl.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class HelloTest {

    @Autowired
    EntityManager entityManager;

    @Test
    public void contextLoad() throws Exception{
        //given
        Hello hello = new Hello();
        entityManager.persist(hello);
        //when
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QHello h = new QHello("h");
        //Then
        Hello result = query.selectFrom(h)
                .fetchOne();

        assertThat(result).isEqualTo(hello);
    }
}