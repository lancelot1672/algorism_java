package adj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1753_최단경로_PQ {
	static int V, E;
	static int start;
	static ArrayList<Point>[] adjList;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		//주어진 시작점
		start = Integer.parseInt(br.readLine());
		
		//인접리스트 초기화
		adjList = new ArrayList[V+1];
		for(int v=1; v<=V; v++) {
			adjList[v] = new ArrayList<>();
		}
		
		//간선 입력받기
		for(int e=0; e<E; e++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			//단방향
			adjList[from].add(new Point(to, w));
		}
		dijkstra();
	}
	static void dijkstra() {
		PriorityQueue<Point> pq = new PriorityQueue<>((p1,p2) -> p1.w - p2.w);
		boolean[] visited = new boolean[V+1];
		int[] D = new int[V+1];
		
		Arrays.fill(D, Integer.MAX_VALUE);
		
		//처음 노드
		D[start] = 0;
		pq.add(new Point(start, 0));
		
		
		while(!pq.isEmpty()) {
			Point now = pq.poll();
			
			visited[now.num] = true;
			
			for(Point next : adjList[now.num]) {
				if(!visited[next.num] && D[next.num] > D[now.num] + next.w) {
					D[next.num] = D[now.num] + next.w;
					pq.add(new Point(next.num, D[next.num]));
					
				}
			}
		}
		for(int i=1; i<=V; i++) {
			if(D[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			}else {
				System.out.println(D[i]);
			}
		}
	}
	static class Point{
		int num;
		int w;
		
		public Point(int num, int w) {
			this.num = num;
			this.w = w;
		}
		
	}
}
