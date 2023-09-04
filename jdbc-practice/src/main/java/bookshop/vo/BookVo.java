package bookshop.vo;

public class BookVo {
	private int no = 0;
	private String title ="";
	private String rent="";
	private String author="";
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	
	@Override
	public String toString() {
		return "BookInfo [책 번호=" + no + ", 책 제목=" + title + ", 작가: " + author + ", 대여유무: " + rent + " ]";
	}
	
	
	
	
	
	
}
