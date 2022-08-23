#include <bits/stdc++.h>

using namespace std;
int top[100001];
int bottom[100001];
int n, h;
int result = 200001, result_cnt = 0;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

	cin >> n >> h;

	for (int i = 0; i < n/2; i++) 
		cin >> top[i] >> bottom[i];
		
	sort(top, top + (n / 2));
	sort(bottom, bottom + (n / 2));

	for (int i = 1; i <= h; i++) {

		int val = lower_bound(top, top + (n / 2), i ) - top ;
		val += upper_bound(bottom, bottom + (n / 2), h-i ) - bottom ;
		val = n - val;

		if (result == val)
			result_cnt++;
		else if (result > val) { 
			result = val;
			result_cnt = 1;
		}
	}

	cout << result << ' ' << result_cnt << '\n';

}