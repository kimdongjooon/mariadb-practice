package bookmall.main;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrderDao;
import bookmall.vo.CartVo;
import bookmall.vo.OrderVo;

public class BookMall {

	public static void main(String[] args) {
		MemberDao memberDao = new MemberDao();
//		memberDao.insert(memberVo1);
//		memberDao.insert(memberVo2);
		

		System.out.println("## 회원리스트");
		memberDao.findAll();

		System.out.println("## 카테고리");
		new CategoryDao().findAll();
		

		System.out.println("## 상품");
		new BookDao().findAll();
		
		System.out.println("## 카트");
		List<CartVo> cart_list = new CartDao().findAll();
		for(CartVo vo : cart_list) {
			System.out.println(vo);
		}
		
		System.out.println("## 주문");
		List<OrderVo> orders_list = new OrderDao().findAll();
		for(OrderVo vo : orders_list) {
			System.out.println(vo);
		}
		
		System.out.println("## 주문도서");

	}

}
