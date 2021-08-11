import sys

r = sys.stdin.readline

N = int(r())
dist = [*map(int, r().split())]
cost = [*map(int, r().split())]

t, m = 0, cost[0]

for d, c in zip(dist, cost[:-1]):
    t += d * (m if m < c else (m := c))

print(t)