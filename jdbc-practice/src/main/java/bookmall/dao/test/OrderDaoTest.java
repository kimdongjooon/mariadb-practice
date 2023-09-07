package bookmall.dao.test;

import bookmall.dao.OrderDao;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

public class OrderDaoTest {

	public static void main(String[] args) {
		
		OrderVo vo = new OrderVo(1,"강북 미아동");
		OrderBookVo obvo = new  OrderBookVo();
		testOrderInsert(vo);
		testOrderFindAll();
		testOrderBookFindAll();
		
	}

	private static void testOrderBookFindAll() {
		for(OrderBookVo vo : new OrderDao().orderBookFindAll()) {
			System.out.println(vo);
		}
		
	}

	private static void testOrderFindAll() {
		for(OrderVo vo : new OrderDao().findAll()) {
			System.out.println(vo);
		}
		
	}

	private static void testOrderInsert(OrderVo vo) {
		new OrderDao().insert(vo);
		
	}

}
