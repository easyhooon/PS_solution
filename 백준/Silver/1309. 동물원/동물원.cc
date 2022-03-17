#include <bits/stdc++.h>
using namespace std;

#define MOD 9901

int dp[100001];

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int n;

	cin >> n;

	dp[0] = 1;
	dp[1] = 3;

	for (int i = 2; i <= n; i++) {
		dp[i] = (dp[i - 1] * 2 + dp[i - 2]) % MOD;
	}

	cout << dp[n] << '\n';

	return 0;
}