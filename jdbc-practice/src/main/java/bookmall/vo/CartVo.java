package bookmall.vo;

public class CartVo {
	private int no;
	private int bookNo;
	private String category;
	private String title;
	private int quntity;
	private int price;
	private int total_price;
	private int member_no;
	private String email;
	
	public CartVo() {}
	public CartVo(int bookNo, int quntity, MemberVo mvo) {
		this.bookNo = bookNo;
		this.quntity = quntity;
		this.member_no = mvo.getNo();
		this.email = mvo.getEmail();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getQuntity() {
		return quntity;
	}
	public void setQuntity(int quntity) {
		this.quntity = quntity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	@Override
	public String toString() {
		return "| " + 
			   "도서 제목: " + title + 
			   ", 수량: " + quntity + 
			   ", 가격: " + price + 
			   ", 총 가격: " + total_price + 
			   " |";
	}
	
	
	
	
	
}
