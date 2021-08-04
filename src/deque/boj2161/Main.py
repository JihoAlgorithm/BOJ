deck, ans = [i for i in range(1, int(input()) + 1)], []
while True:
    ans.append(deck.pop(0))
    if not deck: break
    deck.append(deck.pop(0))
print(' '.join(map(str, ans)))