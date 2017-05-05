#!/usr/bin/python3
# -*- coding: utf-8 -*-

import csv
from datetime import datetime
from matplotlib import pyplot as plt

# read the data
def read_csv(path, delimiter=','):
    with open(path) as csv_file:
        reader = csv.reader(csv_file, delimiter=delimiter)
        for line in reader:
            yield line
data = list(read_csv('collections.csv'))[1:]

# format the data
x, y = [], []
for row in data:
    x.append(datetime.strptime(row[0], '%Y-%m-%d'))
    y.append(int(row[1]))

# plot the data
plt.plot(x, y, '-')
plt.ylim(ymin=0)
plt.show()

