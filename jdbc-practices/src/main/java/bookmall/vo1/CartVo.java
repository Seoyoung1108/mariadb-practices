package bookmall.vo1;

public class CartVo {
	private int no;
	private int user_no;
	private int book_no;
	private int quantity;
	
	public CartVo() {
	}

	public CartVo(int user_no, int book_no, int quantity) {
		this.user_no = user_no;
		this.book_no = book_no;
		this.quantity = quantity;
	}

	public long getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUserNo() {
		return user_no;
	}

	public void setUserNo(int user_no) {
		this.user_no = user_no;
	}

	public int getBookNo() {
		return book_no;
	}

	public void setBookNo(int book_no) {
		this.book_no = book_no;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	// 도서 제목, 가격??
	
}
