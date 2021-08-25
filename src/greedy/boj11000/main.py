import sys
from heapq import heappush
from heapq import heappop

read = sys.stdin.readline

N = int(read())
times = [tuple(map(int, read().split())) for _ in range(N)]

times.sort(key = lambda x: x[0])

class_queue = []
heappush(class_queue, times[0][1])

for i in range(1, N):
    if class_queue[0] <= times[i][0]:
        heappop(class_queue)
    heappush(class_queue, times[i][1])

print(len(class_queue))
