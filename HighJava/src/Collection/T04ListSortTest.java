package Collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T04ListSortTest {
	public static void main(String[] args) {

		ArrayList<Member> list = new ArrayList<Member>();

		list.add(new Member(1, "홍길동", "01076004475"));
		list.add(new Member(5, "변학도", "01045674475"));
		list.add(new Member(9, "성춘향", "01084524475"));
		list.add(new Member(3, "이순신", "01097564475"));
		list.add(new Member(6, "강감찬", "01014234475"));
		list.add(new Member(2, "일지매", "01098524475"));

		System.out.println("정렬 전.");
		for (Member mem : list) {
			System.out.println(mem);
		}
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");

		Collections.sort(list);

		System.out.println("이름의 오름차순으로 정렬 후: ");
		for (Member mem : list) {
			System.out.println(mem);
		}
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");

//		Collections.sort(list, new SortNumDesc());
//		System.out.println("comparator:" + list);

	}
}
/*
 * // * 정렬 기준의 외부 선언을 위해서 comparator 인터페이스를 구현하면 된다. member의 번호의 내림차순으로 정렬하기 //
 */
//
//class SortNumDesc implements Comparator<Member> {
//
//	@Override
//	public int compare(Member mem1, Member mem2) {
//
////		if (mem1.getNum() > mem2.getNum()) {
////			return -1;
////		} else if (mem1.getNum() == mem1.getNum()) {
////			return 0;
////		} else
////			return 1;
////	}
////Wrapper클래스에서 제공하는 메서드를 이용하는 방법
//		// 오름차순
//		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
//	}

class Member implements Comparable<Member> {

	private int num;
	private String name;
	private String tel;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	@Override
	public int compareTo(Member mem) {

		return this.getName().compareTo(mem.getName());
	}

	/*
	 * 회원이름을 기준으로 오름차순 정렬이 될 수 있도록 작성하기 comparable은 this와 mem 비교
	 */

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

}
