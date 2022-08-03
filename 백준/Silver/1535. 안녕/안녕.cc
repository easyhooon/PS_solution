#include<iostream>
#include<algorithm>

//Knapsack algorithm
using namespace std;

int K[21][101]; 

int knapsack(int n, int W, int price[], int weight[]) {

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= W; j++) {
			if (weight[i] > j) {
				K[i][j] = K[i - 1][j];
			}
			else {
				K[i][j] = max(K[i - 1][j - weight[i]] + price[i], K[i - 1][j]);
			}
		}
	}
	return K[n][W];
}

int main() {

	int N, L[21], J[21];

	cin >> N;

	for (int i = 1; i <= N; i++) {
		cin >> L[i];
	}
	for (int i = 1; i <= N; i++) {
		cin >> J[i];
	}

	cout << knapsack(N, 99, J, L);

	return 0;
}