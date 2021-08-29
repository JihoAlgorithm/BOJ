import sys
from re import compile

read = sys.stdin.readline
I = 'Infected!'
G = 'Good'

N = int(read())
p = compile('^[A-F]{0,1}A+F+C+[A-F]{0,1}$')

print('\n'.join([I if p.match(read().rstrip()) else G for _ in range(N)]))