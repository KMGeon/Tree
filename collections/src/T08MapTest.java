package HighJava.src.Collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class T08MapTest {
	public static void main(String[] args) {

		Map<String, String> map = new HashMap<String, String>();

		// 자료 추가->put(키값 , value값)
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");

		System.out.println("map->" + map);
		// 자료 수정 -> 데이터를 저장할 때 키ㅏ값이 같으면 나중에 입력한 값이 저장된다.

		// put(수정할 키값 , 새로운 value값)
		map.put("addr", "서울");
		System.out.println("map->" + map);

		// 자료 삭제 -> remove(삭제할 키값)
		map.remove("name");
		System.out.println("map->" + map);

		// 자료 읽기 -> get(key값)
		System.out.println("addr=" + map.get("addr"));
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");

		// 키값들을 읽어와 자료를 출력하는 방법
		// 1.keyset()메서드 이용하기 keyset(): map의key값들만 읽어와 set형으로 반환한다.
		Set<String> keyset = map.keySet();

		System.out.println("Iterator를 이용한 방법");
		Iterator<String> it = keyset.iterator();
		while (it.hasNext()) {
			String key = it.next();
			System.out.println(key + ":" + map.get(key));
		}
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		// 2.set형의 데이터를 foreach문으로 처리하는 방법
		for (String key : keyset) {
			System.out.println(key + ":" + map.get(key));
		}
		System.out.println("---------------------------------------------------");
		// 방법3. value값만 읽어와 출력하기 -> values()이용하기
		System.out.println("valuses()를 이용한 방법");
		for (String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");

		// 방법4 ->Map관련 클래스에는 map,entry타입의 내부 클래스가 만들어져 있다.
		// 이 내부 클ㄹ래스는 키값과 value라는 멤버변수로 구성되어 있다.
		// map에는 이 map.entry타입의 객체들 set형으로 저장하여 관리한다.
		// Map.Entry타입의 객체 모두 가져오기 ->entrySet()이용하기
		Set<Entry<String, String>> entrySet = map.entrySet();

		// 가져온 entry객체들을 순서대로 처리하기 위해서 iterator객체 이용
		Iterator<Entry<String, String>> entryIt = entrySet.iterator();

//		while (entryIt.hasNext()) {
//			Map.Entry<String, String>entry = entryIt.next();
//			System.out.println("key값"+entry.getKey());
//			System.out.println("value값: "+entry.getValue());
//			System.out.println();
//		}
		for (Entry<String, String> entry : entrySet) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}
}
