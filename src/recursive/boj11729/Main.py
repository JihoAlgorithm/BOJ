import sys
import math

write = sys.stdout.write

def h(x, y, z, n):
    if n < 1: return
    h(x, z, y, n - 1)
    write('{} {}\n'.format(x, z))
    h(y, x, z, n - 1)

N = int(sys.stdin.readline())
write('{}\n'.format(int(math.pow(2, N) - 1)))
h('1', '2', '3', N)
