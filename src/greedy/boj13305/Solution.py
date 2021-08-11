import sys

N = int(sys.stdin.readline())
length = list(map(int, sys.stdin.readline().split()))
price = list(map(int, sys.stdin.readline().split()))

minPrice = min(price[0:N - 1])
result = 0

for i in range(1, N):
    if price[i] > minPrice:
        result += length[i] * price[i]
    else:
        result += sum(length[i:N - 1]) * minPrice
        break
print(result)