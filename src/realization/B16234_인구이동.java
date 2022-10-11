package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16234_인구이동 {
	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<United> uniteds;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}// end input
		int day = 0;
		while(true) {
			//print();
			visited = new boolean[N][N];
			uniteds = new ArrayList<>();
			
			//연합 탐색
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j]) continue;
					United united = bfs(i,j);
					if(united.cnt != 1)			// 두 국가 이상이 연합.
						uniteds.add(united);
				}
			}
			if(uniteds.size() == 0) {	// 연합이 없으면 끝.
				break;
			}
			//이동
			for(United u : uniteds) {
				//연합 인구수 / 연합을 이루고 있는 칸의 개수
				int population = u.population / u.cnt;	
				for(Point p : u.list) {
					map[p.i][p.j] = population;
				}
			}
			day++;
		}// end while
		System.out.println(day);
	}
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	static United bfs(int starti, int startj) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(starti, startj));
		visited[starti][startj] = true;
		int cnt = 1;
		int population = map[starti][startj];
		United united = new United();	//연합.
		united.list.add(new Point(starti, startj));
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			for(int d=0; d<4; d++) {
				int nexti = now.i + di[d];
				int nextj = now.j + dj[d];
				
				if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= N) continue;
				if(visited[nexti][nextj]) continue;
				
				//인구 차 비교
				int nowP = map[now.i][now.j];
				int nextP = map[nexti][nextj];
				
				int dist = Math.abs(nowP - nextP);
				// 두 나라의 인구 차이가 L명 이상, R명 이하
				if(dist >= L && dist <= R) {
					cnt++;	// 연합 증가
					population += map[nexti][nextj];	// 인구수 증가
					united.list.add(new Point(nexti, nextj)); 	//연합 좌표
					
					q.add(new Point(nexti, nextj));
					visited[nexti][nextj] = true;
				}
			}
		}
		united.cnt = cnt;
		united.population = population;
		return united;
	}
	static class Point{
		int i;
		int j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
	}
	static class United{
		int cnt;
		int population;
		ArrayList<Point> list = new ArrayList<>();
		
		public United() {
		}
		
	}
	static void print() {
		System.out.println("----------------------------");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
