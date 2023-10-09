package kr.or.funding.ptImfor;

import java.util.List;

public interface IPtImforDAO {
	
	// 상품등록
	public int insertPtRegister(PtImforVO pv);

	// 상품 출력
	public List<PtImforVO> findall();

	// 상품 출력 조인 테이블
	public List<PtImforVO> acthAll();

	public List<PtImforVO> selectDetail(PtImforVO pv);
	
	
}
