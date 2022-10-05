package professor;

import java.util.Scanner;
//파이프 옮기기1 (dfs)
public class B17070_파이프옮기기_교수님 {
 static int N, ans;
 static int[][] map;

 // 0:right, 1:down, 2:corss
 static int[] di = { 0, 1, 1 };
 static int[] dj = { 1, 0, 1 };

 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
     N = sc.nextInt();
     map = new int[N][N];

     for (int i = 0; i < N; i++) {
         for (int j = 0; j < N; j++) {
             map[i][j] = sc.nextInt();
         }
     }

     dfs(0, 1, 0);
     System.out.println(ans);
 }

 static void dfs(int nowi, int nowj, int dir) {
     if (nowi == N - 1 && nowj == N - 1) {
         ans++;
         return;
     }

//     // 가로
//     if (dir != 1 && nowi + 1 < N && map[nowi][nowj + 1] == 0) {
//         dfs(nowi, nowj + 1, 0);
//     }
//
//     // 세로
//     if (dir != 0 && nowi + 1 < N && map[nowi + 1][nowj] == 0) {
//         dfs(nowi + 1, nowj, 1);
//     }
//
//     // 대각선
//     if (nowi + 1 < N && nowj + 1 < N && map[nowi + 1][nowj] == 0 && map[nowi][nowj + 1] == 0) {
//         dfs(nowi + 1, nowj + 1, 2);
//     }

     for (int d = 0; d < 3; d++) {
         int nexti = nowi + di[d];
         int nextj = nowj + dj[d];
         if (nexti < N && nextj < N && map[nexti][nextj] == 0) {
             if (dir == 0 && d == 1)
                 continue;
             if (dir == 1 && d == 0)
                 continue;
             if (d == 2 && (map[nowi][nowj + 1] == 1 || map[nowi + 1][nowj] == 1))
                 continue;

             dfs(nexti, nextj, d);
         }
     }
 }
}