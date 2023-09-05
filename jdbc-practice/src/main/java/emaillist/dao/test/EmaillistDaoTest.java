package emaillist.dao.test;

import java.util.List;

import emaillist.dao.EmaillistDao;
import emaillist.vo.EmaillistVo;

public class EmaillistDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmaillistVo vo = new EmaillistVo();
		vo.setFirstName("둘");
		vo.setLastName("리3");
		vo.setEmail("asd2324@asd.com");
		
		testInsert(vo);
		testFindAll();
		testDeleteByEmail("asd2324@asd.com");
		testFindAll();
		
	}

	private static void testDeleteByEmail(String email) {
		new EmaillistDao().deleteByEmail(email);
		
	}

	private static void testFindAll() {
		// TODO Auto-generated method stub
		List<EmaillistVo> list = new EmaillistDao().findAll();
		for (EmaillistVo vo : list ) {
			System.out.println(vo);
		}
		
	}

	private static void testInsert(EmaillistVo vo) {
		new EmaillistDao().insert(vo);
		
	}

	
	

}
