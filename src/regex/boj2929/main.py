from sys import stdin
from re import compile

nop = 0
mc = compile('[A-Z]').split(stdin.readline())

for c in mc[1:-1]:
    nop += 4 - c if (c := (len(c) + 1) % 4) > 0 else 0

print(nop)