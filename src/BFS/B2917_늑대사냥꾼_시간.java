package BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B2917_늑대사냥꾼_시간 {
	static int N,M;
	static int[][] map;
	static Point start;
	static Point end;
	static ArrayList<Point> tree;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		tree = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			Arrays.fill(map[i], Integer.MAX_VALUE);
		}
		
		for(int i=0; i<N; i++) {
			char[] input = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				if(input[j] == '+') {
					map[i][j] = 0;
					tree.add(new Point(i, j, 0));
				}
				else if(input[j] == 'V') {
					start = new Point(i,j, 0);
				}
				else if(input[j] == 'J') {
					end = new Point(i,j, 0);
				}
			}
		}
		bfs();
		//print();
		int result = bfs2();
		System.out.println(result);
	}
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	static int min = Integer.MAX_VALUE;
	static int bfs2() {
		PriorityQueue<Point> pq = new PriorityQueue<>((p1, p2)-> p2.d - p1.d); 
		boolean[][] visit = new boolean[N][M];
		start.d = map[start.i][start.j];
		pq.add(start);
		
		while(!pq.isEmpty()) {
			Point now = pq.poll();
			if(map[now.i][now.j] < min) {
				min = map[now.i][now.j];
			}
			if(now.i == end.i  && now.j == end.j) {
				return min;
			}
			for(int d=0; d<4; d++) {
				int nexti = now.i + di[d];
				int nextj = now.j + dj[d];
				
				if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= M) {
					continue;
				}
				if(map[nexti][nextj] == 0) {
					continue;
				}
				if(visit[nexti][nextj]) {
					continue;
				}

				visit[nexti][nextj] = true;
				pq.add(new Point(nexti, nextj, map[nexti][nextj]));
			}
		}
		return 0;
	}
	static void bfs() {
		// 좌표마다 거리를 갱신
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				for(int t=0; t<tree.size(); t++) {
					Point now = tree.get(t);
					if(map[i][j] == 0) {
						continue;
					}
					map[i][j] = Math.min(map[i][j], Math.abs(now.i - i) + Math.abs(now.j - j));
				}
			}
		}
	}
	static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	static class Point{
		int i;
		int j;
		int d;
		public Point(int i, int j, int d) {
			this.i = i;
			this.j = j;
			this.d = d;
		}

		
	}
}
