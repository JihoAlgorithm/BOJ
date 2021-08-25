from sys import stdin

read = stdin.readline

N = int(read())
weights = [*map(int, read().split())]
weights.sort()

prefix_sum = 1

for weight in weights:
    if prefix_sum < weight: break
    prefix_sum += weight

print(prefix_sum)