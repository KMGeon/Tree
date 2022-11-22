package kr.or.ddit.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.BookVO;

//매퍼 인터페이스
@Mapper
public interface ProdMapper {

	public List<Map<String, Object>> amtSale();
}
