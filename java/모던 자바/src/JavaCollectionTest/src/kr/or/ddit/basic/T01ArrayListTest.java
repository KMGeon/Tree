package kr.or.ddit.basic;

import java.util.List;
import java.util.ArrayList;


public class T01ArrayListTest {
	public static void main(String[] args) {
		
		// Default Capacity = 10;
		List list1 = new ArrayList();
		
		// add()메소드를 사용해서 데이터를 추가한다.
		list1.add("aaa");
		list1.add("bbb");
	//	list1.add(111); int 111을 integer로 만들면 111이 랩퍼클래스라고 한다. 오토박싱이 일어난다.  
		list1.add(new Integer(111));
		list1.add('k');
		list1.add(true);
		list1.add(12.31);
		
		//size() => 데이터 개수
		System.out.println("size => " + list1.size());
		System.out.println("list1 => " + list1);
		
		// get로 데이터 꺼내오기
		System.out.println("1번째 자료 : " + list1.get(1));
		
		//데이터를 끼워 넣어도 같다. 
		list1.add(0, "zzz");
		System.out.println("list1 => " + list1);
		
		// 데이터 변경하기
		String temp = (String) list1.set(0, "YYY");
		System.out.println("temp => " + temp);
		System.out.println("list1 => " + list1);
		
		// 삭제하기도 같다.
		list1.remove(0);
		System.out.println("삭제 후 : " + list1);
		System.out.println("-----------------------------------------------");
		list1.remove(new Integer(111));
		System.out.println("111 삭제후 " + list1);
		
		System.out.println("-----------------------------------------------");
		// 제너릭을 지정하여 선언할 수 있다.
		List<String> list2 = new ArrayList<String>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");
		
		for(int i=0; i<list2.size(); i++) {
			System.out.println(i + " : " + list2.get(i));
		}
		System.out.println("-------------------------------------");
		
		// 향상된 for문
		for (String s : list2) {
			System.out.println(s);
		}
		
		// contains(비교객체); => 리스트에 '비교객체'를 찾아 '비교객체'가  
		// 있으면 true, 없으면 false
		System.out.println(list2.contains("DDD"));
		System.out.println(list2.contains("ZZZ"));
		
		// indexOf(비교객체); => 리스트에 '비교객체'를 찾아 '비교객체'가  
		// 있는 index값을 반환한다. 없으면 -1반환함.
		System.out.println("DDD의 index값 : " + list2.indexOf("DDD"));
		System.out.println("ZZZ의 index값 : " + list2.indexOf("ZZZ"));
		System.out.println("------------------------------------");
		
		// toArray() => 리스트 안의 데이터들을 배열로 변환하여 반환한다.
		// 기본적으로 Object형 배열로 반환한다.
		Object[] strArr = list2.toArray();
		System.out.println("배열의 개수 : " + strArr.length);
		
		// 리스트의 제너릭 타입에 맞는 자료형의 배열로 변환하는 방법
		// 제너릭타입의 0짜리 배열을 생성해서 배열변수로 넣어준다.
		// 형식) toArray(new 제너릭타입[0])
		String[] strArr2 = list2.toArray(new String[0]);
		System.out.println("strArr2의 개수 : " + strArr2.length);
		
		// 삭제
		for (int i = 0; i < list2.size(); i++) {
			list2.remove(list2.get(i));
		}
		
		// list2.size() => 2 인 이유 배열이 앞을 먼저 지워주면 인덱스가 
		// 채워진다. 그래서 데이터가 남는다. 
		// 지우려면 뒤부터 지워야한다. for문 해보기
		
		System.out.println(list2.size());
		
		
	}
	
}
