from sys import stdin
from re import match, sub
r = stdin.readline
N = int(r())
p = sub('[*]', '[a-z]*', r())
# print('\n'.join(['DA' if match(p, r()) else 'NE' for _ in range(N)]))
# exec("print('DA' if match(p, r()) else 'NE');" * N)
a = []
exec("a.append('DA' if match(p, r()) else 'NE');" * N)
print('\n'.join(a))