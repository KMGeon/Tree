#pragma once
#include "GameObject.h"
#include "GameManager.h"
class Enemy : public GameObject //게임오브젝트를 상속받음
{
public:
	Enemy(COORD startPos, GameManager* _manager);
	~Enemy();

	void tick();
	void die();
	void OnCollision();
	static int movePosX;
private:
	GameManager* manager;
	int time;
};

