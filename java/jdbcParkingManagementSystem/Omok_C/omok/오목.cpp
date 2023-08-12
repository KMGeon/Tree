#include "pch.h"
#include <iostream>
using namespace std;

int main()
{
	int BOARD_SIZE = 19, width, height, i;
	system("cls");
	cout << "	===== Lst's Play Omok ===== " << endl;
	cout << "  ";
	for (width = 1; width < BOARD_SIZE+1; width++)
	{
		if (width < 10)
			cout << " " << width;
		else if (width >= 10)
			cout << width;
	}
	cout << endl;
	for (i = 1; i < BOARD_SIZE+1; i++)
	{
		for (height = 1; height < BOARD_SIZE+1; height++)
		{
			if (height < 10)
				cout << " " << height;
			else if (height >= 10)
				cout << height;
			if (height == 1)
			{
				for (i = 1; i < BOARD_SIZE+1; i++)
				{
					if (i == 1)
						cout << "┌";
					else if (i < BOARD_SIZE )
						cout << " ┬";
					else
						cout << " ┐";
				}
			}
			else if (height < BOARD_SIZE  && height != 1)
			{
				for (i = 1; i < BOARD_SIZE+1; i++)
				{
					if (i == 1)
						cout << "├";
					else if (i < BOARD_SIZE )
						cout << " ┼";
					else
						cout << " ┤";
				}
			}
			else if (height == BOARD_SIZE )
			{
				for (i = 1; i < BOARD_SIZE+1; i++)
				{
					if (i == 1)
						cout << "└";
					else if (i < BOARD_SIZE && i != 1)
						cout << " ┴";
					else
						cout << " ┘";
				}
			}
			cout << endl;
		}
	}
	cout << endl<< "●'s X,Y:";
	return 0;
}