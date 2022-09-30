package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1600_말되원 {
	static int K;
	static int W, H;
	static int[][] map;
	static int[][][] D;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];
		D = new int[K+1][H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end Input

		for (int a = 0; a <= K; a++) {
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					D[a][i][j] = Integer.MAX_VALUE;
				}
			}
		}
		for(int i=0; i<=K; i++) {
			D[i][0][0] = 0;
		}

		dfs(0, 0, K);
		int ans = Integer.MAX_VALUE;
		for(int i=0; i<K; i++) {
			//System.out.println(D[i][H-1][W-1]);
			ans = Math.min(ans, D[i][H-1][W-1]);
		}
		if(ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}
	}


	static int[] di1 = { -2, -1, -2, -1, +2, +1, +1, +2 };
	static int[] dj1 = { -1, -2, +1, +2, +1, +2, -2, -1 };

	static int[] di2 = { 1, -1, 0, 0 };
	static int[] dj2 = { 0, 0, 1, -1 };

	static void dfs(int i, int j, int k) {
		if (i == H - 1 && j == W - 1) {
			return;
		}
		if (k > 0) {
			// 1개써보면서 가자
			for (int d = 0; d < 8; d++) {
				int nexti = i + di1[d];
				int nextj = j + dj1[d];

				if (nexti < 0 || nexti >= H || nextj < 0 || nextj >= W) {
					continue;
				}
				if (map[nexti][nextj] == 1) {
					continue;
				}
				
				if (D[k-1][nexti][nextj] > D[k][i][j] + 1) {
					D[k-1][nexti][nextj] = D[k][i][j] + 1;
					dfs(nexti, nextj, k - 1);
				}
			}
		}
		// 안쓰면서 가자
		for (int d = 0; d < 4; d++) {
			int nexti = i + di2[d];
			int nextj = j + dj2[d];

			if (nexti < 0 || nexti >= H || nextj < 0 || nextj >= W) {
				continue;
			}
			if (map[nexti][nextj] == 1) {
				continue;
			}
			if (D[k][nexti][nextj] > D[k][i][j] + 1) {
				D[k][nexti][nextj] = D[k][i][j] + 1;
				dfs(nexti, nextj, k);
			}
		} // end for
		
	}
}
