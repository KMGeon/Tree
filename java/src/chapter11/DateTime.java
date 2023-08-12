package chapter11;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
	public static void main(String[] args) {
		Date date = new Date();
		// 2022-07-05 우리나라는 이런 형식을 좋아함
		
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//System.out.println(format.format(date));	
		
		//Calendar(싱글톤형식)
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int dayOfMonth = calendar.get(Calendar.DATE);
		System.out.println(year + "년" + ++month + "월" + dayOfMonth + "일");
		
		
		//localDateTime 은 시간까지 표현
		//method chaining 방식 => Value Object(VO)를 만들 때 빌더패턴으로 만드는 것과 동일
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 E요일 hh:mm:ss")));
		
		
		
	}
}
