package na;

import java.util.ArrayList;

public class ArrayListTest01 {

	public static void main(String[] args) {
		
		ArrayList list1 = new ArrayList();
		// 클래스 명+ 참조변수  // 연산자 +생성자
		
		//add()메서드를 이용해서 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(123);
		list1.add('k');
		list1.add(false);
		list1.add(123.45);
		
		System.out.println("list1 : " + list1);
		System.out.println("size(길이) : " + list1.size());
		 
		//get()메서드로 데이터를 꺼내온다.
		System.out.println("list1의 세번째 데이터 : " + list1.get(2));
		
		//데이터 끼워넣기-add
		list1.add(3,"zzz"); //(끼워넣을 자리,끼워넣을 데이터)
		System.out.println("4번째 자리에 zzz를 끼워넣은 list1 전체 : " + list1);
		
		//데이터 수정하기-set
		//list1.set(3,"yyy")
		
		String temp = (String)list1.set(3, "yyy");
		System.out.println("temp : " + temp);
		System.out.println("4번째 자리에 yyy가 들어간 list1 전체 : " + list1);
		
	}
}
