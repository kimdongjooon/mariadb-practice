package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.BookVo;

public class BookDao {

	public void findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookVo> result = new ArrayList<>(); 
		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.64.3:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

			//3. SQL 준비
			String sql =
				"select a.no, a.title, a.price, b.category "+
				"from book a, category b "+
				"where a.category_no = b.no "+
				"and b.category like ('%') "+
				"order by a.no ";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			BookVo vo = new BookVo();
			//6. 결과 처리
			while(rs.next()) {
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPrice(rs.getInt(3));
				vo.setCategory_str(rs.getString(4));
				
				System.out.println(vo.toString());
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
		
	}

	public boolean insert(BookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.64.3:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

			//3. sql준비.
			
			String sql =
					"insert" +
				    " into book" +
					" values (null, ?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			//4. 값 바인딩.
			pstmt.setString(1, vo.getTitle());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setInt(3, vo.getCategory_no());
			
			
			//5. SQL 실행.
			int count = pstmt.executeUpdate();
			
			//6. 결과 처리.
			result = count == 1;
			
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
		
		return result;
		
	}

		

	

}
