package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

public class OrderDao {
	
	public OrderVo findByNoAndUserNo(Long no, int user_no) {
		// List<OrderVo> result = new ArrayList<>();
		OrderVo result = new OrderVo();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			conn = getConnection();
			
			// 3. Statement 준비하기
			String sql = "select no, number, payment, shipping, status, user_no from orders where no = ? and user_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			// 4. parameter binding
			int ino = no.intValue();
			pstmt.setInt(1, ino);
			pstmt.setInt(2, user_no);
			
			// 5. SQL 실행
			rs=pstmt.executeQuery();
			
			// 6. 결과 처리
			while(rs.next()) {
				// int no = rs.getInt(1);
				String number = rs.getString(2);
				int payment = rs.getInt(3);
				String shipping = rs.getString(4);
				String status = rs.getString(5);
				// int user_no = rs.getInt(6);
				
				result.setNo(ino);
				result.setNumber(number);
				result.setPayment(payment);
				result.setShipping(shipping);
				result.setStatus(status);
				result.setUserNo(user_no);
				return result;
				//result.add(vo);
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
		
		return null;
	}
	
	public OrderVo findByNoAndUserNo(int no, int user_no) {
		// List<OrderVo> result = new ArrayList<>();
		OrderVo result = new OrderVo();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			conn = getConnection();
			
			// 3. Statement 준비하기
			String sql = "select no, number, payment, shipping, status, user_no from orders where no = ? and user_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			// 4. parameter binding
			pstmt.setInt(1, no);
			pstmt.setInt(2, user_no);
			
			// 5. SQL 실행
			rs=pstmt.executeQuery();
			
			// 6. 결과 처리
			while(rs.next()) {
				// int no = rs.getInt(1);
				String number = rs.getString(2);
				int payment = rs.getInt(3);
				String shipping = rs.getString(4);
				String status = rs.getString(5);
				// int user_no = rs.getInt(6);
				
				result.setNo(no);
				result.setNumber(number);
				result.setPayment(payment);
				result.setShipping(shipping);
				result.setStatus(status);
				result.setUserNo(user_no);
				
				return result;
				//result.add(vo);
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
		
		return null;
	}
	
	public List<OrderBookVo> findBooksByNoAndUserNo(int orders_no, int user_no) {
		List<OrderBookVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			conn = getConnection();
			
			// 3. Statement 준비하기
			String sql = "select ob.quantity, ob.price, ob.book_no, ob.orders_no, o.user_no, b.title"
					+ " from orders_book ob, orders o, book b"
					+ " where ob.book_no = b.no and ob.orders_no = o.no and ob.orders_no = ? and o.user_no = ?"
					+ " order by ob.book_no asc";
			pstmt = conn.prepareStatement(sql);
			
			// 4. parameter binding
			pstmt.setInt(1, orders_no);
			pstmt.setInt(2, user_no);
			
			// 5. SQL 실행
			rs=pstmt.executeQuery();
			
			// 6. 결과 처리
			while(rs.next()) {
				int quantity = rs.getInt(1);
				int price = rs.getInt(2);
				int book_no = rs.getInt(3);
				String book_title = rs.getString(6);
				
				OrderBookVo vo = new OrderBookVo();
				vo.setQuantity(quantity);
				vo.setPrice(price);
				vo.setBookNo(book_no);
				vo.setOrderNo(orders_no);
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
			String sql = "select count(*) from orders";
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
	
	public Boolean insert(OrderVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			// 3. Statement 준비하기
			String sql = "insert into orders values (null, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			// 4. parameter binding
			pstmt.setString(1, vo.getNumber());
			pstmt.setInt(2, vo.getPayment());
			pstmt.setString(3, vo.getShipping());
			pstmt.setString(4, vo.getStatus());
			pstmt.setInt(5, vo.getUserNo());

			// 5. SQL 실행
			int count = pstmt.executeUpdate(); // 데이터 변경
			
			result = count == 1;
			ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                vo.setNo(rs.getInt(1));
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
	
	public Boolean insertBook(OrderBookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			// 3. Statement 준비하기
			String sql = "insert into orders_book values (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			// 4. parameter binding
			pstmt.setInt(1, vo.getQuantity());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setInt(3, vo.getBookNo());
			pstmt.setInt(4, vo.getOrderNo());

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
	
	public Boolean deleteByNo(int no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			// 3. Statement 준비하기
			String sql = "delete from orders where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			// 4. parameter binding
			pstmt.setInt(1, no);
			
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
	
	public Boolean deleteBooksByNo(int orders_no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			// 3. Statement 준비하기
			String sql = "delete from orders_book where orders_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			// 4. parameter binding
			pstmt.setInt(1, orders_no);
			
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
