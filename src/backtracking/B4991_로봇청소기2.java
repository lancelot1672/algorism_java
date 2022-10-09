package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B4991_로봇청소기2 {
	static int N,M;
	static char[][] map;
	static boolean[][] v;
	static Point Cleaner;
	static ArrayList<Point> list;
	static boolean[] visit;
	static boolean isClean;
	static int min;
	static int[][] adjMatrix;
	static ArrayList<Node>[] adjList;
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
			
			list = new ArrayList<>();
			min = Integer.MAX_VALUE;
			isClean = true;
			
			for(int i=0; i<N; i++) {
				char[] arr = br.readLine().toCharArray();
				for(int j=0; j<M; j++) {
					map[i][j] = arr[j];
					if(map[i][j] == 'o') {
						Cleaner = new Point(i,j);
					}
				}
			}
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == '*') {
						list.add(new Point(i,j));
					}
				}
			}// end Input
			list.add(0, Cleaner);
			map[Cleaner.i][Cleaner.j] = '*';
			
			adjList = new ArrayList[list.size()];
			for(int i=0; i<list.size(); i++) {
				adjList[i] = new ArrayList<Node>();
			}
			adjMatrix = new int[list.size()][list.size()];
			for(int i=0; i<list.size(); i++) {
				if(isClean)
					bfs(i);
			}
			
			for(int i=0; i<list.size(); i++) {
				for(int j=0; j<list.size(); j++) {
					System.out.print(adjMatrix[i][j] + " ");
				}
				System.out.println();
			}
			
			//MST
			PriorityQueue<Node> pq = new PriorityQueue<>();
			boolean [] visited = new boolean[list.size()];
			
			// 진공청소기부터 시작
			pq.add(new Node(0,0));
			int sum = 0;
			while(!pq.isEmpty()) {
				Node now = pq.poll();
				
				if(visited[now.n]) {
					continue;
				}
				visited[now.n] = true;
				sum +=  now.w;
				for(Node next : adjList[now.n]) {
					if(!visited[next.n]) {
						pq.add(next);
					}
				}
			}
			System.out.println(sum);
		}
	}
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	
	static void bfs(int idx) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		Point start = list.get(idx);
		q.add(start);	// 진송청소기 위치로 부터
		visited[start.i][start.j] = true;
		
		//먼지 위치
		Point dirty = list.get(idx);
		
		int dist = 0;
		int cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s=0; s<size; s++) {
				Point now = q.poll();

				for(int d=0; d<4; d++) {
					int nexti = now.i + di[d];
					int nextj = now.j + dj[d];
					
					if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= M) {
						continue;
					}
					if(visited[nexti][nextj]) {
						continue;
					}
					if(map[nexti][nextj] == '*') {
						// a 부터 b와의 거리
							for(int i=0; i<list.size(); i++) {
								Point p = list.get(i);
								if(p.i == nexti && p.j == nextj) {
									cnt++;
									adjMatrix[idx][i] = dist+1;
									adjMatrix[i][idx] = dist+1;
									
									
									adjList[idx].add(new Node(i, dist+1));
									adjList[i].add(new Node(idx, dist+1));
								}
							}
						
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
		if(cnt != list.size() -1) {
			isClean = false;
		}
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
	static class Node implements Comparable<Node>{
		int n;
		int w;
		public Node(int n, int w) {
			this.n = n;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.w - o.w;
		}
		
		
	}
}
