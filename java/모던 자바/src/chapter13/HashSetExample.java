package chapter13;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetExample {
	public static void main(String[] args) {
		Set<Board> set = new HashSet<>();
		set.add(new Board("제목1", "내용1", "작성자1"));
		set.add(new Board("제목2", "내용2", "작성자2"));
		set.add(new Board("제목3", "내용3", "작성자3"));
		
		set.add(new Board("제목1", "내용1", "작성자1"));
		System.out.println(set.size());
		
		Iterator<Board> iterator = set.iterator();
		while (iterator.hasNext()) {
		//	Board board = (Board) iterator.next();
			Board board = iterator.next();
			System.out.println(board);
		}
		//hasNext() => 데이터가 있으면 꺼내와라. 없으면 반복문 종료
		//타입이 Board로 같으면 변환 필요없음
		//데이터 출력 순서는 (다른 컴퓨터와) 다르다.
		
		set.remove(new Board("제목2", "내용2", "작성자2"));
		
		System.out.println(set.size());
	}
}
