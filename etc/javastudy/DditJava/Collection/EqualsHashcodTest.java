package kr.or.ddit.basic;

import java.util.HashSet;

public class EqualsHashcodTest {

	public static void main(String[] args) {

		// 메서드영역 : class정보(static 정보)
		// 콜스택영역 : main 메서드영역 확보
		// 힙영역

		// main 영역에 p1(100번지-참조값),p2,p3
		// person 정보를 메서드영역에 저장
		// 힙영역에 id,name,메서드 > 전체가 (100번지-참조값)
		
		// id,name,메서드>p1 (100번지)
		
		Person p1 = new Person();
		p1.setId(1);
		p1.setName("홍길동");
		
		Person p2 = new Person();
//		p2.setId(2);
//		p2.setName("일지매");
		p2.setId(1);
		p2.setName("홍길동");
		
		Person p3 = p1;
		
		System.out.println(p1 == p2);
		//내용은 같아도 주소(번지-참조값)가 다르기 때문에 false가 나옴
		System.out.println(p1.equals(p2));
		//참조값이 달라서
		//equals : object에 만들어져 있음

		/*
		 public boolean equals(Object obj){
		 return this == obj
		 }
		 */
	
		String str1 = "이순신";
		String str2 = "이순신";
		String str3 = new String("이순신");
		String str4 = new String("이순신");
	
		// 문자열 처리 - 힙영역 안에 별도로 존재 (10번지라고 할 때)
		// 스택(str)에 10번지라고 저장
		// new가 있으면 30번지에 (다른 영역에 생성된다는 의미)
	
		HashSet<Person> testSet = new HashSet<>();
		
		testSet.add(p1);
		testSet.add(p2);
		
		System.out.println("set의 크기 : " +  testSet.size());
		
		System.out.println("p1 : " + p1.hashCode());
		System.out.println("p2 : " + p2.hashCode());
		System.out.println("p3 : " + p3.hashCode());
		//p1,p3 참조값이 같다고 위에서 했기때문에 hashCode도 같음
		
		/*
		 - equals()메서드 ==> 두 객체의 내용이 같은지 검사하는 연산자
		 - hashCode()메서드 ==> 두 객체의 동일성을 검사하는 연산자

		 - HashSet,Hashtable, HashMap과 같이 Hash로 시작하는 컬렉션
		 	객체들은 객체의 의미상의 동일성을 비교하기 위해서 
		 	hashCode()메서드를 호출하여 비교한다.
		 	그러므로, 객체가 같은지 여부를 결정하려면, 
		 	hashCode() 메서드를 재정의 해야한다.
		 	
		 - hashCode()메서드에서 사용하는 '해싱 알고리즘'은 
		 	서로 다른 객체들에 대해서 같은 hashcode가 발생할 수 있다.	
		 */
		
	}

}


class Person{
	private int id;
	private String name;
	String tel;
	String add;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((add == null) ? 0 : add.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (add == null) {
			if (other.add != null)
				return false;
		} else if (!add.equals(other.add))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		return true;
	}
	
//	// 객체의 id값과 name값이 같으면 true를 반환하도록 재정의한다.
//	@Override
//	public boolean equals(Object obj) {
//		if(obj==null) return false;
//		
//		// 같은 유형의 클래스인지 검사
//		if(this.getClass() != obj.getClass()) return false;
//		
//		// 참조값이 같은지 검사
//		if(this==obj) return true;
//		
//		// 매개변수에 저장된 객체를 현재 객체 유형으로 형변환 한다.
//		Person that = (Person)obj;
//		
//		if(this.name == null && that.name != null)
//			return false;
//
//		if(this.id == that.id && this.name==that.name)
//			return true;
//		
//		if(this.id == that.id && this.name.equals(that.name))
//			return true;
//			
//		return false;
//	}
//	
	
	
	
}