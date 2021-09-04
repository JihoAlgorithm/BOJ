from sys import stdin

read = stdin.readline

N, M = map(int, read().split())
town = [[*map(int, read().split())] for _ in range(N)]

home_list = []
shop_list = []

MAX = 100 * 13
answer = MAX

def get_dist(alived_shop_list):
    total_dist = 0
    for hr, hc in home_list:
        min_dist = MAX
        for r, c in alived_shop_list:
            dr = r - hr if r > hr else hr - r
            dc = c - hc if c > hc else hc - c
            dist = dr + dc
            if min_dist > dist: min_dist = dist
        total_dist += min_dist
    return total_dist

def combination(start, count, visited):
    if count == M:
        global answer
        alived_shop_list = []
        for i in range(shop_length):
            if visited & (1 << i) != 0:
                alived_shop_list.append(shop_list[i])
        sum_dist = get_dist(alived_shop_list)
        if answer > sum_dist:
            answer = sum_dist
        return
    for i in range(start, shop_length):
        combination(i + 1, count + 1, visited | (1 << i))

for r in range(N):
    for c in range(N):
        if town[r][c] == 1:
            home_list.append((r, c))
        elif town[r][c] == 2:
            shop_list.append((r, c))
shop_length = len(shop_list)

combination(0, 0, 0)
print(answer)