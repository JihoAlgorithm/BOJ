N, M = map(int, input().split())
count, deque = 0, [i for i in range(1, N + 1)]

for target in list(map(int, input().split())):
    index = deque.index(target)
    count += index if index <= N >> 1 else N - index
    deque = deque[index + 1:] + deque[:index]
    N -= 1

print(count)