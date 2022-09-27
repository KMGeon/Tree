print("multiReturn")

def addminmuldiv(a, b):
    return a + b, a - b, a * b, a / b


sum, min, mul, div = addminmuldiv(4, 6)

print(sum)
print(min)
print(mul)
print(div)


sum = addminmuldiv(4, 6)
print(sum[0])
#배열식으로 나온다. 
