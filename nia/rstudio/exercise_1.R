x <- matrix(1:9, ncol = 3, nrow = 3)
x
exists("x")
class("some")
class(1)
class(x)

data1 <- 1:5
data2 <- c(TRUE, FALSE, TRUE, TRUE, FALSE)

df <- data.frame(data1, data2)
df

summary(df)

names(df) <- c('Spalte 1', 'Spalte 2')
df

s = seq(0, 10, 0.2)
y = sin(s)
x = s

plot(x, y, type = "l")
lines(sin(0:10))
