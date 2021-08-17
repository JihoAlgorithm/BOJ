import sys

read = sys.stdin.readline

N, K = map(int, read().split())

coins = [int(read()) for _ in range(N)]

count = 0

for coin in reversed(coins):
    change = K // coin
    if change > 0:
        K %= coin
        count += change

print(count)