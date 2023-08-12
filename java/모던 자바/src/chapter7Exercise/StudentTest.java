package chapter7Exercise;

public class StudentTest {

	public static void main(String[] args) {
		Human man = new Human("Steve", 30);
		System.out.println(man);
		
		Student student = new Student("Parker", 23, "Physics");
		System.out.println(student);
		
		student.setName("Peter");
		student.setAge(19);
		student.setMajor("Cpmputer Science");
		
		System.out.print("이름: " + student.getName() + ", ");
		System.out.print("나이: " + student.getAge() + ", ");
		System.out.print("전공: " + student.getMajor());
		
		
	}

}
