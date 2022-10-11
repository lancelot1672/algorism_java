package adj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class S_키순서1_BFS {
	static int N,M, adjMatrix[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			adjMatrix = new int[N+1][M+1];
			
			StringTokenizer st = null;
			
			for(int m=0; m < M; m++) {
				st = new StringTokenizer(br.readLine()," ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMatrix[a][b] = 1;	// a보다 b가 키가크다.
			}
			
			int answer = 0;
			
			for(int i=1; i<=N; i++) {
				if(gtBFS(1) + ltBFS(i) == N-1) answer++;
			}
			System.out.println("#" + tc + " " + answer);
		}
	}
	static int gtBFS(int start)	{	// start 학생부터 자신보다 키가 큰 학생따라 탐색
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		
		visited[start] = true;
		queue.offer(start);
		int cnt = 0;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int i=1; i<=N; i++) {	// 자신의 인접행렬 들여다보기
				if(adjMatrix[cur][i] == 1 && !visited[i]) {
					cnt++;	// 나보다 큰 학생 카운트
					visited[i] = true;
					queue.offer(i);
				}
			}
		}
		return cnt;
	}
	static int ltBFS(int start)	{	// start 학생부터 자신보다 키가 작은 학생따라 탐색
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		
		visited[start] = true;
		queue.offer(start);
		int cnt = 0;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int i=1; i<=N; i++) {	// 자신의 인접행렬 들여다보기
				if(adjMatrix[i][cur] == 1 && !visited[i]) {
					cnt++;	// 나보다 큰 학생 카운트
					visited[i] = true;
					queue.offer(i);
				}
			}
		}
		return cnt;
	}
}
