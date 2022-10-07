package professor;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

import professor.B1600_말이되고픈원숭이_2차_교수님.Point;

public class B17472_다리만들기_교수님 {
	static int R, C;
	static int[][] map;
	static boolean[][] visit;	// 섬 번호 붙이기 bfs 할때 사용할 visited 배열
	
	static int[] di = {0, 0, 1, -1};
	static int[] dj = {1, -1, 0, 0};
	
	static int[] disjoint;
	static PriorityQueue<Edge> pq;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		
		map = new int[R][C];
		visit = new boolean[R][C];
		
		pq = new PriorityQueue<>();
		makeEdge();
		
		// step 3 간선들 중 가장 짧은 것부터 꺼내서 두 섬을 연결하는 작업을 진행하자.
		for(int i=1; i<= landCnt; i++) {
			disjoint[i] = i;
		}
	}
	static int find(int num) {
		if(disjoint[num] == num) return num;
		return disjoint[num] = find(disjoint[num]);
	}
	static boolean union (int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa == pb) return false;
		disjoint[pa] = pb; 
	}
    static void bfs(int i, int j, int landCnt) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(i, j));
	}
    static void makeEdge() {
    	for(int i=0; i<R; i++) {
    		for(int j=0; j<C; j++) {
    			if(map[i][j] > 0) {
    				for(int d=0; d<4; d++) {
    					int nexti = i;
    					int nextj = j;
    					int dist = 0;
    					while(true) {
    						nexti += di[d];
    						nextj += dj[d];
    						if(nexti >= 0 && nexti < R && nextj >= 0 && nextj < C && map[nexti][nextj] == 0) {
    							dist++;
    							continue;
    						}
    						
    						if(nexti >= 0 && nexti < R && nextj >= 0 && nextj < C && map[i][j] != map[nexti][nextj]) {
    							pq.add(new Edge(map[i][j], map[nexti][nextj], dist));
    						}
    					}
    				}
    			}
    		}
    	}
    }
	static class Edge implements Comparable<Edge>{
		int st, end, dist;

		public Edge(int st, int end, int dist) {
			this.st = st;
			this.end = end;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return this.dist - o.dist;
		}
		
	}
	static class Point{
		int i;
		public Point(int i) {
			super();
			this.i = i;
		}
		int j;
		
	}
}
