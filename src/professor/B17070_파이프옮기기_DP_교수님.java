package professor;

import java.util.Scanner;

//파이프 옮기기1 (dfs)
public class B17070_파이프옮기기_DP_교수님 {
	static int N, ans;
	static int[][] map;
	static int[][][] memo;
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
		memo = new int[N][N][3]; // 0 : right, 1: down, 2:cross
		memo[0][1][0] = 1;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 0) {	// 현재 칸이 이동가능한 칸이면
					if(j-1 >= 0) {	//내 왼쪽에서 나에게 오는 경우 (이동방향 오른쪽 : 0)
						memo[i][j][0] += memo[i][j-1][0];	// 내 왼쪽칸에 오른쪽으로 도착한 경우
						memo[i][j][0] += memo[i][j-1][2];	// 내 왼쪽칸에 대각선으로 도착한 경우
					}
					if(i-1 >= 0) {		// 내 윗칸이 있어서 나에게 아래로 온다고 하면
						memo[i][j][1] += memo[i-1][j][1];
						memo[i][j][1] += memo[i-1][j][2];
					}
					if(i-1 >= 0 && j-1 >= 0 && map[i-1][j] == 0 && map[i][j-1] == 0) {
						memo[i][j][2] += memo[i-1][j-1][0];
						memo[i][j][2] += memo[i-1][j-1][1];
						memo[i][j][2] += memo[i-1][j-1][2];
					}
				}
			}
		}
		System.out.println(memo[N-1][N-1][0] + memo[N-1][N-1][1] + memo[N-1][N-1][2]);
	}

}