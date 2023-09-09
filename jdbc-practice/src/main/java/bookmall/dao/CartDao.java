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

public class CartDao {

	
	// 카트에 책 담기. (no와 수량을 입력 받아서 insert)
	// 입력 받은 no는 book_no 
	public void insert(CartVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
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
			pstmt.setString(1,vo.getEmail());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setMember_no(rs.getInt(1));
			}
			pstmt.close();
			rs.close();
			//3-2. sql준비.
			
			String sql2 =
					"insert into cart values(null,?,?,?)";
			pstmt = conn.prepareStatement(sql2);
			
			
			//4. 값 바인딩.
			pstmt.setInt(1, vo.getQuntity());
			pstmt.setInt(2, vo.getBookNo());
			pstmt.setInt(3, vo.getMember_no());
			
			
			//5. SQL 실행.
			int count = pstmt.executeUpdate();
			
			//6. 결과 처리.
//			System.out.println("cartUpate: "+ (count == 1));
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 6. 자원정리
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

	
	// 카트에서 책 삭제 하기 (no 기반으로 입력시 해당 행 삭제 delete)
	public void delete(int no) {
		
		
		
	}
	
	
	// 카트 정보 불러오기 ( 도서제목, 수량, 가격 select)
	public List<CartVo> findAll(MemberVo mvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CartVo> result = new ArrayList<>(); 
		
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
				"select b.no, c.category, a.title, a.price, b.quntity, (a.price*b.quntity) "+
				"from book a, cart b, category c "+
				"where a.no = b.book_no "+
				"and a.category_no = c.no "+
				"and b.member_no = ?";
			pstmt = conn.prepareStatement(sql2);
			
			//4. binding
			pstmt.setInt(1, mvo.getNo());

			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			
			//6. 결과 처리
			while(rs.next()) {
				CartVo vo = new CartVo();
				vo.setNo(rs.getInt(1));
				vo.setCategory(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setPrice(rs.getInt(4));
				vo.setQuntity(rs.getInt(5));
				vo.setTotal_price(rs.getInt(6));
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
}
