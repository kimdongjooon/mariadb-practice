package bookshop.main;

import java.util.List;
import java.util.Scanner;

import bookshop.dao.BookDao;
import bookshop.vo.BookVo;

public class BookShop {
	public static void main(String[] args) {
		displayBookInfo();
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
			int no = scanner.nextInt();
			scanner.nextLine();
			
			BookVo vo = new BookVo();
			vo.setNo(no);
			vo.setRent("Y");
			
			new BookDao().updateRent(vo);
			
			// (2) Book 객체의 정보를 출력
			System.out.println("*****도서 정보 출력하기******");
			displayBookInfo();
			
			System.out.print("더 대여하시겠습니까?(종료:quit, 그 외 계속) : ");
			String option = scanner.nextLine();
			if("quit".equals(option)) {
				break;
			}
		}
		scanner.close();
		
		
		
		
	}
	
	public static void displayBookInfo() {
		System.out.println("***** 도서 정보 출력 *****");
		List<BookVo> list = new BookDao().findAll();
		for (BookVo vo : list) {
			System.out.println(vo);
		}
	}
}
