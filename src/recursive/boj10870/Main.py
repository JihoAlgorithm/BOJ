import sys

def fibonacci(n):
    arr[n] = arr[n] if arr[n] > 0 else n if n < 2 else fibonacci(n - 1) + fibonacci(n - 2)
    return arr[n]

N = int(sys.stdin.readline())
arr = [0 for _ in range(N + 1)]
print(fibonacci(N))