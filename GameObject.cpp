#include "pch.h"
#include "GameObject.h"
#include <iostream>

GameObject::GameObject()
{
}


GameObject::~GameObject()
{
}

void GameObject::tick()
{
}
void GameObject::move(COORD _pos) 
{
	if (!isAlive) {
		return;
	}
	pos.X += _pos.X;
	pos.Y += _pos.Y;
	if (pos.X > 38) {
		pos.X = 38;
	}
	if (pos.X < 1) {
		pos.X = 1;
	}
	if (pos.Y < 2) {
		pos.Y = 2;
	}
	if (pos.Y > 21) {
		pos.Y = 21;
	}
}

void GameObject::checkCollision() 
{
	if (false) { //이 조건문을 수정해서 충돌체크를 하시면 될것같습니다.
		OnCollision();
	}	
}

void GameObject::draw() 
{
	if (!isAlive) {
		return;
	}
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), pos);
	std::cout << image;
}

void GameObject::die() 
{
	isAlive = 0;
}

void GameObject::OnCollision() 
{

}