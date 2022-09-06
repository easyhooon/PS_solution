#include<bits/stdc++.h>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int n;
	cin >> n;

	vector<int> a(n);

	for (int i = 0; i < n; i++) {
		cin >> a[i];
	}

	vector<int> d(n);
	//d[i] = a[1],...,a[i]까지 수열이 있을 때, a[i]을 마지막으로 하는 가장 긴 증가하는 부분 수열의 길이

	//LIS 구현 방법 
	for (int i = 0; i < n; i++) {
		d[i] = 1;
		for (int j = 0; j < i; j++) {
			if (a[j] < a[i] && d[j] + 1 > d[i]) {
				d[i] = d[j] + 1;
			}
		}
	}

	//벡터에서 가장 큰 원소를 찾는 메소드 
	cout << *max_element(d.begin(), d.end()) << '\n';
	return 0;
}