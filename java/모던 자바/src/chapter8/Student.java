package chapter8;

public class Student implements Comparable<Student>{
	private String name;
	private int age;
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
	public int compareTo(Student o) {
//		if(this.age > o.age) {
//			return 1;
//		}else if(this.age < o.age) {
//			return -1;
//		}else {
//			return 0;
//		}
		if(this.name.compareTo(o.name) > 0) {
			return -1;
		}else if(this.name.compareTo(o.name) < 0) {
			return 1;
		}else {
			return 0;
		}
		
//		return this.name.compareTo(o.name);
	
	
	}
	
}
