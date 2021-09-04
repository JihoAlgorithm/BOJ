from sys import stdin
from itertools import combinations

read = stdin.readline

N, M = map(int, read().split())
town = [read().split() for _ in range(N)]

answer = 100 * 13
home_list = []
shop_list = []

for r in range(N):
    for c in range(N):
        if town[r][c] == '1':
            home_list.append((r, c))
        elif town[r][c] == '2':
            shop_list.append((r, c))

dist_list = [[*map(lambda h: abs(h[0] - s[0]) + abs(h[1] - s[1]), home_list)] for s in shop_list]

for alived_shop_list in combinations((s for s in range(len(shop_list))), M):
    sum_dist = sum(map(min, zip(*[dist_list[i] for i in alived_shop_list])))
    if answer > sum_dist: answer = sum_dist

print(answer)