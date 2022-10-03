#include "pch.h"
#include "Enemy.h"


Enemy::Enemy(COORD startPos, GameManager* _manager)
{
	pos = startPos;
	image = "[UXU]";
	manager = _manager;
	time = 0;
	isAlive = 1;
}


Enemy::~Enemy()
{

}

void Enemy::tick()
{
	move(COORD{(short) manager->movePosX,0 });
	if (time > 10) {
		move(COORD{ 0,1 });
		time = 0;
	}
	else { time++; }
	if (pos.Y >= 21) manager->lose();
}

void Enemy::die()
{
}

void Enemy::OnCollision()
{

}
