import sys

input = sys.stdin.readline

N = int(input())
apple_map = [[False for i in range(N)] for j in range(N)]
snake_map = [[False for i in range(N)] for j in range(N)]

K = int(input())
for i in range(K):
    x, y = map(int, input().split())
    apple_map[x - 1][y - 1] = True

L = int(input())
command_time = []
command_operation = []
for i in range(L):
    x, c = input().split()
    command_time.append(int(x))
    command_operation.append(c)

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

snake_map[0][0] = True

snake_deq = []
snake_deq.append((0, 0))


def isOut(x, y):
    if x < 0 or y < 0 or x == N or y == N: return True
    return False

def crawl(x, y, d):
    time = 0
    for c in range(L):
        start_time = 0 if c == 0 else command_time[c - 1]
        for t in range(start_time, command_time[c]):
            time += 1
            nx, ny = x + dx[d], y + dy[d]
            if isOut(nx, ny) or snake_map[nx][ny]:
                sys.stdout.write(str(time))
                return True
            snake_map[nx][ny] = True
            snake_deq.append((nx, ny))
            if apple_map[nx][ny]:
                apple_map[nx][ny] = False
            else:
                x, y = snake_deq.pop(0)
                snake_map[x][y] = False
            x, y = nx, ny
        d = (d + (1 if command_operation[c] == "D" else 3)) % 4
    return x, y, d, time

def more_crawl(state):
    x, y, d, time = state[0], state[1], state[2], state[3]
    while True:
        time += 1
        nx, ny = x + dx[d], y + dy[d]
        if isOut(nx, ny) or snake_map[nx][ny]:
            sys.stdout.write(str(time))
            sys.exit(0)
        snake_map[nx][ny] = True
        snake_deq.append((nx, ny))
        x, y = snake_deq.pop(0)
        snake_map[x][y] = False
        x, y = nx, ny

res = crawl(0, 0, 0)
if res == True: sys.exit(0)
else: more_crawl(res)
"""
5
0
5
4 D
8 D
12 D
15 D
20 L
output: 20

8
3
5 4
5 8
2 5
6
7 D
11 D
15 D
18 D
19 D
20 D
output: 21

8
5
6 1
7 3
3 5
5 7
5 6
12
2 D
8 D
10 D
12 D
18 L
20 L
22 L
24 L
25 L
28 L
32 D
33 L
output: 27
"""