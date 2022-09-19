import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int max;

    static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] count = new int[N];
        for (int i = 0; i < N; i++) {
            max = 0;
            st = new StringTokenizer(br.readLine());
            arr = new int[5]; //새로 초기화
            for (int j = 0; j < 5; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            dfs(0, 0, 0);
            count[i] = max;
        }
        int tmp = count[0];
        int maxIndex = 0;
        for (int i = 1; i < N; i++) {
            if (count[i] >= tmp) {
                tmp = count[i];
                maxIndex = i;
            }
        }
        System.out.println(maxIndex + 1);
    }

    public static void dfs(int i, int sum, int depth) {
        if (i < 5) {
            dfs(i + 1, sum + arr[i], depth + 1);
            dfs(i + 1, sum, depth);
        }
        if (depth == 3) {
            max = Math.max(max, sum % 10);
        }
    }
}