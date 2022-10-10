package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B4991_로봇청소기_시간초과 {
	static int N,M;
	static char[][] map;
	static boolean[][] v;
	static Point Cleaner;
	static ArrayList<Point> list;
	static boolean[] visit;
	static boolean isClean;
	static int min;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			if(M == 0 && N == 0) {
				break;
			}
			map = new char[N][M];
			v = new boolean[N][M];
			
			list = new ArrayList<>();
			
			isClean = true;
			min = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				char[] arr = br.readLine().toCharArray();
				for(int j=0; j<M; j++) {
					map[i][j] = arr[j];
					if(map[i][j] == 'o') {
						Cleaner = new Point(i,j);
					}
					if(map[i][j] == '*') {
						list.add(new Point(i,j));
					}
				}
			}
			visit = new boolean[list.size()];
			dfs(0, 0);
			if(!isClean) {
				System.out.println(-1);
			}else {
				System.out.println(min);
			}
			
		}
	}
	static void dfs(int cnt, int sum) {
		if(min <= sum) {	// 더 길어지면 해볼 필요가 없지
			return;
		}
		if(cnt == list.size()) {
			min = Math.min(min, sum);
			isClean = true;
			return;
		}
		
		for(int i=0; i<list.size(); i++) {
			if(!isClean) {
				return;
			}
			if(!visit[i]) {		//먼지 체크
				int result = bfs(i);
				Point now = Cleaner;
				Cleaner = list.get(i);
				//System.out.println(result);
				if(result == -1) {		// 못가면 끝
					isClean = false;
					return;
				}else {
					map[Cleaner.i][Cleaner.j] = '.';
					visit[i] = true;
					dfs(cnt+1, sum+result);
					visit[i] = false;
					map[Cleaner.i][Cleaner.j] = '*';
				}
				Cleaner = now;
			}
		}

	}
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	
	static int bfs(int idx) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		q.add(Cleaner);	// 진송청소기 위치로 부터
		visited[Cleaner.i][Cleaner.j] = true;
		
		//먼지 위치
		Point dirty = list.get(idx);
		
		int dist = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s=0; s<size; s++) {
				Point now = q.poll();
				if(map[now.i][now.j] == '*') {
					return dist;
				}
				for(int d=0; d<4; d++) {
					int nexti = now.i + di[d];
					int nextj = now.j + dj[d];
					
					if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= M) {
						continue;
					}
					if(visited[nexti][nextj]) {
						continue;
					}
					if(map[nexti][nextj] != 'x') {	// 벽은 못가고..
						q.add(new Point(nexti, nextj));
						visited[nexti][nextj] = true;
					}
				}
			}
			dist++;
			
		}
		// 못가면?
		return -1;
	}
	static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
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
