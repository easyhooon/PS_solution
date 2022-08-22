#include <iostream>
//#include <string>
//#include <vector>
#include <algorithm>
//
using namespace std;
//
int a[1001];


int n, target = 1;
int main(void) {
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> a[i];
	}
	sort(a, a + n);
	for (int i = 0; i < n; i++) {
		if (target < a[i]) break;
		target += a[i];
		//지리네 
	}
	cout << target;
	return 0;

}