package bookmall.vo;

public class MemberVo {
	private int no;
	private String name;
	private String email;
	private String password;
	private String tel;
	
	public MemberVo() {}
	public MemberVo(String name, String email, String password, String tel) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.tel = tel;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	@Override
	public String toString() {
		return "| " + 
				"이름: " + name + 
				" , 전화번호: " + tel+ 
				" , 이메일: " + email + 
				" , 비밀번호: " + password + 
				" |";
	}
	
	
	
	
}
