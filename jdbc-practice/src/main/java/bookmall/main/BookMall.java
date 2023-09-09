package bookmall.main;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrderDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

public class BookMall {

	public static void main(String[] args) {
		MemberDao memberDao = new MemberDao();
		MemberVo memberVo1 = new MemberVo("홍길동","hong@google.com","1234","010-1111-2222");
		MemberVo memberVo2 = new MemberVo("성춘향","sung@gmail.com","5678","010-5475-4444");
		memberDao.insert(memberVo1);
		memberDao.insert(memberVo2) ;

		System.out.println("## 회원리스트");
		
		memberDao.findAll();

		System.out.println("## 카테고리");
		CategoryDao categoryDao = new CategoryDao();
		categoryDao.insert(new CategoryVo("소설"));
		categoryDao.insert(new CategoryVo("수필"));
		categoryDao.insert(new CategoryVo("경제"));
		new CategoryDao().findAll();
		

		System.out.println("## 상품 ");
		BookDao bookdao = new BookDao();
		bookdao.insert(new BookVo("성냥팔이 소녀",9900,1));
		bookdao.insert(new BookVo("수필은 이렇게 쓴다",13500,2));
		bookdao.insert(new BookVo("더 플로",18900,3));
		new BookDao().findAll();
		
		System.out.println("## 카트");
		CartDao cartdao = new CartDao();
		// 1번(성냥팔이 소녀) 책을 2권을 1번(홍길동)이 카드에 담기.
		cartdao.insert(new CartVo(1,2,memberVo1)); 
		// 3번(더 플로) 책을 5권을 1번(홍길동)이 카드에 담기.
		cartdao.insert(new CartVo(3,5,memberVo1)); 
		
		List<CartVo> cart_list = new CartDao().findAll(memberVo1);
		for(CartVo vo : cart_list) {
			System.out.println(vo);
		}
		
		System.out.println("## 주문");
		OrderDao orderdao = new OrderDao();
		orderdao.insert(new OrderVo(memberVo1,"서울시 강북구 미아동"));
		
		List<OrderVo> orders_list = new OrderDao().findAll(memberVo1);
		for(OrderVo vo : orders_list) {
			System.out.println(vo);
		}
		
		System.out.println("## 주문도서");
		List<OrderBookVo> list = new OrderDao().orderBookFindAll(memberVo1);
		for(OrderBookVo vo : list) {
			System.out.println(vo);
		}

	}

}
