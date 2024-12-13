package bookmall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import bookmall.dao1.BookDao;
import bookmall.dao1.CategoryDao;
import bookmall.dao1.UserDao;
import bookmall.vo1.BookVo;
import bookmall.vo1.CartVo;
import bookmall.vo1.CategoryVo;
import bookmall.vo1.UserVo;
import emaillist.dao.EmaillistDao;

public class BookMallTest2 {
	private static UserVo mockUserVo01 = new UserVo("데스트유저01", "mailto:test01@test.com", "1234", "010-0000-0000");
	private static UserVo mockUserVo02 = new UserVo("데스트유저02", "mailto:test02@test.com", "1234", "010-1111-1111");
	
	private static CategoryVo mockCategoryVo01 = new CategoryVo("인문");
	private static CategoryVo mockCategoryVo02 = new CategoryVo("컴퓨터/IT");
	private static CategoryVo mockCategoryVo03 = new CategoryVo("예술");
	
	private static BookVo mockBookVo01 = new BookVo("과학혁명의 구조", 20000);
	private static BookVo mockBookVo02 = new BookVo("J2EE Development Without EJB", 32000);
	private static BookVo mockBookVo03 = new BookVo("서양미술사", 50000);
	
	private static CartVo mockCartVo01 = new CartVo();
	private static CartVo mockCartVo02 = new CartVo();
	
	private static UserDao userDao = new UserDao();
	private static CategoryDao categoryDao = new CategoryDao();
	private static BookDao bookDao = new BookDao();
	//private static CartDao cartDao = new CartDao();
	
	@BeforeAll
	public static void setUp() {
		// 사용자 추가(2명)
		userDao.insert(mockUserVo01);
		userDao.insert(mockUserVo02);
		
		// 카테고리 등록(3개)
		categoryDao.insert(mockCategoryVo01);
		categoryDao.insert(mockCategoryVo02);
		categoryDao.insert(mockCategoryVo03);
		
		
		// 서적 등록(3개)
		mockBookVo01.setCategoryNo(mockCategoryVo01.getNo());
		bookDao.insert(mockBookVo01);

		mockBookVo02.setCategoryNo(mockCategoryVo02.getNo());
		bookDao.insert(mockBookVo02);

		mockBookVo03.setCategoryNo(mockCategoryVo03.getNo());		
		bookDao.insert(mockBookVo03);
		
		/*
		// 카트 담기(2개)
		mockCartVo01.setUserNo(mockUserVo01.getNo());
		mockCartVo01.setBookNo(mockBookVo01.getNo());
		mockCartVo01.setQuantity(1);
		cartDao.insert(mockCartVo01);
		
		mockCartVo02.setUserNo(mockUserVo01.getNo());
		mockCartVo02.setBookNo(mockBookVo02.getNo());
		mockCartVo02.setQuantity(2);		
		cartDao.insert(mockCartVo02);
		*/
	}
	
	
	@Test
	public void testUser() {
		assertEquals(2, userDao.findAll().size());
	}
	
	
	@Test
	public void testCategory() {
		assertEquals(3, categoryDao.findAll().size());
	}
	
	/*
	@Test
	public void testCart() {
		List<CartVo> list = cartDao.findByUserNo(mockUserVo01.getNo());
		
		assertEquals(2, list.size());		

		assertEquals(mockBookVo01.getNo(), list.get(0).getBookNo());
		assertEquals(mockBookVo01.getTitle(), list.get(0).getBookTitle());
		assertEquals(mockCartVo01.getQuantity(), list.get(0).getQuantity());

		assertEquals(mockBookVo02.getNo(), list.get(1).getBookNo());
		assertEquals(mockBookVo02.getTitle(), list.get(1).getBookTitle());		
		assertEquals(mockCartVo02.getQuantity(), list.get(1).getQuantity());
	}*/
	
	
	@AfterAll
	public static void cleanUp() {
		/*
		//주문책
		orderDao.deleteBooksByNo(mockOrderVo.getNo());
		
		// 주문
		orderDao.deleteByNo(mockOrderVo.getNo());
		
		// 카트
		cartDao.deleteByUserNoAndBookNo(mockCartVo01.getUserNo(), mockBookVo01.getNo());
		cartDao.deleteByUserNoAndBookNo(mockCartVo02.getUserNo(), mockBookVo02.getNo());
		*/
		
		// 서적
		bookDao.deleteByNo(mockBookVo01.getNo());
		bookDao.deleteByNo(mockBookVo02.getNo());
		bookDao.deleteByNo(mockBookVo03.getNo()); 
		
		// 카테고리
		categoryDao.deleteByNo(mockCategoryVo01.getNo());
		categoryDao.deleteByNo(mockCategoryVo02.getNo());
		categoryDao.deleteByNo(mockCategoryVo03.getNo());
		
		// 사용자
		userDao.deleteByNo(mockUserVo01.getNo());
		System.out.println(mockUserVo01.getNo());
	
		userDao.deleteByNo(mockUserVo02.getNo());
	}
}
