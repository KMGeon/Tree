#include "pch.h"
#include "GameManager.h"
#include <iostream>
#include "Enemy.h"
#include "Bullet.h"
using namespace std;

GameManager::GameManager() //게임 전체의 관리
{
	gameObjects = vector<GameObject*>();
	player = new Player(COORD{ 15,20 });
	gameObjects.push_back(new Enemy(COORD{ 7,5 }, this));
	gameObjects.push_back(new Enemy(COORD{ 14,5 }, this));
	gameObjects.push_back(new Enemy(COORD{ 28,5 }, this));
	gameObjects.push_back(new Enemy(COORD{ 7,10 }, this));
	gameObjects.push_back(new Enemy(COORD{ 14,10 }, this));
	gameObjects.push_back(new Enemy(COORD{ 21,10 }, this));
	gameObjects.push_back(new Enemy(COORD{ 28,10 }, this));
	gameObjects.push_back(new Enemy(COORD{ 21,5 }, this));
	score = 0;
	isPlaying = 1;
	isWin = 0;
}

GameManager::~GameManager()
{
}

void GameManager::Board() //맵 주변 출력을 여기서함
{
	cout << "|          Score:        "<<score<<"                 | " << endl;
	cout << "|------------------------------------------| " << endl;
	for (int i = 0; i < 20; i++)
		cout << "|                                          |" << endl;
	cout << "|------------------------------------------| " << endl;
}

void GameManager::tick() 
{
	CheckInput(); //입력을 받음
	if (rand() % 2 == 0) //적들이 좌우로 이동할 때 쓰기 위한 x값을 랜덤생성
	{
		movePosX = 1;
	}
	else
	{
		movePosX = -1;
	}

	player->tick(); //현재는 비워둠, 나중에 필요할지도 모르니 만들어뒀습니다.
	for (int i = 0; i < gameObjects.size(); i++) {
		gameObjects[i]->tick(); //플레이어를 제외한 오브젝트를 움직이게 합니다.
	}
	checkCoilision(); //충돌체크, 이 함수에 구현하면 됩니다.
	drawCall(); //화면에 오브젝트들을 그려줌
}

void GameManager::drawCall()
{
	Board();
	player->draw();
	for (int i = 0; i < gameObjects.size(); i++) {
		gameObjects[i]->draw();
	}

}

void GameManager::CheckInput()
{
	if (GetAsyncKeyState(VK_LEFT) & (1 << 15))
	{
		player->move({ -1,0 });
	}
	if (GetAsyncKeyState(VK_RIGHT) & (1 << 15))
	{
		player->move({ 1,0 });
	}
	if (GetAsyncKeyState(VK_UP) & (1 << 15))
	{
		player->move({ 0,-1 });
	}
	if (GetAsyncKeyState(VK_DOWN) & (1 << 15))
	{
		player->move({ 0,1 });
	}
	if (GetAsyncKeyState(VK_SPACE) && 1)
	{
		gameObjects.push_back(player->shoot());
	}
}

void GameManager::checkCoilision()
{
	for (int i = 0; i < gameObjects.size(); i++) {
		gameObjects[i]->checkCollision();
	}
}

void GameManager::win() //이겼을때, NEW START파일을 보시면 알수있습니다.
{
	isPlaying = 0;
	isWin = 1;
}

void GameManager::lose() //졌을때, NEW START파일을 보시면 알수있습니다.
{
	isPlaying = 0;
	isWin = 0;
}
void GameManager::deleteStage()
{
	delete(player);
	for (int i = 0; i < gameObjects.size(); i++) {
		delete(gameObjects[i]);
	}

}