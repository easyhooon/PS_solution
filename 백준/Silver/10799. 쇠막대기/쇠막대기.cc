#include <bits/stdc++.h>

using namespace std;

int solution(string str) {
	int answer = 0;
	stack<char> pipe;
	for (int i = 0; i < str.length(); i++) {
		if (str[i] == '(') pipe.push(str[i]);
		else {
			pipe.pop();
			if (str[i - 1] == '(') answer += pipe.size();
			else answer += 1;
		}
	}
	return answer;
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	string str;
	cin >> str;
	int answer = solution(str);
	cout << answer << '\n';
	return 0;
}