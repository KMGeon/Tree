package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MapTest {

	public static void main(String[] args) {
		/*
		- Map ==> key값과 value값을 한 쌍으로 관리하는 객체
			- key값은 중복을 허용하지 않고 순서가 없다.(Set의 특징을 갖는다.)
			- value은 중복을 허용한다.
		 */
			
		HashMap<String, String> map = new HashMap<>();
		
		// 자료추가 ==> put(key값, value값)
		map.put("name", "홍길동");
		map.put("add", "대전");
		map.put("tel", "010-1234-5678");
		
		System.out.println("map =>" + map);
		
		// 자료수정 ==> 데이터를 추가할 때 'key값'이 같으면 
		//				나중에 입력한 값이 저장된다.
		
		map.put("add", "서울");
		System.out.println("map => " + map);
		
		// 자료삭제 ==> remove(key값) : 'key값'이 같은 자료를 찾아서 삭제한다.
		// 			==> 반환값 : 삭제된 자료의 value값 
		
//		String removeTel = map.remove("tel");
//		System.out.println("map => " +map);
//		System.out.println("삭제된 value값 : " + removeTel);
//		System.out.println();
		
		// 자료읽기
		// get(key값) : 'key값'과 짝이되는 value값을 반환한다.
		System.out.println("이름  : " + map.get("name"));
		System.out.println();
		
		// key값이 존재하는지 여부를 나타내는 메서드 
		// containsKey(key값) 
		// ==> 해당 'key값'이 존재하면 true, 그렇지 않으면 false를 반환한다.
		System.out.println("tel 키값의 존재 여부 : " + map.containsKey("tel"));
		System.out.println("age 키값의 존재 여부 : " + map.containsKey("age"));
		System.out.println();
		
		// Map에 저장된 모든 데이터를 읽어오는 방법
		
		// 방법1) 모든 key값을 읽어와서 처리하기 ==> keySet() 메서드 사용
		// 		==> Map의 모든 key값들을 읽어와 Set형으로 반환한다.
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()){
			String key = it.next(); //key값 1개 읽어오기
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		
		System.out.println("--------------------------------");
		
		// 방법2) 방법1의 처리를 향상된 for문으로 처리하기
		for(String key : map.keySet()){
			String value = map.get(key);
			System.out.println(key + " => " + value);
		}
		
		System.out.println("--------------------------------");
		
		// 방법3) value값만 읽어와 처리하기
		// values()메서드를 이용한다.
		for(String value : map.values()){
			System.out.println(value);
		}

		System.out.println("--------------------------------");
	}
}