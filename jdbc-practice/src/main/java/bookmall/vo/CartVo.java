package bookmall.vo;

public class CartVo {
	private int no;
	private String category;
	private String title;
	private int quntity;
	private int price;
	private int total_price;
	private int member_no;
	
	public CartVo() {}
	public CartVo(int no, int quntity, int member_no) {
		this.no = no;
		this.quntity = quntity;
		this.member_no = member_no;
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
		return "| no=" + no + ", category=" + category + ", title=" + title + ", quntity=" + quntity + ", price="
				+ price + ", total_price=" + total_price + " |";
	}
	
	
	
	
	
}
