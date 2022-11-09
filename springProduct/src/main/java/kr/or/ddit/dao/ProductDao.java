package kr.or.ddit.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.ProductVO;

@Repository
public class ProductDao {
	//의존성 주입(Dependency Injection-DI)
	//제어의 역전(Inversion of Control - IoC)
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//PRODUCT 테이블에 insert
	public int insertProduct(ProductVO productVO) {
		//.insert("namespace.id",파라미터)
		return sqlSessionTemplate.insert("product.insert", productVO);
	}
	
	//상품 목록
	public List<ProductVO> list(String keyword) {
		return this.sqlSessionTemplate.selectList("product.list", keyword);
	}
	//상품 상세
	public ProductVO selectDetail(ProductVO productVO) {
		//.selectOne("namespace.id",파라미터)
		return sqlSessionTemplate.selectOne("product.select_detail",productVO);
	}
	
	//상품 수정 폼(문주)
	public ProductVO detail(ProductVO productVO) {
		return this.sqlSessionTemplate.selectOne("product.select_detail", productVO);
	}
	
	//상품 정보 변경
	public int update(ProductVO productVO) {
		return this.sqlSessionTemplate.update("product.update", productVO);
	}
	
	//상품 삭제
	public int delete(String productId) {
		return this.sqlSessionTemplate.delete("product.delete", productId);
	}
	
	//CART 테이블에 insert
	public int insertCart(CartVO cartVO) {
		return this.sqlSessionTemplate.insert("product.insertCart",cartVO);
	}
	
	//ATTACH 테이블에 다중 insert
	public int insertAttach(List<AttachVO> attachVOList) {
		return this.sqlSessionTemplate.insert("product.insertAttach",attachVOList);
	}
	
	//PRODUCT테이블의 기본키 자동 생성
	public String getProductId() {
		//1행 select, 파라미터 없음
		return this.sqlSessionTemplate.selectOne("product.getProductId");
	}
}










