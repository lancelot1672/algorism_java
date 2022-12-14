package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S등산로조성 {
	static int N, K;
	static int[][] map;
	static int ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//맵 크기
			K = Integer.parseInt(st.nextToken());	// 깎을 수 있는 깊이
			
			map = new int[N][N];
			ans = Integer.MIN_VALUE;
			
			int max = 0;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[i][j]);
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == max) {	//등산로 시자쿠!
						visited = new boolean[N][N];
						dfs(i, j, 1, false);
					}
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	static boolean[][] visited;
	static void dfs(int i, int j, int len, boolean flag) {
		for(int d=0; d<4; d++) {
			int nexti = i + di[d];
			int nextj = j + dj[d];
			
			if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= N) continue;
			if(visited[nexti][nextj]) continue;
			
			// 굳이 깎을 필요가 없죠?
			if(map[i][j] > map[nexti][nextj]) {	// 그냥 내려가
				visited[i][j] = true;
				dfs(nexti, nextj, len + 1, flag);
				visited[i][j] = false;
			}
			
			if(map[nexti][nextj] >= map[i][j]) {
				if(!flag) {	//안깎았을 때 깎고 가자.
					for(int k=1; k<=K; k++) {
						if(map[nexti][nextj] - k < map[i][j]) {	//근데 다음거를 깎는데 나보다 작아야돼. 아님 못깎어.
							visited[i][j] = true;
							map[nexti][nextj] -= k;
							
							dfs(nexti, nextj, len + 1, true);
							
							visited[i][j] = false;
							map[nexti][nextj] += k;
						}

					}
				}
			}

		}
		ans = Math.max(ans, len);
	}
}
