package bookshop.dao;

import bookmall.vo1.BookVo;
import bookshop.vo.AuthorVo;

public class BookDaoTest {
	private static AuthorDao authorDao = new AuthorDao();
	private static BookDao bookDao = new BookDao();
	private static AuthorVo mockAuthorVo = new AuthorVo();
	
	@BeforeAll
	public static void setup() {
		mockAuthorVo.setName("칼세이건");
		authorDao.insert(mocckAuthorVo);
	}
	
	@Test
	public void insertTest() {
		BookVo bookVo = new BookVo();
		bookVo.setTitle("코스모스");
		bookVo.setAuthorId(mockAuthorVo.getId());
		
		bookDao.insert(bookVo);
	}
	
	@AfterAll
	public static void cleanup() {
		authorDao.deleteById(mockAuthorVo.getId());
		bookDao.deleteAll();
	}
}
