package Collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class T06TreeSetTest {
	public static void main(String[] args) {

		TreeSet<String> ts = new TreeSet<String>();
		ArrayList<String> list = new ArrayList<String>();

		// 영어 대문자를 문자열로 변환하여 list에 저장하기
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			String temp = String.valueOf(ch);
			list.add(temp);
		}
		System.out.println("list자료 전"+list);
		Collections.shuffle(list);
		System.out.println("list리스트 자료-후 : " + list);

		for (String str : list) {
			ts.add(str);
		}
		System.out.println("treeset자료-전: " + ts);
		// treeset에 저장횐 자료중 틀정한 자료보다 작은 자료를 찾아서 sortedset으로 반환하는 메서드가 있다.
		// headset(기준값) : 기본적으로 기준값은 포함시키지 않는다.
		// headset(기준값 , 논리값) : 논리값이 참이면 기준값을 포함시킨다.
		SortedSet<String> ss1 = ts.headSet("K");
		System.out.println("K이전 자료: " + ss1);
		System.out.println("K이전 자료(기준값 포함):" + ts.headSet("K", true));
		// 기준값 도다 큰 자료를 찾아 sortedset으로 반환하는 메서드
		// tailset(기준값): 기본적으로 기준값을 포함시킨다.
		// tailset(기준값 , 논리값) : 논리값이 false이면 '기준값'을 포함시키지 않는다.
		SortedSet<String> ss2 = ts.tailSet("K");
		System.out.println("K이후 자료: " + ss2);
		System.out.println("K이후 자료(기준값 미포함): " + ts.tailSet("K", false));
		// subset(기준값1 , 기준값2) : 기준값1 ~ 기준값2 사이의 값을 가져온다.
		// ('기준값1' 포함 , '기준값2' 미포함)
		// subset(기준값1, 논리값1, 기준값2 , 논리값2): 각 값을 기준으로 포함여부 설정
		System.out.println("K(포함)부터N(미포함)까지: " + ts.subSet("K",true ,"N",true));
		System.out.println("K(포함)부터N(미포함)까지: " + ts.subSet("K",false ,"N",false));
		System.out.println("K(포함)부터N(미포함)까지: " + ts.subSet("K",false ,"N",true));
		
	}
}
