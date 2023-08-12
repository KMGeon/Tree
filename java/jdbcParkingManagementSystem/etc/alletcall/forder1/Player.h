#pragma once
#include "GameObject.h"

class Player : public GameObject //게임오브젝트를 상속받음
{
public:
	Player(COORD startPos);
	~Player();

	GameObject* shoot(); //총알 생성해서 넘겨줌
	void move(COORD _pos);
	void tick();
};

