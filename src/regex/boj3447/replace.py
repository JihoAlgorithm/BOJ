from sys import stdin

read = stdin.readline
BUG = 'BUG'
answer = []

while string := read():
    while BUG in string:
        string = string.replace(BUG, '')
    answer.append(string)

print(''.join(answer))