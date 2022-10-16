package adj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1916_최소비용구하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 도시의 갯수 N
		
		ArrayDeque<Node>[] adjList = new ArrayDeque[N+1];
		for(int n=1; n<=N; n++) {
			adjList[n] = new ArrayDeque<>();
		}
		
		int M = Integer.parseInt(br.readLine());
		
		for(int m=0; m<M; m++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new Node(to, weight));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int[] D = new int[N+1];
		Arrays.fill(D, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.weight - n2.weight);
		boolean[] visited = new boolean[N+1];
		
		//의미 없는 첫 노드 시작
		pq.add(new Node(start,0));
		D[start] = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			//방문 체크
			visited[now.to] = true;
			if(visited[end]) {
				break;
			}
			for(Node next : adjList[now.to]) {
				if(!visited[next.to] && D[next.to] > D[now.to] + next.weight) {
					 D[next.to] = D[now.to] + next.weight;	//갱신
					 pq.add(new Node(next.to, D[next.to]));
				}
			}
		}
		System.out.println(D[end]);
	}
	static class Node{
		int to;
		int weight;
		
		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		
	}
}
