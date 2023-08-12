#pragma once
#include "GameObject.h"
class Bullet : public GameObject //게임오브젝트를 상속받음
{
public:
	Bullet(COORD& startPos);
	~Bullet();

	void tick();
};

