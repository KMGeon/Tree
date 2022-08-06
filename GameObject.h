#pragma once
#include <Windows.h>
#include <string>


class GameObject
{
public:
	GameObject();
	~GameObject();

	 virtual void tick();
	 virtual void move(COORD _pos);
	 void checkCollision();
	 void draw();
	 virtual void die(); // isAlive를 0으로 만듬
	 int isAlive; //살아있는지 알려주는변수, 0이면 모든 연산을 멈춤
protected:
	COORD pos; //현재 좌표
	std::string image; //오브젝트의 이미지, 화면에 이걸 그려줌

	 virtual void OnCollision(); //충돌이 일어날때 부르는 함수, 충돌이 날때 생길 일을 상속받은 각자 클래스의 OnCollision함수에 구현하면 됩니다.
};

