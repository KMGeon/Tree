#include "pch.h"
#include "Player.h"
#include "Bullet.h"
#include <string>
#include <iostream>

Player::Player(COORD startPos)
{
	pos = startPos;
	image = ">-0-<";
	isAlive = 1;
}


Player::~Player()
{
}

void Player::move(COORD _pos)
{
	pos.X += _pos.X;
	pos.Y += _pos.Y;
	if (pos.X > 38 || pos.X < 1 || pos.Y < 2 || pos.Y > 21) {
		pos.X -= _pos.X;
		pos.Y -= _pos.Y;
	}
}

void Player::tick()
{

}

GameObject* Player::shoot() 
{
	return new Bullet(pos);
}