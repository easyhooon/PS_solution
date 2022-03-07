#include <bits/stdc++.h>

using namespace std;

using pr = pair<int, int>;

int n, m, board[51][51], ans = 0x7fffffff;
bool check[2501];

vector<pr> v, v2;
vector<int> t;

void select(int now, int cnt)
{
	int i, j;
	if (cnt == m)
	{
		int tmp = 0; //temp_ans
		for (i = 0; i < v2.size(); i++)
		{
			int mn = 0x7fffffff;
			for (j = 0; j < m; j++)
			{
				mn = min(mn, abs(v2[i].first - v[t[j]].first) + abs(v2[i].second - v[t[j]].second));
			}
			tmp += mn;
		}
		ans = min(ans, tmp);
		return;
	}
	for (i = now; i < v.size(); i++)
	{
		if (check[i])
			continue;

		check[i] = true;
		t.push_back(i);

		select(i + 1, cnt + 1);
		check[i] = false;
		t.pop_back(); //뒤에다 박았으니까 뒤에서 끄냄
	}
}
int main()
{
	int i, j;

	cin >> n >> m;

	for (i = 0; i < n; i++)
	{
		for (j = 0; j < n; j++)
		{
			cin >> board[i][j];

			if (board[i][j] == 1)
				v2.push_back({i, j});
			else if (board[i][j] == 2)
				v.push_back({i, j});
		}
	}

	select(0, 0);

	cout << ans;

	return 0;
}