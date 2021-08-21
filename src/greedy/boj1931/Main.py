import sys

read = sys.stdin.readline
START, END = 0, 1
def compare(a, b):
    return a[START] - b[START] if a[END] == b[END] else a[END] - b[END]

N = int(read())
times = [tuple(map(int, read().split())) for _ in range(N)]

times.sort(key = lambda x: (x[END], x[START]))\

meeting_count, end_time = 0, 0

for time in times:
    if end_time <= time[START]:
        end_time = time[END]
        meeting_count += 1

print(meeting_count)