###### [*BOJ*](../../README.md) / *15649*

# N과 M (1)

생각보다 단순했던, 아니지 기본 문제다.

성능을 끌어올리려고 노력했는데, 엄청난 성과는 없었다.

확실히 알 수 있었던 건 역시 비트마스크 기법이 이렇게 작은 범위에서 사용되는 경우에는 별 이득이 없다는 것을 확인했다. 또한 출력량이 많을 땐 역시 BufferedWriter가 효과적이다. 그리고 단순 int 타입 배열보다 한 자리 숫자의 경우는 역시 char 타입 배열이 효율적이다.