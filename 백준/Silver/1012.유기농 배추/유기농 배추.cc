#include <bits/stdc++.h>
using namespace std;

struct coord
{
	int y, x;
};

int n, m, k;

int board[50][50]; //맵 배열
bool visited[50][50]; //방문 했는지 여부를 체크 

int dy[4] = {0, 1, -1 ,0};
int dx[4] = {1, 0, 0, -1};

void init()
{
	for (int i = 0; i < 50; i++)
	{
		for (int j = 0; j < 50; j++)
		{
			board[i][j] = 0; 
			visited[i][j] = false;
		}
	}
}
bool isin(int y, int x)
{
	return 0 <= y && y < n && 0 <= x && x < m; //모든 조건을 만족하면 (true) 1을 반환
}
void bfs(int y, int x)
{
	queue<coord> q;

	visited[y][x] = true;
	q.push({ y, x });
	while (!q.empty()) //queue가 빌때 까지 bfs(탐색)를 진행 
	{
		auto top = q.front();
		auto &cy = top.y, &cx = top.x;
        // auto cy = top.y, auto cx = top.x;
		q.pop();

		for (int i = 0; i < 4; i++)
		{
            int ny = cy + dy[i], nx = cx + dx[i];
			if (isin(ny, nx) && board[ny][nx] == 1 && !visited[ny][nx])
			{
				visited[ny][nx] = true;
				q.push({ ny, nx });
			}
		}
	}
}
int solve()
{
	int ans = 0;
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++)
		{
			if (board[i][j] == 1 && !visited[i][j])
			{
				bfs(i, j);
				ans++;
			}
		}
	}
	return ans;
}
int main()
{
	ios::sync_with_stdio(false);
	cin.tie(0);

	int t;
	cin >> t;
	while (t--)
	{
		init();
		cin >> m >> n >> k;
		for (int i = 0; i < k; i++)
		{
			int y, x;
			cin >> x >> y;
			board[y][x] = 1;
		}
		cout << solve() << '\n';
	}
}