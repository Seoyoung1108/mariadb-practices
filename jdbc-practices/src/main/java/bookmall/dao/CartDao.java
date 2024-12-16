package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CartVo;

public class CartDao {
	
	public List<CartVo> findByUserNo(int user_no) {
		List<CartVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			conn = getConnection();
			
			// 3. Statement 준비하기
			String sql = "select c.quantity, c.user_no, c.book_no, b.title"
					+ " from cart c, book b"
					+ " where c.book_no = b.no and c.user_no = ?"
					+ " order by c.book_no asc";
			pstmt = conn.prepareStatement(sql);
			
			// 4. parameter binding
			pstmt.setInt(1, user_no);
			
			// 5. SQL 실행
			rs=pstmt.executeQuery();
			
			// 6. 결과 처리
			while(rs.next()) {
				int quantity = rs.getInt(1);
				// int user_no = rs.getInt(2);
				int book_no = rs.getInt(3);
				String book_title = rs.getString(4);
				
				CartVo vo = new CartVo();
				vo.setQuantity(quantity);
				vo.setUserNo(user_no);
				vo.setBookNo(book_no);
				vo.setBookTitle(book_title);
				result.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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
	
	public Long count() {
		Long result = 0L;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			conn = getConnection();
			
			// 3. Statement 준비하기
			String sql = "select count(*) from cart";
			pstmt = conn.prepareStatement(sql);
			
			// 5. SQL 실행
			rs=pstmt.executeQuery();
			
			// 6. 결과 처리
			if(rs.next()) {
				result=rs.getLong(1);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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
	
	public Boolean insert(CartVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			// 3. Statement 준비하기
			String sql = "insert into cart values (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			// 4. parameter binding
			pstmt.setInt(1, vo.getQuantity());
			pstmt.setInt(2, vo.getUserNo());
			pstmt.setInt(3, vo.getBookNo());

			// 5. SQL 실행
			int count = pstmt.executeUpdate(); // 데이터 변경
			
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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
	
	public Boolean deleteByUserNoAndBookNo(int user_no, int book_no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			// 3. Statement 준비하기
			String sql = "delete from cart where user_no = ? and book_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			// 4. parameter binding
			pstmt.setInt(1, user_no);
			pstmt.setInt(2, book_no);
			
			// 5. SQL 실행
			int count = pstmt.executeUpdate(); // 데이터 변경
			
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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
	
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
		// 1. JDBC Driver 로딩
		Class.forName("org.mariadb.jdbc.Driver");
		
		// 2. 연결하기
		String url = "jdbc:mariadb://192.168.0.153:3306/bookmall";
		conn = DriverManager.getConnection(url, "bookmall", "bookmall");	
		
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		
		return conn;
	}
}
