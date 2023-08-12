package na;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Room implements Comparable<Room>{
	private int roomNum;
	private String roomClass;
	private String name;

	// 생성자
	public Room() {

	}

	public Room(int roomNum, String roomClass, String name) {
		super();
		this.roomNum = roomNum;
		this.roomClass = roomClass;
		this.name = name;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomClass() {
		return roomClass;
	}

	public void setRoomClass(String roomClass) {
		this.roomClass = roomClass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "방번호 : " + roomNum + ", 방종류 : " + roomClass + ", 투숙객 : " + name;
	}

	@Override
	public int compareTo(Room r) {
		return Integer.compare(this.getRoomNum(), r.getRoomNum());
	}

}

public class HotelManage {

	private Scanner sc;
	private HashMap<Integer, Room> hotelManageMap;

	public static void main(String[] args) {

		// Room r = new Room();
		// System.out.println(r.toString());
		// System.out.println(r);

		new HotelManage().start();

	}

	public HotelManage() {
		// Integer : roomNum
		// Room : roomNum, roomClass, name
		// Room1 : 201, 싱글룸, ""
		// Room2 : 202, 싱글룸, ""
		hotelManageMap = new HashMap<>();
		sc = new Scanner(System.in);

		for (int i = 200; i < 500; i += 100) {
			for (int j = 1; j < 10; j++) {
				if (i / 100 == 2) {
					hotelManageMap.put((i + j), new Room((i + j), "싱글룸", "-"));
				}
				if (i / 100 == 3) {
					hotelManageMap.put((i + j), new Room((i + j), "더블룸", "-"));
				}
				if (i / 100 == 4) {
					hotelManageMap.put((i + j), new Room((i + j), "스위트룸", "-"));
				}
			}
		}

	}

	private void start() {
		sc = new Scanner(System.in);

		System.out.println();
		System.out.println("***********************************");
		System.out.println("    호텔문을 열었습니다. 어서오십시요. ");
		System.out.println("************************************");
		System.out.println();

		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1:// 체크인
				checkIn();
				break;
			case 2: // 체크아웃
				checkOut();
				break;
			case 3: // 객실상태
				roomClass();
				break;
			case 4: // 업무종료
				endService();
				break;

			}

		}

	}

	private void endService() {
		System.out.println();
		System.out.println("***********************************");
		System.out.println("    호텔영업을 종료합니다. 안녕히가세요. ");
		System.out.println("************************************");
		System.exit(0);
	}

	private void roomClass() {
		sc = new Scanner(System.in);
		System.out.println("--------------------------------------");
		System.out.println("         현재 객실 상태");
		System.out.println("--------------------------------------");
//		System.out.println("       방 번호	 방 종류	 투숙객 이름 ");
		System.out.println("--------------------------------------");
//		System.out.println(r);
		List<Room> list = new ArrayList<>();
		for(int key : hotelManageMap.keySet()){
			Room r = hotelManageMap.get(key);
			list.add(r);
		}
		Collections.sort(list);
		for(Room r : list){
			System.out.println(r);
		}
		System.out.println("--------------------------------------");
	}

	private void checkOut() {
		sc = new Scanner(System.in);
		System.out.println("체크아웃 하실 방 번호를 입력하세요");
		int roomNum = sc.nextInt();
		
		if (!hotelManageMap.containsKey(roomNum)) {
			System.out.println("해당 방은 없습니다.");
			return;
		}else if(hotelManageMap.get(roomNum).getName().equals("-")){
			System.out.println("투숙객이 없습니다");
			return;
		}
		Room r = new Room();
		r = hotelManageMap.get(roomNum);
		r.setName("-");
		hotelManageMap.put(roomNum, r);
		System.out.println(r.getRoomNum() + "호에서 체크아웃 하셨습니다");
	}

	private void checkIn() {
		sc = new Scanner(System.in);

		System.out.println("체크인 하실 방 번호를 입력하세요");
		int roomNum = sc.nextInt();
		if (!hotelManageMap.containsKey(roomNum)) {
			System.out.println("해당 방은 없습니다.");
			return;
		}else if(!hotelManageMap.get(roomNum).getName().equals("-")){
			System.out.println("투숙객이 있습니다");
			return;
		}
		Room r = new Room();
		r = hotelManageMap.get(roomNum);
		System.out.println(r.getRoomNum() + "호의 방의 종류는 " + r.getRoomClass()
				+ "입니다");
		System.out.println(roomNum + "호에 투숙하실 분의 성함을 말씀해주세요");
		String name = sc.next();
		r.setName(name);
		hotelManageMap.put(roomNum, r);

		System.out.println(r);
		System.out.println("위의 내용으로 체크인이 완료되었습니다");
		System.out.println();

	}

	private int displayMenu() {
		sc = new Scanner(System.in);

		System.out.println("-------------------------------------");
		System.out.println("     어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인    2.체크아웃    3.객실상태    4.업무종료");
		System.out.println("---------------------------------------");

		int input = Integer.parseInt(sc.nextLine());
		return input;
	}

}
