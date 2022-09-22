package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1504_특정한최단경로 {
	static int N, E;
	static int u, v;
	static boolean check1;
	static boolean check2;
	static int min = Integer.MAX_VALUE;
	static LinkedList<Point>[] adjList; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adjList = new LinkedList[E+1];
		for(int e=1; e<=E; e++) {
			adjList[e] = new LinkedList<Point>();
		}
		for(int n=0; n< N; n++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adjList[a].add(new Point(b,c));
			adjList[b].add(new Point(a,c));
		}
		st = new StringTokenizer(br.readLine());
		u = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		
		dfs(1, 0, false, false);
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}

	}
	static void bfs(Point start) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(start);
		int[] arr = new int[N+1];
		Arrays.fill(arr, Integer.MAX_VALUE);
		
		while(!pq.isEmpty()) {
			Point now = pq.poll();
			for(Point p : adjList[now.to]) {
				if(arr[p.to] > p.weight + now.weight) {
					arr[p.to] = p.weight + now.weight;
					if(p.to == u) {
						check1 = true;
					}
					if(p.to == v) {
						check2 = true;
					}
				}
				
				pq.add(p);
			}
		}
	}
	static void dfs(int num, int sum, boolean check1, boolean check2) {
		if(min < sum) {	//전에 이미 최단 경로가 있으면 return;
			return;
		}
		if(check1 && check2) {		// 두 정점 모두 방문	
			if(num == N) {
				if(min > sum) {
					min = sum;
					return;
				}
			}
		}
		for(Point now : adjList[num]) {
			if(now.to == u) {
				dfs(now.to, sum + now.weight, true, check2);
				
			}
			if(now.to == v) {
				dfs(now.to, sum + now.weight, check1, true);
			}
			dfs(now.to, sum + now.weight, check1, check2);
		}
	}
	static class Point{
		int to;
		int weight;
		
		public Point(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		
		
	}
}
