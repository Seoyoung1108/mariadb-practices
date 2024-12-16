package bookmall.vo;

public class CartVo {
	// primary X
	private int user_no;
	private int book_no;
	private String book_title;
	private int quantity;
	
	public CartVo() {
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
	
	public String getBookTitle() {
		return book_title;
	}

	public void setBookTitle(String book_title) {
		this.book_title = book_title;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
