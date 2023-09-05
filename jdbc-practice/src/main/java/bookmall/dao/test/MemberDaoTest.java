package bookmall.dao.test;

import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;

public class MemberDaoTest {

	public static void main(String[] args) {
		MemberVo vo = new MemberVo("KIM12","KDG1@aaa.com","123421","010-2434-2222");
		testMemberInsert(vo);
		testMemberFindAll();

	}

	private static void testMemberFindAll() {
		new MemberDao().findAll();
		
	}

	private static void testMemberInsert(MemberVo vo) {
		boolean result = new MemberDao().insert(vo);
		System.out.println(result);
		
	}

}
