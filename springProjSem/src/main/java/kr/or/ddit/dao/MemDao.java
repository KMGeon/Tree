package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemVO;

@Repository
public class MemDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	public List<MemVO> list(Map<String,String> map){
		return this.sqlSessionTemplate.selectList("mem.memList", map);
	}


	public int insert(MemVO memVO) {
		return this.sqlSessionTemplate.insert("mem.memInsert", memVO);
	}
	
	//MEM 테이블의 전체 행 수 구함
	//<select id="getTotal" resultType="int">
	public int getTotal(Map<String,String> map) {
		return this.sqlSessionTemplate.selectOne("mem.getTotal",map);
	}
	
	//아이디 중복체크
	public int chkDup(String memId) {
		return this.sqlSessionTemplate.selectOne("mem.chkDup",memId);
	}
}










