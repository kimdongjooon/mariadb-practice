package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CartVo;
import bookmall.vo.OrderVo;

public class OrderDao {

	
	// cart에서 결제할 도서 목록들을 합을 order테이블에 insert 일단 하나씩.
	// 동시에 세부 결제사항을 order book에 insert
	public void insert(OrderVo vo) {
		// vo의 member_no을 기반으로 select해서 
		// member 테이블에서 이름과 이메일을 가져오고
		// cart 테이블에서 총액을 가져온다.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		String name = null;
		String email = null;
		int total_price = 0;
		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.64.3:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

			//3-1. SQL 준비(name, email 가져와서 변수 저장하기)
			String sql1 =
				"select name, email " +
				"from member " +
				"where no = ?";
			pstmt = conn.prepareStatement(sql1);
			
			//4-1. binding
			pstmt.setInt(1, vo.getMember_no());
			
			//5-1. SQL 실행
			rs = pstmt.executeQuery();
			
			
			//6-1. 결과 처리
			while(rs.next()) {
				name = rs.getString(1);
				email = rs.getString(2);
				System.out.println(name+":"+email);
			}
			
			pstmt.close();
			rs.close();
			
			//3-2. SQL 준비(quntity, price 가져와서 변수 저장하기)
			String sql2 =
				"select a.quntity, b.price "+
				"from cart a, book b "+
				"where a.book_no = b.no "+
				"and a.member_no = ? ";
			pstmt = conn.prepareStatement(sql2);
			
			//4-2. binding
			pstmt.setInt(1, vo.getMember_no());
			
			//5-2. SQL 실행
			rs = pstmt.executeQuery();
			
			
			//6-2. 결과 처리
			while(rs.next()) {
				total_price += rs.getInt(1)*rs.getInt(2);
				
			}
			System.out.println("총액: "+total_price);
			pstmt.close();
			rs.close();
			
			
			// 3-3. SQL 준비(insert 하기)
			String sql =
					"insert into orders values(null,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			//4. 값 바인딩.
			pstmt.setInt(1, total_price);
			pstmt.setInt(2, vo.getMember_no());
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setString(5, vo.getAddress());
			
			
			//5. SQL 실행.
			int count = pstmt.executeUpdate();
			
			//6. 결과 실행 
			System.out.println("orderUpate: "+ (count == 1));
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 6. 자원정리
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	// findall : order 테이블(주문번호, 주문자번호(주문자 이름), 주문 도서 총액, 주문자 email, 주문자 주소
	//                       자동         member             cart         member     입력. 
	public void findAll() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	// findall : order_book 테이블(주문 번호, 책고유 번호(책이름), 가격 , 수량, 총가격.
	
	// 
}
