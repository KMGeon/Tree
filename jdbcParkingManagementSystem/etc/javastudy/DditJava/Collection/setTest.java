package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class setTest {

	public static void main(String[] args) {
	/*
	 	- List와 Set의 차이점
	 	1. List
	 		- 데이터의 순서(index)가 있다.
	 		- 중복된 데이터를 저장할 수 있다.
	 	2. Set
	 		- 데이터의 순서(index)가 없다.
	 		- 중복되는 데이터를 저장할 수 없다.
	 */

		HashSet hs1 = new HashSet<>();
		
		//데이터 추가 : add()메서드 이용
		
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("set => " + hs1);
		//인덱스가 따로 없어서 순서대로 출력되지 않음
		System.out.println("set의 개수 => " + hs1.size());
		System.out.println();
		
		//set에 중복되는 데이터를 추가하면 false를 반환하고
		//데이터는 추가되지 않는다.
		boolean isAdd = hs1.add("FF");
		System.out.println("중복되지 않을 때 : " + isAdd);
		System.out.println("set => " + hs1);
		System.out.println();
		
		isAdd = hs1.add("CC");
		System.out.println("중복될 때 : " + isAdd);
		System.out.println("set => " + hs1);
		System.out.println();
		
		// Set의 데이터를 수정하려면 수정하는 명령이 따로 없기 때문에
		// 해당자료를 삭제한 후에 추가해 주는 방식으로 처리한다.
		
		// 삭제하는 메서드 : remove(삭제할 데이터)
		//					==> 반환값 : 삭제성공(true), 삭제실패(false)
		//				 clear() ==> 전체데이터 모두 삭제
		
		// "FF" 데이터를 "EE"로 변경하기
		hs1.remove("FF");
		System.out.println("삭제한 후 set => " + hs1);
		hs1.add("EE");
		System.out.println("set => " + hs1);
		System.out.println();
		
		/*
		hs1.clear();
		System.out.println("전체 삭제 후 set => " + hs1);
		*/
		
		/*
		 Set의 데이터는 순서(index)가 없기 때문에 List처럼 index로 데이터를 하나씩 불러올 수 없다.
		 그래서 데이터를 하나씩 얻기 위해서는 Iterator형 객체로 변환해야 한다.
		  
		 - Set형의 데이터들을 Iterator형 객체로 변환하는 메서드 => iterator()
		 */

		Iterator it = hs1.iterator(); // Set 데이터를 Iterator로 변환하기
		
		//Iterator의 hasNext()메서드
		// ==> Iterator의 포인터가 가리키는 곳 다음 번째에 데이터가 있는 지 검사한다.
		// 		(데이터가 있으면 true, 없으면 false 반환)

		while(it.hasNext()) {
			// Iterator의 next()메서드
			// ==> Iterator의 포인터가 가리키는 곳 다음번째에 위치로 이동한 후 
			//		그 곳의 데이터를 읽어 와 반환한다.
			System.out.print(it.next() + "  ");
		}
			System.out.println();
		
		// 향상된 for문을 사용하면 Iterator를 사용한 효과를 낼 수 있다.
		for(Object data : hs1) {
			System.out.print(data + "  ");
		}
		System.out.println();
		
		// 우리반 학생들 중 번호를 이용하여 추첨하는 프로그램을 작성해 보자.
		// 번호는 1번부터 24번까지 있고, 추첨할 인원은 3명이다.
		// 당첨자를 출력해 보시오.
		
		// 최솟값부터 최댓값 사이의 정수형 난수 만들기
		// (int)(Math.random() * (최댓값 - 최솟값 + 1) + 최솟값)
		
		HashSet<Integer> testSet = new HashSet<>();
		
		while(testSet.size()<3) {
			testSet.add((int)(Math.random()*24 + 1));
		}
		
		System.out.println("당첨자 번호 " + testSet);
		
		// Set유형의 데이터를 List형으로 변환하기
		ArrayList<Integer> testList = new ArrayList<>(testSet);		
		
		System.out.println("List 데이터 출력");
		for(int i=0; i<testList.size(); i++) {
			System.out.println(testList.get(i));
		}
		
		// 로또번호 만들기 
		// 1~45 사이의 중복되지 않은 번호 6개 만들기
		HashSet<Integer> lotto = new HashSet<>();
		
		while(lotto.size()<6) {
			lotto.add((int)(Math.random()*45+1));
		}
		
		System.out.println("행운의 로또 번호 : " + lotto);
		
		
		
		
		
		
	}

}
