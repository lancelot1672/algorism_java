package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1600_말되원_BFS {
	static int K;
	static int W, H;
	static int[][] map;
	static int[][][] D;
	static boolean[][][] V;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];
		D = new int[K+1][H][W];
		V = new boolean[K+1][H][W];
		
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


		bfs(new Point(0,0,K, 0));
		
		int ans = Integer.MAX_VALUE;
		for(int i=0; i<=K; i++) {
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
	static void bfs(Point start) {
		Queue<Point> q = new LinkedList<>();
		V[start.k][start.i][start.j] = true;
		q.add(start);
		
		int cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s=0; s<size; s++) {
				Point now = q.poll();
				
				if(now.i == H-1 && now.j == W-1) {
					return;
				}
				if(now.k > 0) {
					for(int d=0; d<8; d++) {
						int nexti = now.i + di1[d];
						int nextj = now.j + dj1[d];
						if(nexti < 0 || nexti >= H || nextj < 0 || nextj >= W) {
							continue;
						}
						if(map[nexti][nextj] == 1) {
							continue;
						}
//						if(V[now.k-1][nexti][nextj]) {
//							continue;
//						}
//						V[now.k][nexti][nextj] = true;
						if(D[now.k-1][nexti][nextj] > D[now.k][now.i][now.j] + 1) {
							D[now.k-1][nexti][nextj] = D[now.k][now.i][now.j] + 1;
							q.add(new Point(nexti,nextj, now.k-1,now.cnt+1));
						}
					}
				}
				for(int d=0; d<4; d++) {
					int nexti = now.i + di2[d];
					int nextj = now.j + dj2[d];
					if(nexti < 0 || nexti >= H || nextj < 0 || nextj >= W) {
						continue;
					}
					if(map[nexti][nextj] == 1) {
						continue;
					}
//					if(V[now.k][nexti][nextj]) {
//						continue;
//					}
//					V[now.k][nexti][nextj] = true;
					if(D[now.k][nexti][nextj] > D[now.k][now.i][now.j] + 1) {
						D[now.k][nexti][nextj] = D[now.k][now.i][now.j] + 1;
						q.add(new Point(nexti,nextj, now.k, now.cnt+1));
					}
				}
			}
			cnt++;
		}
		
	}
	static class Point{
		int i;
		int j;
		int k;
		int cnt;
		public Point(int i, int j, int k, int cnt) {
			this.i = i;
			this.j = j;
			this.k = k;
			this.cnt = cnt;
		}
		
		
	}
}
