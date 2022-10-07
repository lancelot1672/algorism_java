package BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2636_치즈 {
	static int R, C;
	static int[][] map;
	static ArrayList<Point> list;
	static ArrayList<Integer> cntList;
	static int cheese;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		cntList = new ArrayList<>();
		cheese = 0;
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) cheese++;
			}
		}// end Input
		
		int time = 0;
		while(cheese > 0) {
			cntList.add(cheese);
			bfs(0,0);	// 가장자리 탐색
			melt();		// 앙 녹아요~
			cheese -= list.size();	// 녹은 치즈 만큼
			
			time++;
		}
		if(time == 0) {	//치즈가 없을 수도 있어..
			System.out.println(0);
			System.out.println(0);
		}else {
			System.out.println(time);
			System.out.println(cntList.get(time - 1));

		}

	}
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	
	//가장 자리 탐색 0,0 bfs
	static void bfs(int starti, int startj) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		list = new ArrayList<>();	// 가장자리 리스트
		
		//큐 첫번째
		q.add(new Point(starti, startj));
		visited[starti][startj] = true;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			for(int d=0; d<4; d++) {
				int nexti = now.i + di[d];
				int nextj = now.j + dj[d];
				
				if(nexti < 0 || nexti >= R || nextj < 0 || nextj >= C) {
					continue;
				}
				if(visited[nexti][nextj]) {
					continue;
				}
				if(map[nexti][nextj] == 1) {
					visited[nexti][nextj] = true;
					list.add(new Point(nexti, nextj));
				}
				if(map[nexti][nextj] == 0) {
					visited[nexti][nextj] = true;
					q.add(new Point(nexti, nextj));
				}
			}
		}
	}
	
	//녹아..
	static void melt() {
		for(Point p : list) {
			map[p.i][p.j] = 0;
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
