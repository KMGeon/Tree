package chapter13;

public class IntValue {
	private int value;

	public IntValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "IntValue [value=" + value + "]";
	}

}
