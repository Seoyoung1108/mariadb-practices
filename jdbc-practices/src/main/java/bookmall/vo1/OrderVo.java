package bookmall.vo1;

public class OrderVo {
	private int no;
	private String number;
	private int payment;
	private String shipping;
	private String status;
	private int user_no;
	
	public OrderVo() {
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public String getShipping() {
		return shipping;
	}
	public void setShipping(String shipping) {
		this.shipping = shipping;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUserNo() {
		return user_no;
	}
	public void setUserNo(int user_no) {
		this.user_no = user_no;
	}
}
