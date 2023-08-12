package chapter11;

import java.util.Objects;

//Value Object (데이터를 저장하는 객체)
public class Member {
	
	//필드
	private String id;
	private String name;
	private int mileage;
	
	//생성자
	public Member() {
		
	}
	public Member(String id, String name, int mileage) {
		this.id = id;
		this.name = name;
		this.mileage = mileage;
	}
	
	
	//메소드
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Member)) {
			return false;
		}
		Member other = (Member) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	public void run() {
		
	}

//	
//	
//	@Override
//	public boolean equals(Object obj) {
//		if(this == obj) {
//			return true;
//		}
//		if(!(obj instanceof Member)) {
//			return false;
//		}
//		Member member = (Member) obj;
//		
//		if(this.id.equals(member.id) && this.name.equals(member.name)) {
//			return true;
//		}else {
//			return false;
//		}
		
}
