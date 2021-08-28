from sys import stdin
from re import IGNORECASE, compile

read = stdin.readline
yes, no = 'yes', 'no'
p = compile('problem', IGNORECASE)
answer = []

while line := read():
    answer.append(yes if p.search(line) else no)

print("\n".join(answer))