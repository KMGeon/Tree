import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
from random import random

form_main = uic.loadUiType("pyqt10.ui")[0]

class MainWindow(QMainWindow, QWidget, form_main):
    def __init__(self) :
        super().__init__()
        self.initUI()
        self.show()
        self.result = ""
        self.computer = self.ran()
    def initUI(self):
        self.setupUi(self)
        self.pb.clicked.connect(self.myClick)
    
    def getStrike(self, computer, player):
        cnt = 0
        p1 = player[:1]
        p2 = player[1:2]
        p3 = player[2]

        c1 = computer[:1]
        c2 = computer[1:2]
        c3 = computer[2]
        
        if p1 == c1:
            cnt += 1
            print(cnt)
        if p2 == c2:
            cnt += 1
            print(cnt)
        if p3 == c3:
            cnt += 1
            print(cnt)
        return int(cnt)
    
    def getBall(self, computer, player):
        cnt = 0
        p1 = player[:1]
        p2 = player[1:2]
        p3 = player[2]

        c1 = computer[:1]
        c2 = computer[1:2]
        c3 = computer[2]
        
        if p1 == c2 or p1 == c3:
            cnt += 1
        if p2 == c1 or p2 == c3:
            cnt += 1
        if p3 == c2 or p3 == c1:
            cnt += 1
        return cnt
    
    def ran(self):
        arr = [
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9
              ]
        for i in range(100):
            rnd = int(random() * len(arr))
            a = arr[0]
            b = arr[rnd]
            arr[0] = b
            arr[rnd] = a
        return str(arr[0]) + str(arr[1]) + str(arr[2])
    
    def myClick(self):
        player = self.le.text()
        strk = self.getStrike(self.computer, player)
        ball = self.getBall(self.computer, player)
        self.result += player + " = " + str(strk) + "S  " + str(ball) + "B\r\n"
        self.te.setText(self.result)
        self.le.setText("")
        if strk == 3:
            QMessageBox.about(self,'야구게임',"정답 : " + str(player))

if __name__ == "__main__" :
    app = QApplication(sys.argv) 
    window = MainWindow() 
    sys.exit(app.exec_())