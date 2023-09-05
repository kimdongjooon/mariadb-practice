package bookmall.dao.test;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CategoryVo vo = new CategoryVo();
		vo.setCategory("임의 카테고리");
		testCategoryInsert(vo);
		testCategoryFindAll();
	}

	private static void testCategoryInsert(CategoryVo vo) {
		new CategoryDao().insert(vo);
		
	}

	private static void testCategoryFindAll() {
		new CategoryDao().findAll();
		
	}

	

}
