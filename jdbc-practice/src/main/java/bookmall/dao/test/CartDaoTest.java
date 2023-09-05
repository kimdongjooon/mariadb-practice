package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CartDao;
import bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		int no= 1;
		testCartInsert(new CartVo(1,2,1));// book_no,quntity,member_no
		testCartInsert(new CartVo(2,3,1));
		testCartInsert(new CartVo(3,1,1));
//		testCartDelete(no);
		testCartFindAll();
		

	}
	private static void testCartInsert(CartVo vo) {
		new CartDao().insert(vo);
		
	}
	
	private static void testCartFindAll() {
		List<CartVo> list = new CartDao().findAll();
		for(CartVo vo : list) {
			System.out.println(vo);
		}
		
	}

	private static void testCartDelete(int no) {
		new CartDao().delete(no);
		
	}

	

}
