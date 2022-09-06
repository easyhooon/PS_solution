import java.util.*;
import java.io.*;


public class Main {
    static int m, n, h;
    static int[][][] map;
    static int[][][] dist;
    static Queue<int[]> q = new LinkedList<>();
    static int[] dx = { 0, 0, -1, 1, 0, 0 };
    static int[] dy = { -1, 1, 0, 0, 0, 0 };
    static int[] dz = { 0, 0, 0, 0, 1, -1 };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken()); // 상자의 가로 칸의 수
        n = Integer.parseInt(st.nextToken()); // 상자의 세로 칸의 수
        h = Integer.parseInt(st.nextToken()); // 상자의 개수
        map = new int[h][n][m];
        dist = new int[h][n][m];
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < m; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    dist[i][j][k] = -1;
                    if(map[i][j][k] == 1) {
                        q.add(new int[] { j, k, i });
                        // 방문 체크
                        dist[i][j][k] = 0;
                    }
                }
            }
        }
        System.out.println(bfs());
    }
    public static int bfs() {
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int nowX = now[0];
            int nowY = now[1];
            int nowZ = now[2];
            for(int i = 0; i < 6; i++) {
                int nextZ = nowZ + dz[i];
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < m
                        && nextZ >= 0 && nextZ < h) {
                    if(map[nextZ][nextX][nextY] == 0 && dist[nextZ][nextX][nextY] == -1) {
                        q.add(new int[] { nextX, nextY, nextZ });
                        map[nextZ][nextX][nextY] = 1;
                        dist[nextZ][nextX][nextY] = dist[nowZ][nowX][nowY] + 1;
                    }
                }
            }
        }
//        for(int i = 0; i < h; i++) {
//            for(int j = 0; j < n; j++) {
//                for(int k = 0; k < m; k++) {
//                   System.out.print(dist[i][j][k]);
//                }
//                System.out.println();
//            }
//        }


        // 모두 익을 때 까지 최소 시간은
        // 특정 토마토가 익을 때 까지의 최대 시간과 동치이다.
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < m; k++) {
                    // 방문하지 않은 점 존재
                    if(map[i][j][k] == 0) {
                        return -1;
                    } else {
                        max = Math.max(max, dist[i][j][k]);
                    }
                }
            }
        }

        return max;
    }
}