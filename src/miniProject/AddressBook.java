package miniProject;

public class AddressBook {
	
	//필드
	private String name;
	private String hp;
	private String company;
	
	//생성자
	public AddressBook() {}
	
	public AddressBook(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	
	//메소드 gs
	public String getName() {
		return name;
	}
	
	public String getHp() {
		return hp;
	}
	
	public String getCompany() {
		return company;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setHp(String hp) {
		this.hp = hp;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	public String toString() {
		return name + ", " + hp + ", " + company;
	}
	
}