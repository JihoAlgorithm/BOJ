import re, sys
r = sys.stdin.readline
N, p = int(r()), r().replace('*', '.*')
exec("print('DA' if re.match(p, r()) else 'NE');" * N)