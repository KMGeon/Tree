package project2;

import java.util.Scanner;

public class project2 {
	public static void main(String[] args) {
		int board[][] = new int[6][7];
		Scanner sc = new Scanner(System.in);
		int turn = 1;
		int firstTurn = 1;
		int inputNumber = 0;
		String yesNo;

		while (true) {
			if (firstTurn == 1) {
				for (int i = 0; i < board.length; i++) // 바둑판 초기화
				{
					for (int j = 0; j < board[i].length; j++) {
						board[i][j] = 0;
					}

				}
				Intro();
				boardMap(board, -1, turn);
				System.out.printf("-------●`s Turn---------");
				System.out.println();
				firstTurn++;
				System.out.printf(" Select (1-7) :");
				inputNumber = sc.nextInt();

			} else {

				Intro();
				boardMap(board, inputNumber - 1, turn);

				if (resultCheck(board, turn) == 1) // 바둑판 입력후 정답체크 return 값이 1이면 까만돌 승
				{
					System.out.println();
					System.out.println("=======●WINS!=======");
					System.out.print("Play Again? (y/n) : ");
					yesNo = sc.next();
					if (yesNo.equals("y")) {

						firstTurn = 1;
						turn = 1;
						inputNumber = 0;
						continue;

					} else {
						break;
					}
				} else if (resultCheck(board, turn) == 0) // 0이면 하얀돌승
				{
					System.out.println();
					System.out.println("=======ㅇWINS!=======");
					System.out.print("Play Again? (y/n) : ");
					yesNo = sc.next();
					if (yesNo.equals("y")) {

						firstTurn = 1;
						turn = 1;
						inputNumber = 0;
						continue;

					} else {
						break;
					}

				}

				if (turn == 1) {
					System.out.printf("-------○`s Turn---------");
					System.out.println();
					turn = 0;
				} else {
					System.out.printf("-------●`s Turn---------");
					System.out.println();
					turn = 1;
				}
				System.out.printf(" Select (1-7) :");
				inputNumber = sc.nextInt();

			}

		}

	}

	public static void Intro() {
		System.out.print(" ┌───────────────────────┐");
		System.out.println();
		System.out.print(" │                  Let`s play 4 In A Line                 │");
		System.out.println();
		System.out.print(" └───────────────────────┘");
		System.out.println();
	}

	public static void boardMap(int temp[][], int inputNumber, int turn) {
		if (inputNumber != -1 && turn == 1) {
			for (int i = 5; i >= 0; i--) {
				if (temp[i][inputNumber] == 0) {
					temp[i][inputNumber] = 1;
					break;
				}
			}

		} else if (inputNumber != -1 && turn == 0) // 흰돌
		{
			for (int i = 5; i >= 0; i--) {
				if (temp[i][inputNumber] == 0) {
					temp[i][inputNumber] = 2;
					break;
				}
			}
		}
		System.out.print(" ");
		for (int i = 0; i < temp[0].length; i++) {
			System.out.print(" " + (i + 1));
		}
		System.out.println();

		for (int length = 0; length < temp.length; length++) {
			System.out.print(" ");
			for (int width = 0; width < temp[0].length; width++) {
				if (temp[length][width] == 0)
					if (length == 0)
						if (width == 0)
							System.out.print("┏");
						else if (width == (temp[0].length) - 1)
							System.out.println("┓");
						else
							System.out.print("┳");
					else if (length == temp.length - 1)
						if (width == 0)
							System.out.print("┗");
						else if (width == temp[0].length - 1)
							System.out.println("┛");
						else
							System.out.print("┻");
					else if (width == 0)
						System.out.print("┣");
					else if (width == temp[0].length - 1)
						System.out.println("┫");
					else
						System.out.print("╋");
				else if (temp[length][width] == 1) { // 1이면 까만돌
					System.out.print("●");
				} else // 2이면 흰돌
				{
					System.out.print("○");
				}
			}
		}

	}

	public static int resultCheck(int temp[][], int turn) {
		int count1 = 0;
		int count2 = 0;
		int q = 0;

		for (int i = 0; i < 6; i++) // 가로빙고 체크
		{
			for (int j = 0; j < 4; j++) {
				count1 = 0;
				count2 = 0;
				for (int k = j; k < j + 4; k++) {
					if (temp[i][k] == 1 && turn == 1) {
						count1++;
					} else if (temp[i][k] == 2 && turn == 0) {
						count2++;
					}
				}
				if (count1 == 4) {
					return 1;
				} else if (count2 == 4) {
					return 0;
				}
			}
		}

		for (int i = 0; i < 7; i++) // 세로빙고 체크
		{
			for (int j = 0; j < 3; j++) {
				count1 = 0;
				count2 = 0;
				for (int k = j; k < j + 4; k++) {
					if (temp[k][i] == 1 && turn == 1) {
						count1++;
					} else if (temp[k][i] == 2 && turn == 0) {
						count2++;
					}
				}
				if (count1 == 4) {
					return 1;
				} else if (count2 == 4) {
					return 0;
				}
			}
		}

		for (int i = 0; i <= 2; i++) // 정대각선 빙고 아래쪽 체크
		{
			for (int j = 0; j <= i; j++) {
				count1 = 0;
				count2 = 0;
				for (int k = j; k < j + 4; k++) {
					if (temp[k + 2 - q][k] == 1 && turn == 1) {
						count1++;
					} else if (temp[k + 2 - q][k] == 2 && turn == 0) {
						count2++;
					}
				}

				if (count1 == 4) {
					return 1;
				} else if (count2 == 4) {
					return 0;
				}
			}
			q++;
		}
		q = 0;
		for (int i = 0; i <= 2; i++) // 정대각선 빙고 위쪽 체크
		{
			for (int j = 0; j <= i; j++) {
				count1 = 0;
				count2 = 0;
				for (int k = j; k < j + 4; k++) {
					if (temp[k][k + 3 - q] == 1 && turn == 1) {
						count1++;
					} else if (temp[k][k + 3 - q] == 2 && turn == 0) {
						count2++;
					}
				}

				if (count1 == 4) {
					return 1;
				} else if (count2 == 4) {
					return 0;
				}
			}
			q++;
		}

		q = 0;
		for (int i = 0; i <= 2; i++) // 역대각선 빙고 위쪽 체크
		{
			for (int j = 0; j <= i; j++) {
				count1 = 0;
				count2 = 0;
				for (int k = j; k < j + 4; k++) {
					if (temp[k][3 - k + q] == 1 && turn == 1) {
						count1++;
					} else if (temp[k][3 - k + q] == 2 && turn == 0) {
						count2++;
					}
				}

				if (count1 == 4) {
					return 1;
				} else if (count2 == 4) {
					return 0;
				}
			}
			q++;
		}

		int num = 0;
		for (int i = 2; i >= 0; i--) // 역대각선 빙고 아래쪽 체크
		{
			num = 0;
			for (int j = i; j <= 2; j++) {
				count1 = 0;
				count2 = 0;
				q = 0;
				for (int k = j; k < j + 4; k++) {
					if (temp[k][6 - q - num] == 1 && turn == 1) {
						count1++;
					} else if (temp[k][6 - q - num] == 2 && turn == 0) {
						count2++;
					}
					q++;
				}

				if (count1 == 4) {
					return 1;
				} else if (count2 == 4) {
					return 0;
				}
				num++;
			}

		}

		return 3223;
	}
}