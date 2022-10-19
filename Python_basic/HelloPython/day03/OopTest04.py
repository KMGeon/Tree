class Byden:

    def __init__(self):
        self.ac = 10

    def makewar(self):
        self.ac -= 1

        
class Putin:

    def __init__(self):
        self.nuclear = 6660

    def altzheimer(self):
        self.nuclear -= 1

        
class Mugun(Byden, Putin):

    def __init__(self):
        Byden.__init__(self)
        Putin.__init__(self)

    
mg = Mugun() 
print(mg.ac)
print(mg.nuclear)
mg.makewar()
mg.altzheimer()
print(mg.ac)
print(mg.nuclear)
    
