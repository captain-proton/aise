#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import sys
import random
import math
from PySide2.QtCore import Qt, Slot, Signal, QObject, QPoint
from PySide2.QtGui import QPainter, QBrush, QPen, QColor, QMouseEvent
from PySide2.QtWidgets import\
    (QAction, QApplication, QMainWindow,
     QStatusBar, QVBoxLayout, QWidget)
from fbs_runtime.application_context.PySide2 import ApplicationContext
from datetime import datetime, timedelta
from dataclasses import dataclass


WINDOW_BG_COLOR = 'rgb(227,242,253)'
DRAW_AREA_BG_COLOR = 'rgb(187,222,251)'
DRAW_AREA_BORDER_COLOR = 'rgb(130,177,255)'

CIRCLE_BORDER_COLOR = QColor.fromRgb(230, 81, 0)
CIRCLE_COLOR = QColor.fromRgb(255, 183, 77)
CIRCLE_RADII = [10, 30, 50]

TRIALS = 90
MIN_CIRCLE_DISTANCE = 30


def calculate_distance(x1: int, y1: int, x2: int, y2: int):
    a = math.pow(x1 - x2, 2)
    b = math.pow(y1 - y2, 2)
    distance = math.sqrt(a + b)
    return distance


class Comm(QObject):

    circle_clicked = Signal(int, int)


class CircleDrawer(QWidget):

    def __init__(self, radius: int):
        QWidget.__init__(self)
        self.comm = Comm()
        self.radius = radius

    def paintEvent(self, paintEvent):

        if not hasattr(self, 'circle_pos'):
            x = random.randint(0, self.width() - self.radius * 2)
            y = random.randint(0, self.height() - self.radius * 2)
            self._circle_pos = QPoint(x, y)

        pen = QPen(CIRCLE_BORDER_COLOR)
        brush = QBrush(CIRCLE_COLOR, Qt.SolidPattern)

        painter = QPainter(self)

        painter.setPen(pen)
        painter.setBrush(brush)
        painter.setRenderHint(QPainter.Antialiasing)
        painter.drawEllipse(self.circle_pos.x(), self.circle_pos.y(),
                            self.radius * 2, self.radius * 2)

    def mouseReleaseEvent(self, event: QMouseEvent):

        event_x = event.pos().x()
        event_y = event.pos().y()
        distance = calculate_distance(self.circle_pos.x() + self.radius,
                                      self.circle_pos.y() + self.radius,
                                      event_x,
                                      event_y)
        if distance <= self.radius:
            self.comm.circle_clicked.emit(event_x, event_y)

    @property
    def circle_pos(self):
        if hasattr(self, '_circle_pos'):
            return self._circle_pos
        return None

    @circle_pos.setter
    def circle_pos(self, position: QPoint):
        if not isinstance(position, QPoint):
            return
        
        self._circle_pos = position

    @property
    def circle_center_x(self):
        return self._circle_pos.x() + self.radius

    @property
    def circle_center_y(self):
        return self._circle_pos.y() + self.radius


class CircleContainer(QWidget):

    def __init__(self):
        QWidget.__init__(self)
        self.comm = Comm()
        self.circle_queue = CIRCLE_RADII * int(TRIALS / len(CIRCLE_RADII))
        random.shuffle(self.circle_queue)

        # outer widget that draws the border
        self.container = QWidget()
        style = (f'background-color: {DRAW_AREA_BG_COLOR};'
                 f'border: 1px solid {DRAW_AREA_BORDER_COLOR};')
        self.container.setStyleSheet(style)

        self.container_layout = QVBoxLayout()
        # inner widget that draws the circles. can't use a combination
        # at this point as the border is removed if paintEvent is
        # overidden
        radius = self.get_new_radius()
        x, y = self.calculate_new_draw_position(radius)
        self.draw_circle(radius, x, y)
        self.container.setLayout(self.container_layout)

        # set layout of this widget (container -> drawer)
        self.layout = QVBoxLayout()
        self.layout.addWidget(self.container)
        self.setLayout(self.layout)

    @Slot(int, int)
    def on_circle_clicked(self, x, y):
        self.container_layout.removeWidget(self.draw_area)
        self.draw_area.close()

        if len(self.circle_queue) > 0:
            radius = self.get_new_radius()
            x, y = self.calculate_new_draw_position(radius)
            self.draw_circle(radius, x, y)

        self.comm.circle_clicked.emit(x, y)

    def calculate_new_draw_position(self, radius: int):

        max_x = self.container.width() - radius * 2
        max_y = self.container.height() - radius * 2

        x, y = (random.randint(0, max_x), random.randint(0, max_y))
        distance = math.inf
        if hasattr(self, 'draw_area'):
            circle_center_x = self.draw_area.circle_center_x
            circle_center_y = self.draw_area.circle_center_y
            distance = calculate_distance(circle_center_x,
                                          circle_center_y,
                                          x, 
                                          y)

        return (x, y)\
            if distance >= MIN_CIRCLE_DISTANCE\
            else self.calculate_new_draw_position()

    def get_new_radius(self):
        return self.circle_queue.pop()

    def draw_circle(self, radius: int, x: int, y: int):
        self.draw_area = CircleDrawer(radius)
        self.draw_area.circle_pos = QPoint(x, y)
        self.draw_area.comm.circle_clicked.connect(self.on_circle_clicked)
        self.container_layout.addWidget(self.draw_area)
        self.last_spawn_time

    @property
    def is_circle_queue_empty(self):
        return len(self.circle_queue) == 0


class MainWindow(QMainWindow):
    def __init__(self):
        QMainWindow.__init__(self)
        self.setWindowTitle("Exercise 8B - Verheyen")
        self.setStyleSheet(f'background-color: {WINDOW_BG_COLOR};')

        self.circle_container = CircleContainer()
        self.circle_container.comm.circle_clicked.connect(
            self.on_circle_clicked)
        self.setCentralWidget(self.circle_container)

        self.status = QStatusBar()
        self.status.showMessage("Hello HCI Exercise 8B", 3000)
        self.setStatusBar(self.status)

    @Slot(int, int)
    def on_circle_clicked(self, x, y):
        finished = self.circle_container.is_circle_queue_empty
        self.status.showMessage(f"Deleted circle on ({x}, {y}) - ", 3000)


if __name__ == "__main__":

    appctxt = ApplicationContext()

    window = MainWindow()
    window.showMaximized()

    exit_code = appctxt.app.exec_()
    sys.exit(exit_code)
