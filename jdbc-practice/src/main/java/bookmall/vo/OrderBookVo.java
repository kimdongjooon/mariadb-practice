package bookmall.vo;

public class OrderBookVo {
	private int book_no;
	private int order_no;
	private int quntity;
	private int price;
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
		return "| book_no=" + book_no + ", order_no=" + order_no + ", quntity=" + quntity + ", price="
				+ price + " |";
	}
	
	
	
}
