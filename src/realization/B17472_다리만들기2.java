package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17472_다리만들기2 {
	static int N, M;
	static int[][] map;
	static int islandCnt;
	static ArrayList<Point>[] list;
	static int min = Integer.MAX_VALUE;
	
	static int[][] bridge;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		islandCnt = 2;
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) {
					bfs(i, j, islandCnt++);
				}
			}
		}
		list = new ArrayList[islandCnt];
		for(int i=2; i<islandCnt; i++) {
			list[i] = new ArrayList<>();
		}
		
		bridge = new int[islandCnt][islandCnt];
		for(int i=2; i<islandCnt; i++) {
			Arrays.fill(bridge[i], Integer.MAX_VALUE);
		}
		// 땅 리스트에 집어넣기
		for(int k=2; k<islandCnt; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == k) {
						list[k].add(new Point(i,j));
					}
				}
			}
		}
//		System.out.println(islandCnt);
//		print();
		dfs(2);
//		

		
//		System.out.println();
		
		boolean flag = false;
		for(int i=2; i<islandCnt; i++) {
			if(func(i)) {
				flag = true;
			}
		}
		
//		for(int i=2; i<islandCnt; i++) {
//			for(int j=2; j<islandCnt; j++) {
//				System.out.print(bridge[i][j] + " \t");
//			}
//			System.out.println();
//		}
		if(flag) {
			System.out.println(min);
		}else {
			System.out.println(-1);
		}
	}
	static boolean func(int idx) {
		PriorityQueue<Bridge> pq = new PriorityQueue<>((p1, p2) -> p1.length - p2.length);
		boolean[] visited = new boolean[islandCnt];
		pq.add(new Bridge(idx, 0));
		
		int cnt = 0;
		int sum = 0;
		while(!pq.isEmpty()) {
			Bridge now = pq.poll();
			if(visited[now.to]) {
				continue;
			}
			sum += now.length;
			visited[now.to] = true;
			cnt++;
			
			for(int i=2; i<islandCnt; i++) {
				if(bridge[now.to][i] != Integer.MAX_VALUE) {
					pq.add(new Bridge(i, bridge[now.to][i]));
					
				}
			}
		}
		if(cnt == islandCnt-2) {
			// 갱신
			min = Math.min(min, sum);
			return true;
		}
		return false;
	}
	static void dfs(int idx) {
		if(idx == islandCnt) {
			return;
		}
		for(Point now : list[idx]) {
			for(int d=0; d<4; d++) {
				int cnt = forward(now.i, now.j, idx, d);
				//check != -1 이면 연결하고 다음 섬 가자
				if(cnt != -1) {			
					dfs(idx+1);
				}
				
			}
		}
	}
	static int forward(int i, int j, int start, int d) {
		int nowi = i;
		int nowj = j;
		int cnt = 0;
		int destination = 0;
		
		while(true) {
			int nexti = nowi + di[d];
			int nextj = nowj + dj[d];
			
			if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= M) {
				return -1;
			}
			if(map[nexti][nextj] == start) {
				return -1;
			}
			if(map[nexti][nextj] != 0) {
				destination = map[nexti][nextj];	//땅에 닿음
				break;
			}
			cnt++;
			nowi = nexti;
			nowj = nextj;
		}
		
		if(cnt == 1) {		//한개는 연결 X
			return -1;
		}
		//다리 연결 되었음.
		// start = 출발 섬 이름
		if(bridge[start][destination] > cnt) {
			bridge[start][destination] = cnt;
		}
		if(bridge[destination][start] > cnt) {
			bridge[destination][start] = cnt;
		}
		return cnt;
	}
	private static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	static int di[] = {1, -1, 0, 0};
	static int dj[] = {0, 0, -1, 1};
	static void bfs(int starti, int startj, int island) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(starti, startj));
		map[starti][startj] = island;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			for(int d=0; d<4; d++) {
				int nexti = now.i + di[d];
				int nextj = now.j + dj[d];
				
				if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= M) {
					continue;
				}
				if(map[nexti][nextj] == 1) {
					map[nexti][nextj] = island;
					q.add(new Point(nexti,nextj));
				}
			}
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
	static class Bridge{
		int to;
		int length;
		public Bridge(int to, int length) {
			this.to = to;
			this.length = length;
		}
		
	}
}
