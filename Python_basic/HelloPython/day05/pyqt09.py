import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
from cProfile import label

form_class = uic.loadUiType("pyqt09.ui")[0]


class MyWindow(QMainWindow, form_class):

    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.pb1.clicked.connect(self.btnRun1_clicked)

        self.pbc.clicked.connect(self.btnRunc_clicked)
        
    def btnRun1_clicked(self):
        strNew = self.sender().text()
        strOld = self.lbl.text()
    
        self.lbl.setText(strNew + strOld)
    
    def btnRunc_clicked(self):
       QMessageBox.about(self, 'About Title', self.lbl.gettext())
    

if __name__ == "__main__":
    app = QApplication(sys.argv)
    myWindow = MyWindow()
    myWindow.show()
    app.exec_()
