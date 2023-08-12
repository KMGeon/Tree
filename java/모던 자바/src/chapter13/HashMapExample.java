package chapter13;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HashMapExample {

	public static void main(String[] args) {
		Map<Student, Integer> map = new HashMap<>();
		map.put(new Student(1, "홍길동"),90);
		map.put(new Student(2, "이순신"),87);
		map.put(new Student(3, "신용권"),100);
		map.put(new Student(4, "조민우"),63);
		map.put(new Student(3, "신용권"),94);
		map.put(new Student(5, "신용권"),94);
		
		System.out.println("총 갯수: " + map.size());
		
		System.out.println("신용권 점수: " + map.get(new Student(3,"신용권")));

		map.remove(5,"신용권");
		
		System.out.println("총 갯수: " + map.size());
		
		//객체를 하나씩 처리(엔트리를 사용)
		// 1. 엔트리를 사용
		Set<Entry<Student, Integer>> entrySet = map.entrySet();
		for (Entry<Student, Integer> entry : entrySet) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
			
		}
		System.out.println();
		//2.key를 이용
		Set<Student> keySet = map.keySet();
		for(Student student : keySet) {
			System.out.println(student + ":" + map.get(student));
		}
		System.out.println();
		//3. value를 이용: value만 가져온다.
		Collection<Integer> values = map.values();
		for (Integer integer : values) {
			System.out.println(integer);
		}
		
		//전체 지우기
		map.clear(); //모든 데이터 지우기
		
	}

}
