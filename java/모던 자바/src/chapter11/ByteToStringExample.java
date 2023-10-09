package chapter11;

public class ByteToStringExample {
	public static void main(String[] args) {
		//사용자
		//plain text
		String txt = "한글";
		//암호화 알고리즘으로 암호화(밑에가 암호화된 거라고 치자)
		String secureTxt = "asdfasdf";
		
		//서버로 보낼 때 바이트 단위로 보낸다.
		byte[] secureBytes = secureTxt.getBytes();
		
		// 서버
		
		//받은 데이터를 복호화
		byte[] bytes = secureBytes;
		
		//바이트 배열을 문자열로 변환
		String receivedTxt = new String(bytes);
		System.out.println(receivedTxt);
		
		
		//서버로 문자열을 보낼 때 String로 보내는게 아니라 
		//바이트나 문자로 변환하여 하여 보낸다.
		
	}
}
