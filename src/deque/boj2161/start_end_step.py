N = int(input())
ans, deck = [], [*range(1, N + 1)]
while N > 1:
    ans.extend(deck[0 : N - (N & 1) : 2])
    deck = [deck[-1]] + deck[1 : : 2] if N & 1 else deck[1 : : 2]
    N -= N >> 1
print(*ans, *deck)