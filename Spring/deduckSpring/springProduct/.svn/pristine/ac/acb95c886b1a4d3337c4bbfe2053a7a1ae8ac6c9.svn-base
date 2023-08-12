package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.ProductVO;

public interface ProductService {
	//메소드 시그니처
	
	//PRODUCT 테이블에 insert
	public int insertProduct(ProductVO productVO);

	//상품 목록
	public List<ProductVO> list(String keyword);
	
	//상품 상세
	public ProductVO selectDetail(ProductVO productVO);
	
	//상품 정보 변경
	public int update(ProductVO productVO);

	// 상품 삭제
	public int delete(String productId);
}
