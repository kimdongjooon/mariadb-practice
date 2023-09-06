package bookmall.vo;

public class OrderVo {
	private int no;
	private int total_price; 
	private int member_no; // 주문자 고유번호는 같이 넣어주기.
	private String name;
	private String email;
	private String address; // 주소 입력 받기. 그외에는 DB에서 찾아서 넣기.
	
	public OrderVo() {}
	public OrderVo(int member_no, String address) {
		this.member_no = member_no;
		this.address = address;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "| no=" + no + ", total_price=" + total_price + ", name=" + name
				+ ", email=" + email + ", address=" + address + " |";
	}

	
	
	
	
	
}
