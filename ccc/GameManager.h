#pragma once
#include "Player.h"
#include "GameObject.h"
#include <vector>

class GameManager //게임 전체의 관리
{
public:
	GameManager();
	~GameManager();

	void tick(); //while문에서 계속 돌아가는 함수, 이게 모든 행동을 통제함
	void drawCall(); // 화면에 그림그리는 함수
	void CheckInput(); // 조작키를 입력받는 함수
	void checkCoilision(); // 충돌체크하는 함수, 미구현
	void win(); //  승리할떄 부르는 함수
	void lose();//  패배할때 부르는 함수
	void start(); // 게임을 다시 시작할때 부르는 함수, 미구현
	void deleteStage();
	int movePosX; // 적들이 좌우로 움직일때 이 값을 바탕으로 움직임
	int isPlaying;
	int isWin;
private:
	void Board(); // 맵을 그리는 함수
	Player* player; // 플레이어
	std::vector<GameObject*> gameObjects; // 총알과 적들을 저장해두는 벡터
	int score; // 점수
};
