from _ast import If
print("구구단")


def multiply(a, b):
    return a * b; 


for x in range(2, 10):
   # if x % 2 == 0:
    for y in range(1, 10):
        print("{}*{}={}".format(x, y, multiply(x, y)))
