package chapter6;

public class CalculatorExample {

	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		
		calculator.powerOn();
		
		int result = calculator.plus(10, 60);
		System.out.println(result);

		System.out.println(calculator.divide(50, 10));
		
		
	}

}
