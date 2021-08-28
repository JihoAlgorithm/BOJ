from re import I, compile

print('\n'.join(['yes' if compile('problem', I).search(s) else 'no' for s in open(0)]))