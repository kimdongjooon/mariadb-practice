package bookmall.dao.test;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		BookVo vo = new BookVo("스필버그의 말2",22500,2);
		testBookInsert(vo);
		testBookFindAll();

	}

	private static void testBookFindAll() {
		new BookDao().findAll();
		
	}

	private static void testBookInsert(BookVo vo) {
		System.out.println("BookInsert: "+new BookDao().insert(vo));
	}

}
