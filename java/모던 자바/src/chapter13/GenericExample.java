package chapter13;

public class GenericExample {

	public static void main(String[] args) {
		/*
		 * Generic(제네릭, 지네릭)
		 * JDK 1.5부터 도입
		 * 표현 방법 : <>를 도입
		 * 
		 * 나타난 배경
		 * 데이터타입을 지금 결정하지 않고 실행(사용,생성)할 때 타입을 넣어준다.
		 * 
		 * 모든 것을 담을 수 있는 Object타입
		 */
		
		IntValue value = new IntValue(10);
		System.out.println(value.getValue());
		StringValue value2 = new StringValue("10");
		System.out.println(value2.getValue());
		
		Value value3 = new Value(10);
		System.out.println(value3.getValue());
		Value value4 = new Value("10");
		System.out.println(value4.getValue());
		
		Value value5 = new Value(10);
		if(value5.getValue() instanceof Integer) {
			System.out.println(value5.getValue());
		}
		
		
		Value value7 = new Value("a");
		int value8 = (int) value7.getValue();
	//	System.out.println(value6); //불가
		
		Box<Integer> box = new Box<Integer>();
	//	box.setValue(100);
		int integer = box.getValue();
		
		
	}

}
