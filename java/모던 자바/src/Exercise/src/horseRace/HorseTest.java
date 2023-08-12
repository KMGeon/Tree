package horseRace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HorseTest {


	static int strRank = 1;

	public static void main(String[] args) {
		List<Horse1> list = new ArrayList<>();

		list.add(new Horse1("0번마"));
		list.add(new Horse1("1번마"));
		list.add(new Horse1("2번마"));
		list.add(new Horse1("3번마"));
		list.add(new Horse1("4번마"));
		list.add(new Horse1("5번마"));
		list.add(new Horse1("6번마"));
		list.add(new Horse1("7번마"));
		list.add(new Horse1("8번마"));
		list.add(new Horse1("9번마"));

		for (Horse1 horse : list) {
			horse.start();
		}
		for (Horse1 hs : list) {
			try {
				hs.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Collections.sort(list);
		System.out.println("경기끝 ....");
		System.out.println("======================================================");
		System.out.println();
		System.out.println(" 경기 결과 ");

		for (Horse1 horse : list) {
			System.out.println(horse.getName1() + " " + horse.getRank() + "등");
		}
	}
}

class Horse1 extends Thread implements Comparable<Horse1>{
	   private String name1;
	   int rank;
	   
	   public Horse1(String name) {
	      this.name1 = name;
	   }
	   
	   public String getName1() {
	      return name1;
	   }

	   public void setName1(String name) {
	      this.name1 = name;
	   }

	   public int getRank() {
	      return rank;
	   }

	   public void setRank(int rank) {
	      this.rank = rank;
	   }


		@Override
		public void run() {
			for (int i = 0; i < 50; i++) {
				System.out.println("\n" + name1 + " : ");
				for (int j = 0; j < i; j++) {
					System.out.print("-");
				}
				System.out.print(">");

				for (int j = 49; j > i; j--) {
					System.out.print("-");
				}

				System.out.println();
				System.out.println();
				System.out.println();

				try {
					Thread.sleep((int) (Math.random() * 500));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			System.out.println(name1 + " 끝");

			setRank(HorseTest.strRank);
			HorseTest.strRank++;
		}

		@Override
		public int compareTo(Horse1 hor) {
			if (rank > hor.getRank()) {
				return 1;
			} else {
				return -1;
			}
		}
	}