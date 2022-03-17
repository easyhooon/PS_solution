#include <bits/stdc++.h>

using namespace std;

long long dp[66][10];
//dp[i][j] -> i자리에 j값을 넣을 수 있는 경우의 수
int i, j;
long long sum;
int main() {
	
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int T;

	cin >> T;

	for (int k = 0; k < 10; k++)
		dp[1][k] = 1;

	while (T--) {
		int num;

		cin >> num;

		for (i = 2; i <= 65; i++) {
			sum = 0;
			for (j = 0; j < 10; j++) {
				dp[i][j] = dp[i - 1][j] + sum;

				sum += dp[i - 1][j];
			}
		}
		
		cout << dp[num+1][9] << '\n';
	}
}