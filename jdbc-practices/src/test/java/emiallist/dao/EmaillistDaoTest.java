package emiallist.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import emaillist.dao.EmaillistDao;
import emaillist.vo.EmaillistVo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 순서 정하기
public class EmaillistDaoTest {
	private static Long count = 0L;
	
	// 테스트 메소드 실행 전 실행하라고 명시, 카운트 세팅
	@BeforeAll
	public static void setUp() {
		count = new EmaillistDao().count();
	}
	
	@Test
	@Order(1)
	public void insertTest() {
		EmaillistVo vo = new EmaillistVo();
		vo.setFirstName("테");
		vo.setLastName("스트");
		vo.setEmail("test@gmail.com");
		
		Boolean result = new EmaillistDao().insert(vo);
		assertTrue(result);
	}
	
	@Test
	@Order(2)
	public void findAllTest() {
		List<EmaillistVo> list = new EmaillistDao().findAll();
		assertEquals(count+1,list.size());
	}
	
	@Test
	@Order(3)
	public void deleteByEmailTest() {
		Boolean result = new EmaillistDao().deleteByEmail("test@gmail.com");
		assertTrue(result);
	}
	
	@AfterAll
	public static void cleanup() {
		
	}
}