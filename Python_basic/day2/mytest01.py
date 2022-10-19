# 첫 수를 넣으세요
# 끝 수를 넣으세요

a = input("좋아하는 숫자를 넣으세요")
b = input("끝 수를 넣으세요")
c = 0

a1 = int(a)
b1 = int(b)

sum = 0
for i in range(a1, b1 + 1):
    sum += i
    print(sum)

print("합은".format(a1, b1, sum))
