import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
from cProfile import label

form_class = uic.loadUiType("pyqt06.ui")[0]


class MyWindow(QMainWindow, form_class):

    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.pb.clicked.connect(self.btnRun_clicked)

    def btnRun_clicked(self):
        for x in range(1, 10):
            print("{}*{}={}".format(self.le1.text(), x, int(x) * int(self.le1.text())))

if __name__ == "__main__":
    app = QApplication(sys.argv)
    myWindow = MyWindow()
    myWindow.show()
    app.exec_()
