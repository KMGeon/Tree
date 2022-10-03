package Collection;

import java.util.*;
import java.util.ArrayList;

public class T01ArrayListTest {
	public static void main(String[] args) {
		// Arraylist는 기본적인 사용법이 vector와 같다.
		// Default Capcity=10;
		List list = new ArrayList();

		// add() 메서드를 이용하여 데이터를 추가한다.
		list.add("aaa");
		list.add("bbb");
		list.add(111);
		// list.add(new integer(111));
		list.add('k');
		// list.add(new string('k');
		list.add(true);
		// list.add(new Boolean(true));
		list.add(12.34);

		// size() -> 데이터의 갯수
		System.out.println("SIZE->" + list.size());
		System.out.println("list1->" + list);

		// get으로 데이터 꺼내오기-> list.get(index)
		System.out.println("1번째 자료:" + list.get(0));

		// 데이터 끼워넣기도 같다. ->list.add(index ,value);
		list.add(0, "zzz");
		System.out.println("list->" + list);

		// 데이터 변경하기(set 메소드)
		String temp = (String) list.set(0, "YYY");
		System.out.println("temp->" + temp);
		System.out.println("list->" + list);

		// 삭제도 같다
		list.remove(0);
		System.out.println("삭제 후 ->" + list);

		list.remove("bbb");
		System.out.println("bbb삭제후" + list);

		list.remove(new Integer(111));
		System.out.println(list);

		// 제넉릭을 지정하여 선언할 수 있다.
		ArrayList<String> list2 = new ArrayList<>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");

		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + ":" + list2.get(i));
		}
		System.out.println("--------------------------------------------");

		// for each문
		for (String str : list2) {
			System.out.println(str);
		}

		
		// *contains(비교객체); -> 리스트에 비교객체를 찾아 비교객체가 있으면 true , 없으면 false리턴함
		System.out.println(list2.contains("DDD"));
		System.out.println(list2.contains("ZZZ"));

		//*indexOf(비교객체);->리스트에서 비교객체를 찾아 비교객체가 있는 index값을 반환한다. 없으면 -1반환
		//indexOf는 입력값의 인덱스를 말해준다.
		System.out.println("DDD의 INDEX값:" + list2.indexOf("DDD"));
		System.out.println("ZZZ의 INDEX값:" + list2.indexOf("ZZZ")); //return -1
		System.out.println("------------------------------------------------");

		// *toArray() -> 리스트 안의 데이터틀을 배열로 변환하여 반환한다. 기본적인 object형 배열로 반환한다.
		//list를 배열로 바꾸어준자.
		Object[] strArr = list2.toArray();
		System.out.println("배열의 개수:" + strArr.length);
		//String[]strArr = (string)list.toArray();
		// 리스트의 제너릭 타입에 맞는 자료형의 배열로 변환하는 방법
		// 제너릭타입의 0개짜리 배열을 생성해서 배열변수로 넣어준다.
		// 형식)toArray(new 제너릭타입[0]))

		String[] strArr2 = list2.toArray(new String[0]);
		System.out.println("strArr2의 개수:" + strArr2.length);

		// 리스트 삭제처리
		//사이즈 만큼 지워도 0을 지우면 1이 0자리로 댕겨짐
		for (int i = 0; i < list2.size(); i++) {
			list2.remove(list2.get(i));
		}
		System.out.println(list2.size());
	}
}
