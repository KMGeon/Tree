class animal:

    def __init__(self):
        self.hungry = 5

    def timegoby(self):
        if self.hungry > 5:
            self.hungry -= 1

    def mattang(self):
        self.hungry = 10


ani = animal()
print(ani.hungry)
ani.timegoby()
ani.mattang()
print(ani.hungry)


class human(animal):

    def __init__(self):
        self.skillLang = 0

    def momTouch(self, stroke):
        self.skillLang += stroke

    def mattang(self):
        self.hungry = 11


hum = human()
hum.mattang()
print(hum.hungry)
