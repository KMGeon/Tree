package chapter11;

public class Member1 {
	//필드
	private String id;
	private String name;
	
	//생성자
	public Member1(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	//메소드
	@Override
	public String toString() {
		return String.format("%s: %s", id,name);
	}
	
}
