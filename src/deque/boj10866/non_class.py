# 로컬에서는 동작하지 않음
# 아마 백준 채점에서 TC가 파일로 되어 있어 이 방식이 가능한 듯
N, *l = open(0)
q = []

def a(x): q.insert(0, x)
def b(x): q.append(x)
def c(): return q.pop(0) if q else -1
def d(): return q.pop() if q else -1
def e(): return len(q)
def f(): return 0 if q else 1
def g(): return q[0] if q else -1
def h(): return q[-1] if q else -1

o = {
    "push_front": a, "push_back": b, "pop_front": c, "pop_back": d,
    "size": e, "empty": f, "front": g, "back": h
}

for cmd in l:
    if len(cmd := cmd.rstrip()) > 9:
        o[(x := cmd.split())[0]](x[1])
    else:
        print(o[cmd]())
