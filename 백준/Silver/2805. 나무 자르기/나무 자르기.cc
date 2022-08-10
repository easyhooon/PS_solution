#include <bits/stdc++.h>

using namespace std;

long long height[1111111];

int main(void) {

	cin.tie(NULL);
	ios::sync_with_stdio(false);

	int K, N;
	long long max = 0;
	cin >> K >> N;

	for (int i = 0; i < K; i++)
		cin >> height[i];

	long long left = 0;
	//long long right = LLONG_MAX;
	long long right = INT_MAX;

	while (left <= right) {
		long long mid = (left + right) / 2;
		long long result = 0;
		for (int i = 0; i < K; i++) {
			if (height[i] - mid < 0)
				continue;
			result += height[i] - mid;
		}
		if (result >= N)
		{
			left = mid + 1;
			if (mid > max)
				max = mid;
		}
		else
			right = mid - 1;

	}

	cout << max << '\n';


	return 0;
}