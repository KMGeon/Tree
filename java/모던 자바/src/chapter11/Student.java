package chapter11;

import java.util.Objects;

public class Student {
	//필드
	private String studentNum;
	
	//생성자
	public Student(String studentNum) {
		this.studentNum = studentNum;
	}
	//메소드
	public String getStudentNum() {
		return studentNum;
	}

	//기본으로 생성된 hashCode 재정의
	@Override
	public int hashCode() {
		return Objects.hash(studentNum);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Student)) {
			return false;
		}
		Student other = (Student) obj;
		return Objects.equals(studentNum, other.studentNum);
	}
	
	
	//내가 작성한 hashCode 재정의
//	@Override
//	public int hashCode() {
//		return studentNum.hashCode();
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if(obj instanceof Student) {
//		Student student = (Student) obj;
//		if(this.studentNum == student.studentNum) {
//				return true;
//		}
//		}
//		return false;
//	}
	
}
