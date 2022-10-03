package Collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T03ListSortTest {
	/*
	 * 정렬과 관련된 인터페이스는 comparable 과 comparator 두가지가 있다. 보통 객체 자체에 정렬 기능을 넣기 위해서는
	 * comparable을 구현하고 정렬 기준을 별도로 구현하고 싶은 경우에는 comparator를 구현하여 사용
	 */
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();

		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		System.out.println("정렬 전: " + list);

		/*
		 * 정렬은 collections.sort()메소드를 이용하여 정렬한다. 정렬은 기본적으로 오름차순정렬을 수행한다.
		 * 
		 * 정렬방식을 변경하려면 정렬방식을 결정하는 객체를 만들어서 collections.sort()메서드의 인수로 넘겨주면 된다.
		 */

		// static과 instance변수 차이 static은 사용할려면 객체를 안만들고 사용가능 , instance는 객체 만들고 사용가능
		Collections.sort(list);
		System.out.println("정렬 후:" + list);

		Collections.shuffle(list);
		System.out.println("섞기 후:" + list);

	//	Collections.sort(list, new Desc());
		System.out.println("외부정렬"+list);
	}
}

/*
 * 정렬방식을 결정하는 class 는 comparator라는 인터페이스를 구현해야 한다.
 * 이 comparator 인터페이스의 compare()라는 메서드를 재정의 하여 구현하면 된다
 */
//public class Desc implements Comparator<String> {
//
//	/*
//	 * compare()메서드의 반환값을 결정하는 방법
//	 * ->이 메서드가 양수를 반환하면 두값의 순서가 바뀐다.(오름차순이 기본임)
//	 *
//	 * -******오름차순 정렬일 경우**********
//	 * ->앞의 값이 크면 양수 , 같으면 0 앞의 값이 작으면 음수를 반환하도록 한다.
//	 *
//	 * -string객체에는 정렬을 위해서 compareTo()메서드가 구현되어 있는데 이 메서드의 반환값은 오름차순에 맞게 반환되도록 구현되어 있다.
//	 * (wrapper클래스와 date , file클래스에도 구현되어 있다.)
//	 */
//	@Override
//	public int compare(String str1, String str2) {
//
//		return str1.compareTo(str2);
//		//내림차순 return str1.compareTo(str2)*-1;
//	}
//
//}