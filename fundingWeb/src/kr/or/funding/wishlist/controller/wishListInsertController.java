package kr.or.funding.wishlist.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.funding.wishlist.service.IWishListService;
import kr.or.funding.wishlist.service.WishListServiceimpl;
import kr.or.funding.wishlist.vo.WishlistVO;


@WebServlet(value="/wishlist/wishlistinsert.do")
public class wishListInsertController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/Funding/view/wishListDetailTest.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		req.setCharacterEncoding("UTF-8");
		
		//1. 찜리시트 가져오기.
		String ptNum = req.getParameter("ptNum"); // 상품번호
		String mbsNum = req.getParameter("mbsNum"); // 회원번호
		
		
		System.out.println("ptNum="+ptNum);
		System.out.println("mbsNum="+mbsNum);
		
		
		// 2. 서비스 객체 생성
		IWishListService wishlistService = WishListServiceimpl.getInstance();
		
		//3. 회원정보 등록하기
		WishlistVO wv = new WishlistVO();
		wv.setPtNum(Integer.parseInt(ptNum));	 // 상품번호 등록
		wv.setMbsNum(Integer.parseInt(mbsNum)); // 회원번호 등록
		
		
		int cnt = wishlistService.insertWishList(wv);
		
		String msg ="";
		if(cnt >0) {
			// 성공
			msg = "성공";
		}else {
			// 실패
			msg = "실패";
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("msg", msg);
		
	}
	
	
}
