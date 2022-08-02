package na;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/*
 문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고,
 	Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
 	이 프로그램에는 아래와 같은 메뉴가 있는데 이 기능을 모두 구현하시오.
 	
 	메뉴예시)
 		1. 전화번호 등록
 		2. 전화번호 수정
 		3. 전화번호 삭제
 		4. 전화번호 검색
 		5. 전화번호 전체 출력
 		0. 프로그램 종료	
 	(Map의 구조는 key값으로 저장되는 사람의 이름을 사용하고,
 		value 값으로는 'Phone클래스의 인스턴스'로 한다.)
 		
 	실행 예시)
 		------------------------------
 		다음 메뉴를 선택하세요.
 		------------------------------
 		1. 전화번호 등록
 		2. 전화번호 수정
 		3. 전화번호 삭제
 		4. 전화번호 검색
 		5. 전화번호 전체 출력
 		0. 프로그램 종료	
 		------------------------------
 		번호입력 >> 1
 		
 		새롭게 등록할 전화번호 정보를 입력하세요.
 		이름>> 홍길동
 		전화번호>> 010-1111-1111
 		주소>> 대전시 중구 대흥동
 		
 		'홍길동'전화번호 등록 완료!!
 		
 		------------------------------
 		다음 메뉴를 선택하세요.
 		------------------------------
 		1. 전화번호 등록
 		2. 전화번호 수정
 		3. 전화번호 삭제
 		4. 전화번호 검색
 		5. 전화번호 전체 출력
 		0. 프로그램 종료	
 		------------------------------
 		번호입력 >> 1
 		
 		새롭게 등록할 전화번호 정보를 입력하세요.
 		이름>> 홍길동
 		
 		'홍길동'은 이미 등록된 사람입니다.
 		
 		------------------------------
 		다음 메뉴를 선택하세요.
 		------------------------------
 		1. 전화번호 등록
 		2. 전화번호 수정
 		3. 전화번호 삭제
 		4. 전화번호 검색
 		5. 전화번호 전체 출력
 		0. 프로그램 종료	
 		------------------------------
 		번호입력 >> 5
 		
 		-------------------------------------------------
 		번호 		이름		전화번호 			주소
 		-------------------------------------------------
 		1 		홍길동	010-1111-1111	대전시 중구 대흥동
 		~~~~
 		-------------------------------------------------
 		출력완료!
 		
 		------------------------------
 		다음 메뉴를 선택하세요.
 		------------------------------
 		1. 전화번호 등록
 		2. 전화번호 수정
 		3. 전화번호 삭제
 		4. 전화번호 검색
 		5. 전화번호 전체 출력
 		0. 프로그램 종료	
 		------------------------------
 		번호입력 >> 0
 		
 		프로그램을 종료합니다.
 		
 */

class Phone {
	private String name;
	private String phone;
	private String add;

	Phone(String name, String phone, String add) {
		this.add = add;
		this.name = name;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	@Override
	public String toString() {
		return "이름 : " + name + ", 전화번호 : " + phone + ", 주소 : " + add;
	}

}

public class PhoneBookTest {

	public static void main(String[] args) {

		 Scanner sc = new Scanner(System.in);
		 HashMap<String, Phone> map = new HashMap<>();

		while (true) {

			System.out.println("다음 메뉴를 선택하세요.");
			System.out.println("------------------");
			System.out.println("1.전화번호 등록");
			System.out.println("2.전화번호 수정");
			System.out.println("3.전화번호 삭제");
			System.out.println("4.전화번호 검색");
			System.out.println("5.전화번호 전체출력");
			System.out.println("0.프로그램 종료");

			int menu = Integer.parseInt(sc.nextLine());

			switch (menu) {
			case 1: // 전화번호 등록

				System.out.println("전화번호를 등록에 필요한 정보를 입력하세요");
				System.out.println("1.이름을 입력해주세요");
				String name = sc.nextLine();

				if (map.containsKey(name) == false) {
					System.out.println("2.전화번호를 입력해주세요");
					String phone = sc.nextLine();
					System.out.println("3.주소를 입력하세요");
					String add = sc.nextLine();

					map.put(name, new Phone(name, phone, add));

					// System.out.println("1.이름 : " + name + "\n2.전화번호 : " +
					// phone
					// + "\n3.주소 : " + add);
					System.out.println(map.get(name));
					// System.out.println(map.get(name).getName()+);

					System.out.println("위의 정보로 등록이 완료되었습니다.");
					System.out.println();
				}

				else {
					System.out.println(name + "은 이미 등록된 사람입니다.");
					System.out.println();
				}

				break;

			case 2: // 전화번호 수정
				System.out.println("수정하실 이름을 적어주세요");
				name = sc.nextLine();
				// Set<String> keySet = map.keySet();
				// keySet.contains(o)
				if (!map.containsKey(name)) {
					System.out.println("등록되지 않은 이름입니다.");
					System.out.println();
				} else {
					Phone p = map.get(name);
					System.out.println(p.toString());
					System.out.println("1.전화번호 변경  2.주소 변경 3.돌아가기>>");
					int input = Integer.parseInt(sc.nextLine());
					switch (input) {
					case 1:
						System.out.println("변경하실 전화번호를 입력하세요>>");
						String tempPhone = sc.nextLine();
						p.setPhone(tempPhone);
						System.out.println(p);
						System.out.println();
						break;
					case 2:
						System.out.println("변경하실 주소를 입력하세요>>");
						String tempAdd = sc.nextLine();
						p.setAdd(tempAdd);
						System.out.println(p);
						System.out.println();
						break;
					case 3:
						break;
					}
				}
				break;
				
			case 3: // 전화번호 삭제
				System.out.println("삭제하실 이름을 적어주세요");
				name = sc.nextLine();

				if (!map.containsKey(name))
					System.out.println("등록되지 않은 이름입니다.");
				else {
					Phone p = map.remove(name);
					System.out.println("삭제되었습니다.");
					System.out.println();
				}

				break;

			case 4: // 전화번호 검색
				System.out.println("검색하실 이름을 적어주세요");
				name = sc.nextLine();

				if (!map.containsKey(name))
					System.out.println("등록되지 않은 이름입니다.");
				else {
					Phone p = map.get(name);
					System.out.println(p);
					System.out.println();
				}

				break;

			case 5: // 전화번호 전체 출력
				System.out.println("전화번호 목록입니다.");

				Set<String> keySet = map.keySet();
				Iterator<String> it = keySet.iterator();
				int num = 0;
				while (it.hasNext()) {
					num++;
					String key = it.next();
					Phone value = map.get(key);
					System.out.println("================");
					System.out.println(num + "번 목록");
					System.out.println(value.getName());
					System.out.println(value.getPhone());
					System.out.println(value.getAdd());
				}

				System.out.println();
				break;

			case 0: // 프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				System.exit(12035);

			}

		}

	}
}
