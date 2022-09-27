# 첫수를 넣으세요 , 끝수를 넣으세요 ,배수를 넣으세요

a = input("첫수")
b = input("끝수")
c = input("배수")

sum = 0
for i in range(int(a), int(b) + 1):
   if i % int(c) == 0:
    sum += i
    
print(sum)

