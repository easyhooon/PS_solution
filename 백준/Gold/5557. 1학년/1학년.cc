#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

ll dp[111][22];
int n, arr[111];

//dp[i][j]: 앞에서 i번째 수 까지 봤을 때, 합이 j인 경우의 수

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n;

    for (int i = 1; i <= n; i++)
    {
        cin >> arr[i];
    }


    //기저조건 
    dp[1][arr[1]] = 1;

    // cout << arr[1] << '\n';

    for (int i = 2; i <= n; i++)
    {
        for (int j = 0; j <= 20; j++)
        {
            if (j - arr[i] >= 0 && j + arr[i] <= 20)
            {
                dp[i][j] = dp[i - 1][j - arr[i]] + dp[i - 1][j + arr[i]];
            }
            else if (j - arr[i] < 0)
            {
                dp[i][j] = dp[i-1][j+arr[i]];
            }

            else //j+arr[i] > 20 
            {
                dp[i][j] = dp[i-1][j-arr[i]];
            }
        }
    }

    // for (int i = 1; i <= n; i++)
    // {
    //     for (int j = 1; j <= 20; j++)
    //     {
    //        cout << dp[i][j] <<' '; 
    //     }
    //     cout <<'\n';
    // }

    

    cout << dp[n-1][arr[n]] << '\n';
}