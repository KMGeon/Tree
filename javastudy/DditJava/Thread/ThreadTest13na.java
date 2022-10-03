package na;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadTest13na {

	public static void main(String[] args) {
		/*
		Horse[] ths = new Horse[] { new Horse("1번말"), new Horse("2번말"),
				new Horse("3번말"), new Horse("4번말"), new Horse("5번말"),
				new Horse("6번말"), new Horse("7번말"), new Horse("8번말"),
				new Horse("9번말"), new Horse("10번말") };
		*/
		List<Horse> ths = new ArrayList<>();
		ths.add(new Horse("1번말"));
		ths.add(new Horse("2번말"));
		ths.add(new Horse("3번말"));
		ths.add(new Horse("4번말"));
		ths.add(new Horse("5번말"));
		ths.add(new Horse("6번말"));
		ths.add(new Horse("7번말"));
		ths.add(new Horse("8번말"));
		ths.add(new Horse("9번말"));
		ths.add(new Horse("10번말"));
		
		for (Horse hs : ths) {
			hs.start();
		}
		
		while(true){
			for(int i=0; i<ths.size(); i++){
				System.out.print(ths.get(i).name+" : ");
				for(int j=1; j<=50; j++){
					if(ths.get(i).place == j){
						System.out.print(">");
						continue;
					}
					System.out.print("-");
				}
				System.out.println();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			int exitCnt = 0;
			for(int i=0; i<ths.size(); i++){
				if(ths.get(i).getState() == Thread.State.TERMINATED) exitCnt++;
			}
			if(exitCnt==10) break;
			System.out.println("\n=========================================================\n");
		}
		
		
		/*
		for (Horse hs : ths) {
			try {
				hs.join();
			} catch (InterruptedException e) {

			}
		}
		*/
		System.out.println();
		Collections.sort(ths);
		System.out.println("===경기결과===");
		for(Horse h: ths){
			System.out.println(h);
		}
	}

}

class Horse extends Thread implements Comparable<Horse>{
	public static int setRank = 1;
	
	public String name;
	public int rank;
	public int place=1;

	public Horse(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			//System.out.println(name + "의 위치 : " + place);
			try {
				Thread.sleep((int) (Math.random() * 500));
			} catch (InterruptedException e) {
			
			}
			if(place<50) place++;
		}
		System.out.println(name + "경기 완료");
		rank = setRank;
		setRank++;
		System.out.println("등수는 "+rank);
	}

	@Override
	public int compareTo(Horse h) {
		return Integer.compare(rank, h.rank);
	}

	@Override
	public String toString() {
		return name + " : " + rank + "등 ";

	/*
	// 이미지로 표현하기
	public void position() {
		int[] dash = new int[50];
		int[] position = new int[50];

		for (int i = 0; i <= 50; i++) {
			for (int j = 0; j <= 50; j++) {
				System.out.print(j);
			}
		}
		System.out.print("-");
*/
	
	
	}	
	
}
