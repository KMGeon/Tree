package kr.or.ddit.mapper;

import kr.or.ddit.vo.PersonVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonMapper {
    public int joinPerson(PersonVO personVO);
    public  int registerCheck(String email);
}
