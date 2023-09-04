package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.BookVo;

public class BookDao {
	public boolean updateRent(BookVo vo) {
		boolean result = false;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      
	      try {
	         //1. JDBC Driver Class 로딩
	         Class.forName("org.mariadb.jdbc.Driver");
	         
	         //2. 연결하기
	         String url = "jdbc:mariadb://192.168.64.3:3307/webdb?charset=utf8";
	         conn = DriverManager.getConnection(url, "webdb", "webdb");
	         
	         //3. SQL 준비.
	         String sql = 
	               "update book" + 
	               " set rent = ?"+ 
	               " where no = ?;";
	         pstmt = conn.prepareStatement(sql);
	         
	         //4. binding
	         pstmt.setString(1, vo.getRent());
	         pstmt.setInt(2, vo.getNo());
	         
	         //5. SQL 실행
	         int count = pstmt.executeUpdate();
	         
	         //6. 결과 처리
				result = count == 1;
	         
	      } catch (ClassNotFoundException e) {
	         System.out.println("드라이버 로딩 실패:" + e);
	      } catch (SQLException e) {
	         System.out.println("error:" + e);
	      } finally {
	         try {
	            //7. 자원정리
	            if(pstmt != null) {
	               pstmt.close();
	            }
	            if(conn != null) {
	               conn.close();
	            }
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	      }
	      return result;
}

	
	public List<BookVo> findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookVo> result = new ArrayList<>();
		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.64.3:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			//3. SQL 준비
			String sql =
				"select b.no, b.title, a.name, b.rent "+
				"from author a, book b " +
				"where a.no = b.author_no;";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
//			pstmt.setString(1, "%" + keyword + "%");
//			pstmt.setString(2, "%" + keyword + "%");
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			//6. 결과 처리
			
			while(rs.next()) {
				BookVo vo = new BookVo();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setAuthor(rs.getString(3));
				vo.setRent(rs.getString(4));
				
				System.out.println(vo.getNo()+":"+vo.getTitle() + ":" + vo.getAuthor() + ":" + vo.getRent());
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
