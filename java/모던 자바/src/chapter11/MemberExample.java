package chapter11;

public class MemberExample {

	public static void main(String[] args) {
		Member member1 = new Member("a001", "김은대", 1000);
		Member member2 = new Member("b001", "이쁜이", 1000);
		Member member3 = new Member("c001", "xxx", 1000);
		
		Member member4 = new Member("d001", "yyy", 1000);
		
		if(member1.equals(member2)) {
			System.out.println("member1과 member2는 동등합니다.");
		}else {
			System.out.println("member1과 member2는 동등하지 않습니다.");
		}
	
	}

}
