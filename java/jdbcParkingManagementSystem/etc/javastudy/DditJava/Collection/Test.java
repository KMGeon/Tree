package kr.or.ddit.basic;

public class Test {
	
	public static void main(String[] args) {
		Value v = new Value();
		
		v.num1 = 100;
//		v.num2 = 200; > private 이기 때문에 오류발생
		v.setNum2(200);
		
		System.out.println("num1 : " + v.num1);
		System.out.println("num2 : " + v.getNum2());
	}
}

class Value{
	public int num1;
	private int num2;

	public int getNum2() {
		return num2;
		}

	public void setNum2(int num2) {
		if(num2<0 || num2>100){
			System.out.println("자료 오류");
		}else{
			this.num2 = num2;	
		}
	}

	private int num;

//	public int getNum() {
//		return num;
//	}
//
//	public void setNum(int num) {
//		this.num = num;
//	}

	@Override
	public String toString() {
		return "Test [num=" + num + "]";
	}
	
}
