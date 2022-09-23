package kr.or.funding.ptImfor;

import java.util.List;

public interface IPtImforService {
	
	// 상품목록 조회
	public List<PtImforVO> findAll();
	
	// 상품등록
	public int reaisterPtImfor(PtImforVO pv);

	// 상품목록 조인 조회
	public List<PtImforVO> atchAll();

	public List<PtImforVO> selectDetail(PtImforVO pv);

}