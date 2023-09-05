package bookmall.dao.test;

import bookmall.dao.OrderDao;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

public class OrderDaoTest {

	public static void main(String[] args) {
		
		OrderVo vo = new OrderVo(1,"강북 미아동");
//		OrderBookVo bvo = new  OrderBookVo();
		testOrderInsert(vo);
//		testOrderFindAll();
		
	}

	private static void testOrderFindAll() {
		new OrderDao().findAll();
		
	}

	private static void testOrderInsert(OrderVo vo) {
		new OrderDao().insert(vo);
		
	}

}
