package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T15ObjectStreamTest {
	public static void main(String[] args) {
		
		// Member객체 생성하기
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("일지매", 30, "경기");
		Member mem3 = new Member("이몽룡", 40, "강원");
		Member mem4 = new Member("성춘향", 50, "광주");
		
		ObjectOutputStream oss = null;
		
		try {
			// 객체를 파일에 저장하기
			
			// 출력용 스트림 객체 생성하기 (데코레이터 패턴)
			oss = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("e:/D_Other/memObj.bin")));
			
			// 쓰기 작업
			oss.writeObject(mem1); // writer()메소드가 실행되며 직렬화가 일어난다.
			oss.writeObject(mem2);
			oss.writeObject(mem3);
			oss.writeObject(mem4);
			
			System.out.println("쓰기 작업 완료...");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oss.close(); // 보조 스트림을 닫으면 기반 스트림도 닫히는 구조이다. 
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("e:/D_Other/memObj.bin")));
			
			Object obj = null;
			
			// readObject()메소드가 실행되면 역직렬화가 일어난다. (FileInputStream에서 읽어온다.)
			while((obj = ois.readObject()) != null) {
				// 파일의 마지막에 다다르면 EOF Exception 발생함(End Of File)
				
				// 읽어온 객체를 원래의 타입으로 변환 후 사용하나다. 
				Member mem = (Member) obj;
				
				System.out.println("이름 : " + mem.getName());
				System.out.println("나이 : " + mem.getAge());
				System.out.println("주소 : " + mem.getAddr());
				System.out.println("------------------------");
				
			}
			
		} catch (IOException e) {
			// e.printStackTrace();
			// 더 이상 읽어올 객체가 없으면 예외 발생함.(EOF Exception)
			System.out.println("출력 작업 끝...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				ois.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		
	}
}


class Member implements Serializable { // VO : Value Object
	// 자바는 Serializable 인터페이스를 구현한 클래스만 직렬화 할 수 있도록 제한하고 있음.
	/*
	 * transient(일시적) => 직렬화가 되지 않을 멤버변수에 지정한다.
	 * 				(static 필드도 직렬화가 되지 않는다.)
	 * 			   직렬화가 되지 않는 멤버변수는 기본값으로 저장된다.
	 * 			  (참조형 변수 : null, 숫자형 변수 : 0)
	 * */ 
	transient private String name;
	private int age;
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getAddr() {
		return addr;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + ", addr=" + addr + "]";
	}
	
	
	
	
}