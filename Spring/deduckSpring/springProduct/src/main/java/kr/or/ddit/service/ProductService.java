package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.CartVO;
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
	
	//CART 및 CART_DET 테이블에 insert
	public int thankCustomer(CartVO cartVO);

	//ATTACH 테이블에 다중 insert
	public int insertAttach(List<AttachVO> attachVOList);

	//PRODUCT테이블의 기본키 자동 생성
	public String getProductId();
}





