import sys

read = sys.stdin.readline
write = sys.stdout.write

class Deque:
    def __init__(self):
        self.deque = []
    def push_front(self, x):
        self.deque.insert(0, x)
    def push_back(self, x):
        self.deque.append(x)
    def pop_front(self):
        x = self.deque.pop(0) if self.size() > 0 else -1
        return x
    def pop_back(self):
        x = self.deque.pop() if self.size() > 0 else -1
        return x
    def size(self):
        return len(self.deque)
    def empty(self):
        return 1 if self.size() == 0 else 0
    def front(self):
        return self.deque[0] if self.size() > 0 else -1
    def back(self):
        return self.deque[self.size() - 1] if self.size() > 0 else -1

deque = Deque()

methods = {
    "push_front": deque.push_front,
    "push_back": deque.push_back,
    "pop_front": deque.pop_front,
    "pop_back": deque.pop_back,
    "size": deque.size,
    "empty": deque.empty,
    "front": deque.front,
    "back": deque.back
}

N = int(read())
answer = []

for i in range(N):
    operation = read().rstrip()
    if len(operation) > 9:
        operation, x = operation.split(" ")
        methods[operation](x)
    else:
        answer.append(str(methods[operation]()))

print("\n".join(answer))