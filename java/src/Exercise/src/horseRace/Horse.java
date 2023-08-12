
package horseRace;

import java.util.Random;

public class Horse {
	public static void main(String[] args) {
		
	}
}

class HorseRacer extends Thread implements Comparable<HorseRacer>{
	//필드
	public static int currentRank = 0;
	private String horseName;
	private int ranking;
	private int location;
		
	//생성자
	public HorseRacer(String horseName) {
		this.horseName = horseName;
	}
		
	//메소드
	public String getHorseName() {
		return horseName;
	}
	
	public int getRanking() {
		return ranking;
	}
	
	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}
	
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	
	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "경주마" + horseName + "는 " + ranking + "등 입니다.";
	}

	@Override
	public void run() {
		Random random = new Random();
		int ranNum = random.nextInt(200)+1;
		for(int i = 1; i <= 50; i++) {
			location = i;
			try {
				Thread.sleep(ranNum);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public int compareTo(HorseRacer h) {
		return Integer.compare(this.ranking, h.getRanking());
	}
	
	
	

}