package BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17142_연구소3 {
	static int N, M;
	static int[][] map;
	static ArrayList<Point> virus;
	static Point[] output;
	static int roomCnt;
	static int min;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		map = new int[N][N];
		virus = new ArrayList<>();
		output = new Point[M];
		roomCnt = 0;
		min = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virus.add(new Point(i, j));
				}else if(map[i][j] == 0) {
					roomCnt++;
				}
				
			}
		}// end Input
		comb(0,0);
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
	}
	static void comb(int idx, int cnt) {
		if(cnt == M) {
			for(int i=0; i<M; i++) {
				System.out.println(output[i]);
			}
			int room = roomCnt;
			int re = bfs(output, room);
			System.out.println(re);
			if(re != -1) {
				min = Math.min(min, re);
			}
			return;
		}
		if(idx == virus.size()) {
			return;
		}
		output[cnt] = virus.get(idx);
		comb(idx+1, cnt+1);
		comb(idx+1, cnt);
		
	}
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static int bfs(Point[] viruss, int room) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		for(Point p : viruss) {
			visited[p.i][p.j] = true;
			q.add(p);
		}
		int time = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s=0; s<size; s++) {
				Point now = q.poll();
				
				for(int d=0; d<4; d++) {
					int nexti = now.i + di[d];
					int nextj = now.j + dj[d];
					
					if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= N) {
						continue;
					}
					if(visited[nexti][nextj]) {
						continue;
					}
					if(map[nexti][nextj] == 2) {
						visited[nexti][nextj] = true;
						q.add(new Point(nexti, nextj));
					}
					if(map[nexti][nextj] == 0) {
						visited[nexti][nextj] = true;
						q.add(new Point(nexti, nextj));
						room--;
					}
				}
			}
			time++;
		}
		if(room == 0) {
			return time;
		}else {
			return -1;
		}
	}
	static class Point {
		int i;
		int j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + "]";
		}
		
		
	}
}
