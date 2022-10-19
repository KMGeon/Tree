# 홀/짝을 입력하시오
import random

flag = input("나의 선택")

for i in random.sample(range(1, 10), 1):
    print(i)
    if i % 2 == 0:
        com = "홀"
        print("컴퓨터 :" + com)
       
    else: 
        com = "짝"
        print("컴퓨터 :" + com)
         
if flag.__eq__(com):
    print("승리")
else:
    print("패배")
