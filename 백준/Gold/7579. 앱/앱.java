import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] memory = new int[N + 1];
        int[] cost = new int[N + 1];
        int[][] dp = new int[N + 1][10001];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= 10000; j++) {
                if (i == 1) {
                    if (j - cost[i] >= 0) dp[i][j] = memory[i];
                } else {
                    if (j - cost[i] >= 0) {
                        dp[i][j] = Math.max(dp[i - 1][j - cost[i]] + memory[i], dp[i - 1][j]);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }


        for (int j = 0; j <= 10000; j++) {
            if (dp[N][j] >= M) {
                System.out.println(j);
                return;
            }
        }
    }
}

