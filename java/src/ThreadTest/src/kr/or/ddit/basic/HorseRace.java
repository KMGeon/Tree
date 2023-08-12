package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HorseRace {

	public static void main(String[] args) {
		Horse[] horse = new Horse[] {
				
				new Horse ("1번말"),
				new Horse ("2번말"),
				new Horse ("3번말"),
				new Horse ("4번말"),
				new Horse ("5번말"),
				new Horse ("6번말"),
				new Horse ("7번말"),
				new Horse ("8번말"),
				new Horse ("9번말"),
				new Horse ("10번말")				
		};
		
		playState state = new playState(horse);
		
		for(Horse h : horse) {
			h.start();
		}
		state.start();
		
		for(Horse h : horse) {
			try {
				h.join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			state.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("경기가 끝났습니다.");
		System.out.println();
		
		Arrays.sort(horse);
		
		System.out.println("경기결과");
		for(Horse h : horse) {
			System.out.println(h);
		}
		
		
	}

}

class Horse extends Thread implements Comparable<Horse> {
	public static int currentRank = 1;
	private String horseName;
	private int rank;
	private int location;

	public Horse(String horseName) {
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "경주마" + horseName + "은" + rank + "등 입니다.";
	}

	@Override
	public int compareTo(Horse h) {
		return Integer.compare(this.rank, h.getRank());
	}

	@Override
	public void run() {
		Random random = new Random();
		int ranNum = random.nextInt(200)+1;
		for (int i = 1; i <= 50; i++) {
			location = i;
			try {
				Thread.sleep(ranNum);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		rank = Horse.currentRank++;
	}
}

class playState extends Thread {
	private Horse[] horse;

	public playState(Horse[] horse) {
		this.horse = horse;
	}

	@Override
	public void run() {
		while (true) {
			if (Horse.currentRank == horse.length)
				break;
			else {
				for (int i = 1; i <= 10; i++) {
					System.out.println();
				}
				for (int i = 0; i < horse.length; i++) {
					System.out.print(horse[i].getHorseName() + " : ");
					for (int j = 1; j <= 50; j++) {
						if (horse[i].getLocation() == j) {
							System.out.print(">");
						} else {
							System.out.print("-");
						}
					}
					System.out.println();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}