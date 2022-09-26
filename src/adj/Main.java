package adj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static LinkedList<Integer>[] adjList;
	static int N,M;
	static int max = Integer.MIN_VALUE;
	static int[] arr;
	static boolean[] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// LinkedList 배열 초기화
		adjList = new LinkedList[N+1];
		arr = new int[N+1];
		for(int i=1; i<N+1; i++) {
			adjList[i] = new LinkedList<>();
		}
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			adjList[A].add(B);
		}
		for(int i=1; i<=N; i++) {
			bfs(i);
		}
		max = Arrays.stream(arr).max().getAsInt();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<= N; i++) {
			if(arr[i] == max) {
				sb.append(i + " ");
			}
		}
		System.out.println(sb.toString());
	}
	static void bfs(int start) {
		visit = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visit[start] = true;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int n : adjList[now]) {
				if(!visit[n]) {
					q.add(n);
					visit[n] = true;
					arr[n]++;
				}
			}
		}
	}
	static class Point{
		int num;
		int cnt;
		public Point(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
		@Override
		public String toString() {
			return "Point [num=" + num + ", cnt=" + cnt + "]";
		}
	}

	
}