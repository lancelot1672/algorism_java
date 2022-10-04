package BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17141_연구소2 {
	static int N, M;
	static int[][] map;
	static ArrayList<Point> virus;
	static ArrayList<Point> room;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		virus = new ArrayList<>();
		room = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virus.add(new Point(i,j));
				}
				else if(map[i][j] == 0) {
					room.add(new Point(i,j));
				}
			}
		}
		output = new Point[M];	// M개를 뽑아서.
		
		comb(0,0);
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
	}
	static Point[] output;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	static void comb(int idx, int cnt) {
		if(cnt == M) {
			
			bfs(output);
		
			return;
		}
		if(idx == virus.size()) {
			return;
		}
		
		output[cnt] = virus.get(idx);
		comb(idx+1, cnt+1);
		comb(idx+1, cnt);
	}
	static void bfs(Point[] newVirus) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		// 0의 갯수
		int zeroCnt = virus.size() + room.size();
		
		// 기존의 바이러스
//		for(Point p : virus) {
//			q.add(p);
//			visited[p.i][p.j] = true;
//		}
		// 뉴 바이러스
		for(Point p : newVirus) {
			q.add(p);
			visited[p.i][p.j] = true;
			zeroCnt--;			// 빈 공간에 바이러스 투입!
		}
		int cnt = 0;
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
					if(map[nexti][nextj] != 1) {
						q.add(new Point(nexti, nextj));
						visited[nexti][nextj] = true;
						zeroCnt--;
					}
				}
			}// end S
			cnt++;
		}
		
		//끝
		if(zeroCnt == 0) {
			min = Math.min(min, cnt-1);
		}
	}
	static boolean check(int[][] map) {
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	static class Point{
		int i;
		int j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
