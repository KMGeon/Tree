package HighJava.src.Generics;

class Util {
	/*
	 * 제너릭 메서드<T,R> R method(T t)
	 * 
	 * 파라미터 타입과 리턴타입으로 타입 파라미터(타입글자)를 가지는 메서드
	 * 
	 * 선언방법 : 리턴타입 앞에 <>기호를 추가하여 타입글자를 기술 후 사용함.
	 */
	public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
		boolean keyCompare = p1.getKey().equals(p2.getKey());
		boolean valueCompare = p1.getValue().equals(p2.getValue());
		return keyCompare && valueCompare;
	}

}

/*
 * 멀티타입 <k,v>을 가지는 제너릭 클래스
 */
class Pair<K, V> {
	private K key;
	private V value;

	public Pair(K key, V value) {
		super();
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
	//만약에 제너릭이 선언이 되었는데 바꾸고 싶으면 메소드 선언 앞에 제너릭을 사용하여 바꿀 수 있다.
	public <K, V> void displayAll(K key, V value) {
		System.out.println(key + ":" + value);
	}
}

public class T04GenericMethodTest {
	public static void main(String[] args) {
		Pair<Integer, String> p1 = new Pair<Integer, String>(1, "홍길동");
		Pair<Integer, String> p2 = new Pair<Integer, String>(1, "홍길동");

		
		p1.<String, Integer>displayAll("키", 180);
		
		boolean result1 = Util.<Integer, String>compare(p1, p2);

		if (result1) {
			System.out.println("논리(의미)적으로 동일한 객체입니다.");
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		} else {
			System.out.println("논리(의미)적으로 동일한 객체 아님.");
		}
		Pair<String, String> p3 = new Pair<String, String>("001", "홍길동");
		Pair<String, String> p4 = new Pair<String, String>("002", "홍길동");

		boolean result2 = Util.compare(p3, p4);
		// == boolean result2 = Util.<string,string>compare(p3,p4);

		if (result2) {
			System.out.println("논리(의미)적으로 동일한 객체입니다.");
		} else {
			System.out.println("논리(의미)적으로 동일한 객체 아님.");
		}
	}
}
