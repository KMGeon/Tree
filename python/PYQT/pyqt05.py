import random
import sys

from PyQt5 import uic
from PyQt5.QtWidgets import *

form_class = uic.loadUiType("pyqt05.ui")[0]


class MyWindow(QMainWindow, form_class):

    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.pb.clicked.connect(self.btnRun_clicked)

    def btnRun_clicked(self):
        for i in random.sample(range(1, 10), 1):
            print(i)
            if i % 2 == 0:
                com = "홀"
                self.le2.setText("홀")

            else:
                com = "짝"
                self.le2.setText("짝")

        if self.le1.text().__eq__(com):
            self.le3.setText("승리")
        else:
            self.le3.setText("패배")


if __name__ == "__main__":
    app = QApplication(sys.argv)
    myWindow = MyWindow()
    myWindow.show()
    app.exec_()
