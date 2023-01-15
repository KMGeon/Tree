import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
from cProfile import label

form_class = uic.loadUiType("pyqt03.ui")[0]


class MyWindow(QMainWindow, form_class):

    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.pb.clicked.connect(self.btnRun_clicked)
    
    def btnRun_clicked(self):
        firstNum = int(self.le1.text())
        secNum = int(self.le2.text())
        result = firstNum+secNum
        print(firstNum)
        print(secNum)
        print(result)
        self.le3.setText(str(result))
        
        

if __name__ == "__main__":
    app = QApplication(sys.argv)
    myWindow = MyWindow()
    myWindow.show()
    app.exec_()

