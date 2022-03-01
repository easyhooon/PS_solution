#include <bits/stdc++.h>

//멀티 쓰레드 느낌

using namespace std;

int n, k, cnt, s, x, y;
bool check[222][222];
int board[222][222];
int dx[4] = {0, 1, 0, -1};
int dy[4] = {-1, 0, 1, 0};
int vsize;

queue<pair<int, int>> q;
queue<pair<int, int>> temp_q;

void bfs(int sec)
{
    while (sec != s)
    {
        while (!q.empty())
        {
            auto &top = q.front();
            int cx = top.second;
            int cy = top.first;
            //cout << cy << ' ' << cx << '\n';

            q.pop();

            check[cy][cx] = true;

            for (int k = 0; k < 4; k++)
            {
                int nx = cx + dx[k];
                int ny = cy + dy[k];

                if (0 < nx && nx <= n && 0 < ny && ny <= n)
                {
                    if (!check[ny][nx] && board[ny][nx] == 0)
                    {
                        check[ny][nx] = true;
                        board[ny][nx] = board[cy][cx];
                        temp_q.push({ny, nx});
                    }
                }
            }
        }
        sec++;

        q = temp_q;
        while (!temp_q.empty())
        {
            temp_q.pop();
        }
    }
    cout << board[y][x] << '\n';
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> k;

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            cin >> board[i][j];
        }
    }

    for (int l = 1; l <= k; l++)
    {
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (board[i][j] == l)
                    q.push({i, j});
            }
        }
    }

    cin >> s >> y >> x;

    bfs(0);

    return 0;
}
