#include <bits/stdc++.h>

using namespace std;

long long d[91][2];
//d[i][j] = i자리의 이친수의 개수 중에서 j로 끝나는 것의 개수

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int n;
	cin >> n;

	d[1][0] = 0;
	d[1][1] = 1;

	for (int i = 2; i <= n; i++) {
		d[i][0] = d[i - 1][0] + d[i - 1][1];
		d[i][1] = d[i - 1][0];
	}
	cout << d[n][0] + d[n][1] << '\n';
	return 0;
}