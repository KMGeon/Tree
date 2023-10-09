package kr.or.ddit.service.impl;

import kr.or.ddit.mapper.PersonMapper;
import kr.or.ddit.service.PersonService;
import kr.or.ddit.vo.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonMapper personMapper;

    public int joinPerson(PersonVO personVO){
        return  this.personMapper.joinPerson(personVO);
    }

    @Override
    public int registerCheck(String email) {
        return this.personMapper.registerCheck(email);
    }

}
