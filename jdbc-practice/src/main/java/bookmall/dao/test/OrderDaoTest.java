package bookmall.dao.test;

import bookmall.dao.OrderDao;
import bookmall.vo.MemberVo;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

public class OrderDaoTest {

	public static void main(String[] args) {
		MemberVo memberVo1 = new MemberVo("홍길동","hong@google.com","1234","010-1111-2222");
		memberVo1.setNo(1);
//		OrderVo vo = new OrderVo(1,"강북 미아동");
//		OrderBookVo obvo = new OrderBookVo();
//		testOrderInsert(vo);
		testOrderFindAll(memberVo1);
		testOrderBookFindAll(memberVo1);
		
	}

	private static void testOrderBookFindAll(MemberVo mvo) {
		for(OrderBookVo vo : new OrderDao().orderBookFindAll(mvo)) {
			System.out.println(vo);
		}
		
	}

	private static void testOrderFindAll(MemberVo mvo) {
		for(OrderVo vo : new OrderDao().findAll(mvo)) {
			System.out.println(vo);
		}
		
	}

	private static void testOrderInsert(OrderVo vo) {
		new OrderDao().insert(vo);
		
	}

}
