#include <bits/stdc++.h>
using namespace std;

#define MAX 51 

int n, num, root_node, node, result = 0;

vector<int> v[MAX];
//기본 값 (0)으로 초기화된 MAX개의 원소를 가지는 vector v를 생성

bool check[MAX];

void bfs(int start) {
	check[start] = true;
	queue<int> q;
	q.push(start);

	while (!q.empty()) {
		node = q.front();
		q.pop();
		
		int child_count = 0;
		for (int i = 0; i < v[node].size(); i++) {
			int next = v[node][i];

			if (!check[next]) {
				child_count++;
				check[next] = true;
				q.push(next);
			}
		}
		//자식이 없으면 result의 계수 1증가
		if (child_count == 0)
		{
			result++;
		}
	}
}

int main()
{
	cin >> n;
	for (int i = 0; i < n; i++)
	{
		cin >> num;
		if (num != -1)
		{
			v[num].push_back(i);
			v[i].push_back(num);
		}
		else
		{
			root_node = i;
		}
	}

	cin >> num;
	check[num] = true;

	if (!check[root_node]) bfs(root_node);

	cout << result << endl;
	return 0;
}
