package kr.or.ddit.basic;

class Util {
	/*
	 * 제너릭 메소드 <T, R> R method(T t)
	 * 
	 * 파라미터 타입과 리턴타입으로 타입 파라미터(타입글자)를 가지는 메소드
	 * 
	 * 선언방법 : 리턴타입 앞에 <> 기호를 추가하고 타입글자를 기술 후 사용함
	 */
	public static <K, V> boolean Compare(Pair<K, V> p1, Pair<K, V> p2) {
		boolean keyCompare = p1.getKey().equals(p2.getKey());
		boolean valueCompare = p1.getValue().equals(p2.getValue());
		
		return keyCompare && valueCompare;
	}
}
/*
 * 멀티타입<K, V> 을 가지는 제너릭 클래스
 * 
 */

class Pair<K, V>{
	private K key;
	private V value;
	
	public Pair(K key, V value) {
	
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	
	public <K, V> void displayAll(K key, V val) {
		System.out.println(key + " : " + val);
	}
	
}


public class T04GenericMethodTest {
	public static void main(String[] args) {
		
		Pair<Integer, String> p1 = new Pair<>(1, "홍길동");
		Pair<Integer, String> p2 = new Pair<>(1, "홍길동");
		
		// 밑에 Util.<Integer, String>Compare(p1, p2); 의 <Integer, String>은 생략가능 
		// why? 들어올 때 이미 <Integer, String>타입이라고 알고 있기 때문에
		boolean result1 = Util.<Integer, String>Compare(p1, p2);
		
		if(result1) {
			System.out.println("논리(의미)적으로 동일한 객체임.");
		}else {
			System.out.println("논리(의미)적으로 동일한 객체아님.");
		}
		
		Pair<String, String> p3 = new Pair<>("001", "홍길동");
		Pair<String, String> p4 = new Pair<>("002", "홍길동");
		
		boolean result2 = Util.Compare(p3, p4);
		
		if(result2) {
			System.out.println("논리(의미)적으로 동일한 객체임.");
		}else {
			System.out.println("논리(의미)적으로 동일한 객체아님.");
		}
		
		// K와 V는 <Integer, String>로 정해졌기 때문에 에러.
		// p1.displayAll("키", 180);
		
		// 메소드만의 <K, V>를 따라간다.
		p1.displayAll("키", 180);
		
	}
}
