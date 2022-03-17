#include<iostream>
using namespace std;

int v[101];
bool d[101][1001];

int main() {

	int n, s, m;
	cin >> n >> s >> m;

	for (int i = 1; i <= n; i++) {
		cin >> v[i];
	}

	d[0][s] = true;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j <= m; j++) {
			if (d[i][j] == false) {
				continue;
			}
			if (j - v[i + 1] >= 0) {
				d[i + 1][j - v[i + 1]] = true;
			}
			if (j + v[i + 1] <= m) {
				d[i + 1][j + v[i + 1]] = true;
			}
		}
	}

	int ans = -1;
	for (int i = 0; i <= m; i++) {
		if (d[n][i] == true) {
			ans = i;
		}
	}

	cout << ans;

	return 0;
}