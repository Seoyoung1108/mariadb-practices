package bookmall.dao1;

public class OrderBookVo {
	// primary X
	private int quantity;
	private int price;
	private int book_no;
	private String book_title;
	private int orders_no;
	
	public OrderBookVo() {
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
	public int getOrderNo() {
		return orders_no;
	}
	public void setOrderNo(int orders_no) {
		this.orders_no = orders_no;
	}
	
}
