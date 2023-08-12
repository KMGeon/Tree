package studentListSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentList {
	public static void main(String[] args) {
		
		List<Student> list = new ArrayList<>();

		list.add(new Student("20144444", "강감찬", 50, 40, 50));
		list.add(new Student("20141111", "이문주", 80, 90, 100));
		list.add(new Student("20142222", "안하용", 50, 10, 90));
		list.add(new Student("20147777", "최승훈", 40, 20, 0));
		list.add(new Student("20143333", "김태훈", 20, 90, 100));
		list.add(new Student("20140000", "홍길동", 10, 50, 80));
		list.add(new Student("20146666", "임꺽정", 20, 45, 10));
		list.add(new Student("20149999", "심청이", 20, 90, 100));
		list.add(new Student("20145555", "심봉사", 50, 40, 50));

		System.out.println("-------------------------------정렬 전--------------------------------- ");
		System.out.println();
		for(Student std : list) {
			System.out.println(std);
		}
		System.out.println();

		Collections.sort(list);
		System.out.println("--------------------------학번기준 정렬 후--------------------------------- ");
		System.out.println();
		for(Student std : list) {
			System.out.println(std);
		}
		
		System.out.println();
		System.out.println("--------------------------총점기준 정렬 후--------------------------------- ");
		System.out.println();
		Collections.sort(list, new TotalScoreSort());
		for(Student std : list) {
			System.out.println(std);
		}
		
		
		
		
	}

}
class TotalScoreSort implements Comparator<Student>{
	
	// 총점기준 정렬
	public int compare(Student std1, Student std2) {
		if(std1.getTotalScore() == std2.getTotalScore()) {
			return std1.getStudentNo().compareTo(std2.getStudentNo()) * -1;
		}else {
			return Integer.compare(std1.getTotalScore(), std2.getTotalScore()) * -1;
		}
	}
}
