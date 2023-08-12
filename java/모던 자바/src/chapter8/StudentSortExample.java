package chapter8;

import java.util.Arrays;

public class StudentSortExample {

	public static void main(String[] args) {
		Student[] students = {
				new Student("이승연", 20),
				new Student("김연수", 26),
				new Student("허지현", 30)
				};
		Arrays.sort(students);
		
		for(Student s : students) {
			
			System.out.println(s.getName() + ", " + s.getAge());
		}
	}

}
