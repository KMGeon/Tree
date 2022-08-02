package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 문제)5명의 별명을 입력받아  ArrayList에 저장한 후 
 이들 중 별명의 길이가 제일 긴 별명을 출력하시오.
 (입력은 Scanner 이용) 
 */

public class ArrayListTest03 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ArrayList<String> aliasList = new ArrayList<>();

		System.out.println("5명의 별명을 입력하세요");

		for (int i = 1; i <= 5; i++) {
			System.out.println(i + "번의 별명을 입력하세요");
			String alias = sc.nextLine();

			aliasList.add(alias);
		}
		System.out.println(aliasList);

		// 제일 긴 별명의 길이가 저장될 변수 선언
		// 이 변수는 List의 첫번째 데이터의 길이로 초기화 한다.
		int maxLength = aliasList.get(0).length();

		for (int i = 1; i < aliasList.size(); i++) {
			if (maxLength < aliasList.get(i).length()) {
				maxLength = aliasList.get(i).length();
			}
		}

		System.out.println("별명이 가장 긴 사람은?");
		for (int i = 0; i < aliasList.size(); i++) {
			if (maxLength == aliasList.get(i).length())
				System.out.println("제일 긴 별명은 " + aliasList.get(i) + "입니다.");
		}

	}
}
