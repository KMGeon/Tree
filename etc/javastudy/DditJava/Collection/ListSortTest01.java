package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
  정렬과 관련된 interface는 Comparable, Comparator 이렇게 두 가지가 있다.
  
  - Comparable은 Collection에 추가되는 데이터 자체에 
  정렬 기준을 넣고 싶을 때 구현하는 인터페이스이다. (내부 정렬기준 구현하기)
  
  - Comparator는 외부에 별도로 정렬 기준을 구현하고 싶을 때 사용하는 인터페이스이다.
  	(외부정렬기준 구현하기)
  
  - Comparable에서는 compareTo()메서드를 재정의하고, 
  	Comparator에서는 compare()메서드를 재정의해야 한다.
  	
  - String 클래스, Wrapper클래스, Date클래스, File클래스에는 
  	내부 정렬 기준이 구현되어 있다.
  	(내부 정렬 기준은 오름차순으로 처리되도록 구현되어 있다.)
*/

public class ListSortTest01 {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬전 : " + list);

		//정렬은 Collections.sort()메서드를 이용하여 정렬한다.
		//Collections.sort()메서드는 기본적으로 내부 정렬 기준으로 정렬한다.
		//내부 정렬 기준 
		// ==> Collection에 추가되는 데이터 자체에 구성된 정렬 기준(지금은  String의 내부 정렬기준)
		// ==> 기본적인 자료형의 내부 정렬 기준은 오름차순으로 정렬되도록 만들어져 있다.
		
		Collections.sort(list);
		System.out.println("정렬후 : " + list);
		
		Collections.shuffle(list); //자료 섞기
		System.out.println("자료 섞기 후 : " + list);
		
		//외부 정렬 기준을 사용해서 정렬하기
		Collections.sort(list, new Desc());
		System.out.println("내림차순 정렬 후 : " + list);
	
	}

}

// 정렬 방식을 정해주는 class만들기(외부 정렬 기준 class만들기)
class Desc implements Comparator<String>{

	/* compare메서드의 반환값의 역할
	 	- 반환값이 0 ==> 두 값이 같다.
	 	- 반환값이 양수 ==> 두 값의 순서를 바꾼다.
	 	- 반환값이 음수 ==> 두 값의 순서를 바꾸지 않는다.
	
		예) 오름차순일 경우 ==> 앞의 값이 크면 양수, 같으면 0
						      뒤의 값이 크면 음수가 반환되도록 구현한다.
	*/

	@Override
	public int compare(String str1, String str2) {
		// 내림 차순으로 구현하려고 한다.
		/*
		if(str1.compareTo(str2)>0){
			return -1;
		}else if(str1.compareTo(str2) <0){
			return 1;
		}else{
			return 0;
		}
		*/
		return str1.compareTo(str2)*-1;
	
	
	
	
	
	}
	
}

