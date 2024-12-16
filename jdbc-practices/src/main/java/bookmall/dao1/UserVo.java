package bookmall.dao1;

public class UserVo {
	private int no;
	private String name;
	private String email;
	private String password;
	private String phonenumber;
	
	public UserVo() {
	}
	
	public UserVo(String name, String email, String password, String phonenumber) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phonenumber = phonenumber;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
}
