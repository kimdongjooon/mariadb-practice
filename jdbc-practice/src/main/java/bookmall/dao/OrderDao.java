package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CartVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

public class OrderDao {

	
	// cart에서 결제할 도서 목록들을 합을 order테이블에 insert 일단 하나씩.
	// 동시에 세부 결제사항을 order book에 insert
	// 주문 완료시 cart 주문내역 삭제하기.
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
		List<OrderBookVo> result = new ArrayList<>();

		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.64.3:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
			
			//3-0.멤버 email 받아서 no 뽑아서 가져와서 변수 저장하기
			String sql0=
					"select no from member where email = ?";
			pstmt = conn.prepareStatement(sql0);
			
			pstmt.setString(1,vo.getEmail());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setMember_no(rs.getInt(1));;
			}
			pstmt.close();
			rs.close();
			
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
			
			pstmt.close();
			rs.close();
			
			// 3-3. SQL 준비(orders insert 하기)
			String sql3 =
					"insert into orders values(null,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql3);
			
			//4-3. 값 바인딩.
			pstmt.setInt(1, total_price);
			pstmt.setInt(2, vo.getMember_no());
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setString(5, vo.getAddress());
			
			
			//5-3. SQL 실행.(order)
			pstmt.executeUpdate();
			
			//6-3. 결과 실행 
//			if(count == 1){System.out.println(name+"님 주문완료.") ;}
			
			pstmt.close();
			rs.close();
			
			//3-4. SQL 준비(주문 테이블 최신 no 가져오기)
			String sql4 =
				"select no " +
				"from orders "+
				"where member_no = ? " +
				"order by no desc "+
				"limit 1";
			pstmt = conn.prepareStatement(sql4);
			
			//4-4. binding
			pstmt.setInt(1, vo.getMember_no());
			
			//5-4. SQL 실행
			rs = pstmt.executeQuery();
			
			
			//6-4. 결과 처리 넘버 무조건 1개.
			if(rs.next()) {
				vo.setNo(rs.getInt(1));
			}
			pstmt.close();
			rs.close(); 
			
			//3-5. SQL 준비(quntity, price 가져와서 변수 저장하기)
			String sql5 =
				"select a.no, d.no, c.quntity, d.price "+
				"from orders a, member b, cart c, book d "+
				"where a.member_no = b.no "+
				"and b.no = c.member_no "+
				"and c.book_no = d.no "+
				"and a.member_no = ? " +
				"and a.no = ?";
			pstmt = conn.prepareStatement(sql5);
			
			//4-5. binding
			pstmt.setInt(1, vo.getMember_no());
			pstmt.setInt(2, vo.getNo());
			
			//5-5. SQL 실행
			rs = pstmt.executeQuery();
			
			
			//6-5. 결과 처리
			while(rs.next()) {
				OrderBookVo obvo =new OrderBookVo();
				obvo.setOrder_no(rs.getInt(1));
				obvo.setBook_no(rs.getInt(2));
				obvo.setQuntity(rs.getInt(3));
				obvo.setPrice(rs.getInt(4));
				
				result.add(obvo);
				
			}
			pstmt.close();
			rs.close();
			
			// 3-6. SQL 준비(orders_book insert 하기)
			String sql6 =
					"insert into order_book values(?,?,?,?,null)";
			pstmt = conn.prepareStatement(sql6);
			// for each문으로 한꺼번에 다 넣기.
			for(OrderBookVo obvo : result) {
				//4-6. 값 바인딩.
				pstmt.setInt(1, obvo.getBook_no());
				pstmt.setInt(2, obvo.getOrder_no());
				pstmt.setInt(3, obvo.getQuntity());
				pstmt.setInt(4, obvo.getPrice());
			
				//5-6. SQL 실행.(order book)
				pstmt.executeUpdate();
				
				//6-6. 결과 실행 
//				System.out.println("orderBookUpate: "+ (count5 == 1));
			}
			
			// 3-7. SQL 준비(주문완료 후 cart 책 내역 삭제.)
			String sql7 =
					"DELETE FROM cart WHERE member_no = ?;";
			pstmt = conn.prepareStatement(sql7);
			// 4-7 값 바인딩.
			pstmt.setInt(1, vo.getMember_no());
			// 5-7 SQL 실행.
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 7. 자원정리
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
	public List<OrderVo> findAll(MemberVo mvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OrderVo> result = new ArrayList<>(); 
		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.64.3:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
			
			//3-1.멤버 email 받아서 no 뽑아서 가져와서 변수 저장하기
			String sql1=
					"select no from member where email = ?";
			pstmt = conn.prepareStatement(sql1);
			
			pstmt.setString(1,mvo.getEmail());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mvo.setNo(rs.getInt(1));
			}
			pstmt.close();
			rs.close();
			
			//3. SQL 준비
			String sql2 =
				"select no,name,total_price,email,address "+
				"from orders " +
				"where member_no = ?";
			pstmt = conn.prepareStatement(sql2);
			
			//4. binding
			pstmt.setInt(1,mvo.getNo());

			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			//6. 결과 처리
			while(rs.next()) {
				OrderVo vo = new OrderVo();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setTotal_price(rs.getInt(3));
				vo.setEmail(rs.getString(4));
				vo.setAddress(rs.getString(5));
				
				result.add(vo);
			}
			
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
		
		
		
		return result;
		
	}

	
	
	
	// orderBookFindAll : order_book 테이블(주문 번호, 책고유 번호(책이름), 가격 , 수량, 총가격.
	public List<OrderBookVo> orderBookFindAll(MemberVo mvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OrderBookVo> result = new ArrayList<>(); 
		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.64.3:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

			//3-1.멤버 email 받아서 no 뽑아서 가져와서 변수 저장하기
			String sql1=
					"select no from member where email = ?";
			pstmt = conn.prepareStatement(sql1);
			
			pstmt.setString(1,mvo.getEmail());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mvo.setNo(rs.getInt(1));
			}
			pstmt.close();
			rs.close();
			
			//3. SQL 준비
			String sql2 =
				"select b.no, b.name, c.title, a.price, a.quntity,  (a.price*a.quntity) "+
				"from order_book a, orders b, book c "+
				"where a.order_no = b.no "+
				"and a.book_no = c.no "+
				"and b.member_no = ? "+
				"order by b.no asc";
			pstmt = conn.prepareStatement(sql2);
			
			//4. binding
			pstmt.setInt(1,mvo.getNo());

			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			//6. 결과 처리
			while(rs.next()) {
				OrderBookVo obvo = new OrderBookVo();
				obvo.setOrder_no(rs.getInt(1));
				obvo.setMemberName(rs.getString(2));
				obvo.setBookName(rs.getString(3));
				obvo.setPrice(rs.getInt(4));
				obvo.setQuntity(rs.getInt(5));
				obvo.setPrice_mul_quntity(rs.getInt(6));
				
				
				result.add(obvo);
			}
			
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
		
		
		
		return result;
		
	}
	// 
}
