#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import sys
import random
import math
from PySide2.QtCore import Qt, Slot, Signal, QObject
from PySide2.QtGui import QPainter, QBrush, QPen, QColor, QMouseEvent
from PySide2.QtWidgets import\
    (QAction, QApplication, QMainWindow,
     QStatusBar, QVBoxLayout, QWidget)


WINDOW_BG_COLOR = 'rgb(227,242,253)'
DRAW_AREA_BG_COLOR = 'rgb(187,222,251)'
DRAW_AREA_BORDER_COLOR = 'rgb(130,177,255)'

CIRCLE_BORDER_COLOR = QColor.fromRgb(230, 81, 0)
CIRCLE_COLOR = QColor.fromRgb(255, 183, 77)
CIRCLE_RADIUS = 100


class Comm(QObject):

    circle_clicked = Signal(int, int)


class CircleDrawer(QWidget):

    def __init__(self):
        QWidget.__init__(self)
        self.comm = Comm()
        self.min_x = 0
        self.max_x = self.width() - CIRCLE_RADIUS * 2

        self.min_y = 0
        self.max_y = self.height() - CIRCLE_RADIUS * 2

        self.base = (random.randint(self.min_x, self.max_x),
                     random.randint(self.min_y, self.max_y))

    @property
    def center(self):
        return (self.base[0] + CIRCLE_RADIUS,
                self.base[1] + CIRCLE_RADIUS)

    def paintEvent(self, paintEvent):

        pen = QPen(CIRCLE_BORDER_COLOR)
        brush = QBrush(CIRCLE_COLOR, Qt.SolidPattern)

        painter = QPainter(self)

        painter.setPen(pen)
        painter.setBrush(brush)
        painter.setRenderHint(QPainter.Antialiasing)
        painter.drawEllipse(self.base[0], self.base[1],
                            CIRCLE_RADIUS * 2, CIRCLE_RADIUS * 2)

    def mouseReleaseEvent(self, event: QMouseEvent):

        a = math.pow(self.center[0] - event.pos().x(), 2)
        b = math.pow(self.center[1] - event.pos().y(), 2)
        distance = math.sqrt(a + b)
        if distance <= CIRCLE_RADIUS:
            self.comm.circle_clicked.emit(event.pos().x(), event.pos().y())


class CircleContainer(QWidget):

    def __init__(self):
        QWidget.__init__(self)
        self.comm = Comm()

        # outer widget that draws the border
        self.container = QWidget()
        style = (f'background-color: {DRAW_AREA_BG_COLOR};'
                 f'border: 1px solid {DRAW_AREA_BORDER_COLOR};')
        self.container.setStyleSheet(style)

        self.container_layout = QVBoxLayout()
        # inner widget that draws the circles. can't use a combination
        # at this point as the border is removed if paintEvent is
        # overidden
        self.draw_circle()
        self.container.setLayout(self.container_layout)

        # set layout of this widget (container -> drawer)
        self.layout = QVBoxLayout()
        self.layout.addWidget(self.container)
        self.setLayout(self.layout)

    @Slot(int, int)
    def on_circle_clicked(self, x, y):
        self.container_layout.removeWidget(self.draw_area)
        self.draw_area.close()

        self.draw_circle()

        self.comm.circle_clicked.emit(x, y)

    def draw_circle(self):
        self.draw_area = CircleDrawer()
        self.draw_area.comm.circle_clicked.connect(self.on_circle_clicked)
        self.container_layout.addWidget(self.draw_area)


class MainWindow(QMainWindow):
    def __init__(self):
        QMainWindow.__init__(self)
        self.setWindowTitle("Exercise 8B - Verheyen")
        self.setStyleSheet(f'background-color: {WINDOW_BG_COLOR};')

        self.widget = CircleContainer()
        self.widget.comm.circle_clicked.connect(self.on_circle_clicked)
        self.setCentralWidget(self.widget)

        self.status = QStatusBar()
        self.status.showMessage("Hello HCI Exercise 8B", 3000)
        self.setStatusBar(self.status)

    @Slot(int, int)
    def on_circle_clicked(self, x, y):
        self.status.showMessage(f"Deleted circle on ({x}, {y})", 3000)


if __name__ == "__main__":
    # Qt Application
    app = QApplication(sys.argv)

    window = MainWindow()
    window.showMaximized()

    sys.exit(app.exec_())
