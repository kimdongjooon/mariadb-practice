package bookmall.vo;

public class OrderBookVo {
	private int book_no;
	private int order_no;
	private int quntity;
	private int price;
	
	// order_book 테이블(주문 번호, 책고유 번호(책이름), 가격 , 수량, 총가격).
	private String bookName;
	private int price_mul_quntity;
	
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getPrice_mul_quntity() {
		return price_mul_quntity;
	}
	public void setPrice_mul_quntity(int price_mul_quntity) {
		this.price_mul_quntity = price_mul_quntity;
	}
	public int getBook_no() {
		return book_no;
	}
	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
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
	@Override
	public String toString() {
		return "| 주문번호: " + order_no + 
				", 책 제목: " + bookName + 
				", 수량: " + quntity +
				", (개당)가격: " + price + 
				", 총 가격: " + price_mul_quntity + 
				" |";
	}
	
	
	
}
