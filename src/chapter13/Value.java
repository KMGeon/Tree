package chapter13;

public class Value {
	//타입이 선언될 때 Object 타입이면 어느 것이든 담을 수 있다.
	private Object value;

	public Value(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Value [value=" + value + "]";
	}
	
}
