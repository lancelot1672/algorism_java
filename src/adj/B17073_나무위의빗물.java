package adj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17073_나무위의빗물 {
	static int N, W;
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;
	static int total_cnt;
	public static void main(String[] args) throws Exception{
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[N+1];
		visited = new boolean[N+1];
		total_cnt = 0;
		
		for(int i=1; i<=N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to  = Integer.parseInt(st.nextToken());
			
			adjList[from].add(to);
			adjList[to].add(from);
		}
		
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		visited[1] = true;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			int cnt = 0;
			for(int next : adjList[now]) {
				if(visited[next]) continue;
				
				visited[next] = true;
				cnt++;
				q.add(next);
			}
			if(cnt == 0) {
				total_cnt++;
			}
		}
		System.out.println((double)W / (double) total_cnt);
	}
}
