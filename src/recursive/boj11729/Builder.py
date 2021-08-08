import sys

f = [
    ['하', '1 2', '1 3'],
    ['2 1', '노', '2 3'],
    ['3 1', '3 2', '이']
]

def h(x, y, z, n):
    if n < 1: return
    h(x, z, y, n - 1)
    s.append(f[x][z])
    h(y, x, z, n - 1)

s = []
N = int(sys.stdin.readline())

s.append(str(2 ** N - 1))
h(0, 1, 2, N)

print('\n'.join(s))