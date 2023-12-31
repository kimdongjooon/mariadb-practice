package bookmall.vo;

public class CategoryVo {
	private int no;
	private String category;
	public int getNo() {
		return no;
	}
	public CategoryVo() {}
	public CategoryVo(String category) {
		this.category = category;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "| no: " + no + ", 카테고리: " + category + " |";
	}
}
