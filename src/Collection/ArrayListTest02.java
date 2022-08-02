package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
문제) 5명의 사람 이름을 입력받아 ArrayList에 저장한 후에 
	저장된 ArrayList의 데이터 중 '김'씨 성의 이름을 모두 출력하시오.
 	(단, 입력은 Scanner객체를 이용한다.) 
*/

public class ArrayListTest02 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ArrayList <String> list1 = new ArrayList<>();
//				   : 이 부분이 제네릭
		
//		System.out.println("1번 사람의 이름을 입력하세요>>");
//		String person1 = sc.nextLine();
//		
//		System.out.println("2번 사람의 이름을 입력하세요>>");
//		String person2 = sc.nextLine();
//		
//		System.out.println("3번 사람의 이름을 입력하세요>>");
//		String person3 = sc.nextLine();
//		
//		System.out.println("4번 사람의 이름을 입력하세요>>");
//		String person4 = sc.nextLine();
//		
//		System.out.println("5번 사람의 이름을 입력하세요>>");
//		String person5 = sc.nextLine();
		
//		list1.add("person1");
//		list1.add("person2");
//		list1.add("person3");
//		list1.add("person4");
//		list1.add("person5");
		
		System.out.println("5명의 이름을 입력하세요");
		
		for(int i =1; i<=5; i++){
			System.out.println(i + "번 사람의 이름을 입력하세요>>");
			String person = sc.nextLine();
			list1.add(person);
		}
		System.out.println(list1);
		
		System.out.println("'김'씨 성을 가진 사람은?");
		for(int i =0; i < list1.size(); i ++){
			String person = list1.get(i);
			
			
			if(list1.get(i).substring(0,1).equals("김")){
				System.out.println(list1.get(i));
			}
		
			if(list1.get(i).charAt(0) == '김'){
				System.out.println(list1.get(i));
			}
			
			if(list1.get(i).indexOf("김")==0){
				System.out.println(list1.get(i));
			}
 		
			if(list1.get(i).startsWith("김")){
				System.out.println(list1.get(i));
			}
			
		}
		
		
			}

}
