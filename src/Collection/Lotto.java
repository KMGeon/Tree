package na;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Lotto {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 1.로또 구매 여부
		// 2.로또 금액 투입
		// 3.로또 제한 조건

		while (true) {

			System.out.println("1.로또구매  2.종료");
			int input = sc.nextInt();

			if (input == 1) {
				System.out.println("구매금액을 적어주세요.");
				int money = sc.nextInt();
				int count = money / 1000;
				int change = money - count * 1000;

				if (1000 > money) {
					System.out.println("금액이 너무 적습니다.");
					continue;
				}
				if (money > 100000) {
					System.out.println("하루 구입가능한 로또를 초과하였습니다.");
					continue;
				}

				System.out.println("구매가능한 로또는 " + count + "장 입니다.");
				System.out.println("잔돈은 " + change + "입니다.");

				for (int i = 0; i < count; i++) {
					HashSet<Integer> lotto = new HashSet<>();

					while (lotto.size() < 6) {
						lotto.add((int) (Math.random() * 45 + 1));
					}

					ArrayList<Integer> lottoList = new ArrayList<>(lotto);
					Collections.sort(lottoList);

					System.out.println("로또번호" + (i + 1) + "는 : " + lottoList
							+ "입니다.");
				}

			}

			if (input == 2) {
				System.out.println("종료합니다.");
				System.exit(0);
			}

		}
	}

}
