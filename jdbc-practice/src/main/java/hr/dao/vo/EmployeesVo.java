package hr.dao.vo;

public class EmployeesVo {
	private Long empNO;
	private String birthDate;
	private String firstName;
	private String lastName;
	private String hireDate;
	private String gender;
	public Long getEmpNO() {
		return empNO;
	}
	public void setEmpNO(Long empNO) {
		this.empNO = empNO;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return "EmployeesVo [empNO=" + empNO + ", birthDate=" + birthDate + ", firstName=" + firstName + ", lastName="
				+ lastName + ", hireDate=" + hireDate + ", gender=" + gender + "]";
	}
	
	
	
}
