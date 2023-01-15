import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
from cProfile import label

form_class = uic.loadUiType("pyqt02.ui")[0]


class MyWindow(QMainWindow, form_class):

    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.pb.clicked.connect(self.btnRun_clicked)
    
    def btnRun_clicked(self):
        result = int(self.le.text()) * 2
        print(self.le.text())
        self.le.setText(str(result))
        
        

if __name__ == "__main__":
    app = QApplication(sys.argv)
    myWindow = MyWindow()
    myWindow.show()
    app.exec_()

