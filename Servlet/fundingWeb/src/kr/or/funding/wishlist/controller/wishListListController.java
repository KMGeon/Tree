package kr.or.funding.wishlist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.funding.wishlist.service.IWishListService;
import kr.or.funding.wishlist.service.WishListServiceimpl;
import kr.or.funding.wishlist.vo.WishlistVO;

@WebServlet(value="/wishlist/wishList.do")
public class wishListListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		IWishListService wishService = WishListServiceimpl.getInstance();
		
		List<WishlistVO> wishList = wishService.findAll();
		
		req.setAttribute("wishList", wishList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/wishLlist_List.jsp");
		
		dispatcher.forward(req, resp);
		
		System.out.println("wishList= " + wishList);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
