package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class S보급로 {
	static int N;
	static int[][] arr;
	static int[][] D;
	
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		

		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			
			for(int i=0; i<N; i++) {
				char[] cArr = br.readLine().toCharArray();
				for(int j=0; j<N; j++) {
					arr[i][j] = cArr[j] - '0';
				}
			}// input End
			
			//DP에 필요한 자료구조
			D = new int[N][N];
			for(int i=0; i<N; i++) {
				Arrays.fill(D[i], Integer.MAX_VALUE);
			}
			D[0][0] = 0;
//			
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					for(int d=0; d<4; d++) {
//						int nexti = i + di[d];
//						int nextj = j + dj[d];
//						
//						if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= N) {
//							continue;
//						}
//						if(D[nexti][nextj] > D[i][j] + arr[nexti][nextj]) {
//							D[nexti][nextj] = D[i][j] + arr[nexti][nextj];
//						}
//					}
//				}
//			}
//			dfs(0,0);
			bfs();
			System.out.printf("#%d %d\n", tc, D[N-1][N-1]);
		}// end tc
		
	}
	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0,0));
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			for(int d=0; d<4; d++) {
				int nexti = now.i + di[d];
				int nextj = now.j + dj[d];
				
				if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= N) {
					continue;
				}
				if(D[nexti][nextj] > D[now.i][now.j] + arr[nexti][nextj]) {
					D[nexti][nextj] = D[now.i][now.j] + arr[nexti][nextj];
					q.add(new Point(nexti, nextj));
				}
			}
		}
		
	}
	static void dfs(int i, int j) {
		if(i == N-1 && j == N-1) {
			return;
		}
		for(int d=0; d<4; d++) {
			int nexti = i + di[d];
			int nextj = j + dj[d];
			
			if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= N) {
				continue;
			}
			if(D[nexti][nextj] > D[i][j] + arr[nexti][nextj]) {
				D[nexti][nextj] = D[i][j] + arr[nexti][nextj];
				dfs(nexti, nextj);
			}
		}
	}
	static class Point{
		int i;
		int j;
		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
	}
}
