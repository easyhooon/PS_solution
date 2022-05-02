#include <bits/stdc++.h>

const int MAX = 1000;

using namespace std;

int n, m;
int board[MAX + 1][MAX + 1];
bool visited[MAX + 1][MAX + 1][2];

int dx[] = {0, 0, 1, -1};
int dy[] = {1, -1, 0, 0};

int bfs()
{
    queue<pair<pair<int, int>, pair<int, int>>> q;
    q.push(make_pair(make_pair(0, 0), make_pair(1, 1)));
    visited[0][0][1] = true;

    while (q.empty() == 0)
    {
        int x = q.front().first.first;
        int y = q.front().first.second;
        int k = q.front().second.first;
        int cnt = q.front().second.second;
        q.pop();

        if (y == n - 1 && x == m - 1)
        {
            return cnt;
        }
        for (int i = 0; i < 4; i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && ny < n && nx < m)
            {
                if (board[ny][nx] == 1 && k == 1)
                {
                    visited[ny][nx][k - 1] = true;
                    q.push(make_pair(make_pair(nx, ny), make_pair(k - 1, cnt + 1)));
                }
                else if (board[ny][nx] == 0 && visited[ny][nx][k] == false)
                {
                    visited[ny][nx][k] = true;
                    q.push(make_pair(make_pair(nx, ny), make_pair(k, cnt + 1)));
                }
            }
        }
    }
    return -1;
}


int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> m;
    for (int i = 0; i < n; i++)
    {
        string inp;
        cin >> inp;
        for (int j = 0; j < m; j++)
        {
            int tmp = inp[j] - '0';
            board[i][j] = tmp;
        }
    }

    int ans = bfs();

    cout << ans << '\n';

    return 0;
}
