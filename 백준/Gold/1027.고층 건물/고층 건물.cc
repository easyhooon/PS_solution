#include <bits/stdc++.h>

using namespace std;
using ll = long long;

vector<ll> v;

bool see(int i, int j)
{
    bool flag = true;
    if (i < j)
    {
        for (int k = i + 1; k < j; k++)
        {
            if ((k - i) * (v[j] - v[i]) <= (v[k] - v[i]) * (j - i))
            {
                flag = false;
            }
        }
    }
    else
    {
        for (int k = j + 1; k < i; k++)
        {
            if ((k - i) * (v[j] - v[i]) >= (v[k] - v[i]) * (j - i))
                flag = false;
        }
    }
    return flag;
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);

    int n;
    cin >> n;

    for (int i = 0; i < n; i++)
    {
        ll ht;
        cin >> ht;
        v.push_back(ht);
    }

    int max_cnt = -1;

    for (int i = 0; i < n; i++)
    {
        int cnt = 0;
        for (int j = 0; j < n; j++)
        {
            if (i == j)
                continue;  
            else
            {
                if(see(i, j))
                    cnt++;
            }
             
        }
        max_cnt = max(max_cnt, cnt);
    }

    cout << max_cnt << '\n';
}