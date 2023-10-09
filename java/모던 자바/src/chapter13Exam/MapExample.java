package chapter13Exam;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class MapExample {

	public static void main(String[] args) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("blue", 96);
		map.put("hong", 86);
		map.put("white", 92);
		
		String name = null;
		int maxScore = 0;
		int totalScore = 0;
		
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			totalScore += entry.getValue();
			Integer score = entry.getValue();
			if(maxScore< score) {
				maxScore = score;
				name = entry.getKey();
			}
		}
		
		System.out.println("평균점수: " + (totalScore/map.size()));
		System.out.println("최고점수: " + maxScore);
		System.out.println("최고점수를 받은 아이디: " + name);
		
	}

}
