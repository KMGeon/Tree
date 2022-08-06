#include "pch.h"
#include <iostream>
#include <stdlib.h>
#include <conio.h>
#include <time.h>
#include <Windows.h>
#include "GameManager.h"
using namespace std;


int main()
{

	srand(time(NULL));

	CONSOLE_CURSOR_INFO cur_info;
	cur_info.dwSize = 1;
	cur_info.bVisible = false;
	SetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE), &cur_info);
	GameManager* gameManager = new GameManager();
		while (true)
		{
			system("cls");

			if (gameManager->isPlaying == 1)
				gameManager->tick(); //여기서 모든 연산이 처리됩니다.
			else {

				if (gameManager->isWin == 1) {
					//이겼을때 어떻게할지
				}
				else {
					//졌을때 어떻게할지
				}
				// 다시시작, 아래 3개의 코드를  다시시작하고 싶은 때으로 옮기면 다시시작됩니다.
				gameManager->deleteStage(); //모든 오브젝트를 지우고
				delete(gameManager); //게임매니저도 지우고
				gameManager = new GameManager(); //새로시작
			}
			Sleep(20);
		}

}