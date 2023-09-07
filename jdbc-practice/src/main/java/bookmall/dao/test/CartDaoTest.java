package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CartDao;
import bookmall.vo.CartVo;
import bookmall.vo.MemberVo;

public class CartDaoTest {

	public static void main(String[] args) {
		MemberVo memberVo1 = new MemberVo("홍길동","hong@google.com","1234","010-1111-2222");
		memberVo1.setNo(1);
//		testCartInsert(new CartVo(1,2,memberVo1));// book_no,quntity,member_no
//		testCartInsert(new CartVo(2,3,memberVo1));
//		testCartInsert(new CartVo(3,1,memberVo1));
//		testCartDelete(no);
		testCartFindAll(memberVo1);
		

	}
//	private static void testCartInsert(CartVo vo) {
//		new CartDao().insert(vo);
//		
//	}
	
	private static void testCartFindAll(MemberVo mvo) {
		List<CartVo> list = new CartDao().findAll(mvo);
		for(CartVo vo : list) {
			System.out.println(vo);
		}
		
	}

	private static void testCartDelete(int no) {
		new CartDao().delete(no);
		
	}

	

}
