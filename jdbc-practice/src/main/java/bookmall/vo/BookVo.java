package bookmall.vo;

public class BookVo {
	private int no;
	private String title;
	private int price;
	private int category_no;
	private String category_str;
	public int getNo() {
		return no;
	}
	public BookVo() {}
	public BookVo(String title, int price, int category_no) {
		this.title = title;
		this.price = price;
		this.category_no = category_no;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCategory_no() {
		return category_no;
	}
	public void setCategory_no(int category_no) {
		this.category_no = category_no;
	}
	
	public String getCategory_str() {
		return category_str;
	}
	public void setCategory_str(String string) {
		this.category_str = string;
	}
	@Override
	public String toString() {
		return "| no=" + no + ", title=" + title + ", price=" + price + ", category=" + category_str + " |";
	}
	
	
	
	
}
