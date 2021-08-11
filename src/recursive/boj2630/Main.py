import sys

read = sys.stdin.readline

N = int(read())
paper = [list(map(int, read().split())) for _ in range(N)]
color = { 'white': 0, 'blue': 0 }

def slice(x, y, n):
    c = paper[x][y]
    for i in range(x, x + n):
        for j in range(y, y + n):
            if c != paper[i][j]:
                n >>= 1
                slice(x, y, n)
                slice(x, y + n, n)
                slice(x + n, y, n)
                slice(x + n, y + n, n)
                return
    color['white' if c == 0 else 'blue'] += 1

slice(0, 0, N)
print(color['white'])
print(color['blue'])