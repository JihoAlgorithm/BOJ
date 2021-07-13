#include<iostream>
#include<algorithm>
#include<queue>

using namespace std;
typedef long long ll;

const ll BIT = 23;
const ll MASK = ~(-1L << BIT);

int N, L;
priority_queue<ll> pq;

ll getEncodeValue(ll n, int i) {
    return n << BIT | i;
}

ll getIndex(ll e) {
    return e & MASK;
}

ll getNumber(ll e) {
    return e >> BIT;
}

int main() {

    ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> L;

    int n;

    for (int i = 0; i < N; i++) {

        cin >> n;

        pq.push(getEncodeValue(~n + 1, i));
        
        while(getIndex(pq.top()) + L <= i) pq.pop();
        
        cout << ~getNumber(pq.top()) + 1 << " ";

    }

}