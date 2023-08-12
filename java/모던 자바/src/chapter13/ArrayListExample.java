package chapter13;

import java.util.ArrayList;
import java.util.List;

public class ArrayListExample {
	public static void main(String[] args) {
		//1.
		ArrayList<String> strList = new ArrayList<>();
		//2.
		List<String> strList1 = new ArrayList<>();
		//3.
		List<String> strList2 = new ArrayList<>("123","123");

//		--------------------------------------------------------------------		
		List<String> list = new ArrayList<>(); // 다형성 -> 상위 클래스 타입이 온다.
		
		//add
		list.add("Java");
		list.add("JDBC");
		list.add("Servlet/JSP");
		for(String string: list) {
			System.out.println(string);
		}
		list.add(2, "Database"); // 2번째의 데이터가 뒤로 밀린다.
		list.add("mybatis");

		for(int i=0; i < list.size(); i++) {
			System.out.println(i + ": " + list.get(i));
		}
		System.out.println();
		//get
		String skill = list.get(4);
		System.out.println(skill);
		
		//remove
		list.remove(0);
		
		for(int i = 0; i< list.size(); i ++) {
			System.out.println(i + ": " + list.get(i));
		}
		System.out.println();
		
		list.remove("myBatis");
		
		for(int i = 0; i< list.size(); i ++) {
			System.out.println(i + ": " + list.get(i));
		}
		System.out.println();
		
		list.clear();
		System.out.println(list.size());

	}
}
