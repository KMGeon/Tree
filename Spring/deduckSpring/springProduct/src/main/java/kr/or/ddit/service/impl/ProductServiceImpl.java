package kr.or.ddit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.dao.ProductDao;
import kr.or.ddit.service.ProductService;
import kr.or.ddit.util.FileUploadUtil;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService{
	//의존성 주입(DI)
	//제어의 역전(IoC)
	@Autowired
	ProductDao productDao;
	
	@Autowired
	FileUploadUtil fileUploadUtil;
		
	//PRODUCT 테이블에 insert
	@Override
	public int insertProduct(ProductVO productVO) {	
		//PRODUCT 테이블에 insert
		int result = this.productDao.insertProduct(productVO);
		//ATTACH 테이블에 다중 insert
		if(result > 0) {//insert 성공 시			
			//파일업로드 및 insert 수행 1)=>실패
//			FileUploadUtil.fileUploadAction(productVO.getProductImage(), 
//					productVO.getProductId());
			//파일업로드 및 insert 수행 2)=>성공
			fileUploadUtil.fileUploadAction(productVO.getProductImage(), 
					productVO.getProductId());
		}
		
		return result;
	}
	
	// 상품 목록
	@Override
	public List<ProductVO> list(String keyword) {
		return this.productDao.list(keyword);
	}
	//상품 상세
	@Override
	public ProductVO selectDetail(ProductVO productVO) {
		return this.productDao.selectDetail(productVO);
	}
	
	//상품 정보 변경
	@Override
	public int update(ProductVO productVO) {
		return productDao.update(productVO);
	}
	
	//상품 삭제
	@Override
	public int delete(String productId) {
		return productDao.delete(productId);
	}

	//CART 및 CART_DET 테이블에 insert
	@Override
	public int thankCustomer(CartVO cartVO) {
		//1. CART 테이블에 insert
		int cartInCnt = this.productDao.insertCart(cartVO);
		log.info("cartInCnt : " + cartInCnt);
		
		//2. CART_DET 테이블에 insert
		
		return 0;
	}
	
	//ATTACH 테이블에 다중 insert
	@Override
	public int insertAttach(List<AttachVO> attachVOList) {
		return this.productDao.insertAttach(attachVOList);
	}
	
	//PRODUCT테이블의 기본키 자동 생성
	@Override
	public String getProductId() {
		return this.productDao.getProductId();
	}
}





		
		
		
		
		

