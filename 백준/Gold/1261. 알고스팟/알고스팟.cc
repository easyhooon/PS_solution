#include <bits/stdc++.h>

using namespace std;
int d[111][111];
int board[111][111];

int n, m;

int dx[] = { 0,0,1,-1 };
int dy[] = { 1,-1,0,0 };

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> m >> n;

	for (int i = 0; i < n; i++) {
        string temp;
        cin >> temp;

		for (int j = 0; j < m; j++) {

            board[i][j] = temp[j] - '0';
			d[i][j] = -1; 
            // 지나가지 않았다 라는 것을 명시
            //방문했을 때 거리를 저장하는 dist배열과 (0이상)
            //방문했는지 여부를 판별하는 check 배열 (방문안하면 -1) 을 합칠 수 있다.
		}
	}
	queue<pair<int, int>> q;
	queue<pair<int, int>> next_queue;
	q.push(make_pair(0, 0));

	d[0][0] = 0; // 실제 초기화는 여기서 해줌 

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if (0 <= nx && nx < n && 0 <= ny && ny < m) {
				if (d[nx][ny] == -1) {
					if (board[nx][ny] == 0) {
						d[nx][ny] = d[x][y];
						q.push(make_pair(nx, ny));
					}
					else {
						d[nx][ny] = d[x][y] + 1;
						next_queue.push(make_pair(nx, ny));
					}
				}
			}
		}

        //0일때 큐를 1일때 큐로 1일때 큐를 2일때 큐로.... 교체해줌 
		if (q.empty()) { 
			q = next_queue;
			next_queue = queue<pair<int, int>>();
		}
	}

    cout << d[n-1][m-1] << '\n';

	return 0;
}