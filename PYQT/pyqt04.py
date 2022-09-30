import random
import sys

from PyQt5 import uic
from PyQt5.QtWidgets import *

form_class = uic.loadUiType("pyqt04.ui")[0]


class MyWindow(QMainWindow, form_class):

    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.pb.clicked.connect(self.btnRun_clicked)

    def btnRun_clicked(self):
        for x in random.sample(range(1, 10), 1):
            fistNum = self.int(self.le1.text(x))
            self.le1.setText(fistNum)


if __name__ == "__main__":
    app = QApplication(sys.argv)
    myWindow = MyWindow()
    myWindow.show()
    app.exec_()
