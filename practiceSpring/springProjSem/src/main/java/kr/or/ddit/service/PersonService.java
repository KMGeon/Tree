package kr.or.ddit.service;

import kr.or.ddit.vo.PersonVO;

public interface PersonService {
    public int joinPerson(PersonVO personVO);
    public  int registerCheck(String email);
}
