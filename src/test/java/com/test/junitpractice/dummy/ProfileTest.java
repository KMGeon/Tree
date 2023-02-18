package com.test.junitpractice.dummy;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileTest {
    @Test
    public void matchAnser() throws Exception{
        //given
        Profile profile = new Profile("Buill Hockey , Inc");
        Question question = new BooleanQuestion(1, "Got bonuses?");
        Answer answer = new Answer(question, Bool.FALSE);
        profile.add(answer);
        //when
        Criteria criteria = new Criteria();
        Answer criteriaAnsert = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criteriaAnsert, Weight.MustMatch);
        criteria.add(criterion);
        //Then


    }

}