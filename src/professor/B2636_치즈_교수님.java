package professor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B2636_치즈_교수님 {
	static int N, M;
	static int[][] map;
	static int time, last;	// 다 녹기 직전 마지막 치즈 수
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}// input
		
		time = 0;
		while(true) {
			if(count() == 0) {
				break;
			}
			last = count();	// 이번에 다 녹을 수 있으니 일단 치즈 갯수를 세놔.
			bfs();	// 녹아야될 치즈 탐색
			melt();		// 한꺼번에 녹이기
			time++;
		}
	}
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		q.add(new Point(0,0));
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			Point air = q.poll();
			for( int d=0; d<4; d++) {
				int nexti = air.i + di[d];
				int nextj = air.j + dj[d];
				
				if(nexti < 0 || nexti >=N || nextj < 0 || nextj >= M) continue;
				if(map[nexti][nextj] == 0) {
					q.add(new Point(nexti, nextj));
					visited[nexti][nextj] = true;
				}
				if(map[nexti][nextj] == 1) map[nexti][nextj] = 2;
				
			}
		}
	}
	static int count() {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) cnt++;
			}
		}
		return cnt;
	}
	static void melt() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 2) map[i][j] = 0;
			}
		}
	}
	static class Point {
		int i;
		int j;
		
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
		
	}
}
