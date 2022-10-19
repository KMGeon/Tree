class Car:
    def __init__(self):
        self.speed = 0

    def accel(self, strength):
        self.speed += strength

    def __del__(self):
       