from re import compile
from sys import stdin

read = stdin.readline
BUG = 'BUG'
string = ''
p = compile(BUG)

while line := read(): string += line
while BUG in string: string = p.sub('', string)

print(string)