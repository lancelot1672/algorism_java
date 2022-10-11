package adj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class S_키순서1_DFS {
	static int N,M, adjMatrix[][];
	static int gtCnt, ltCnt;
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
				gtCnt = ltCnt = 0;	// 초기화
				gtDFS(i, new boolean[N+1]);
				ltDFS(i, new boolean[N+1]);
				if(gtCnt+ltCnt == N-1) answer++;
			}
			System.out.println("#" + tc + " " + answer);
		}
	}
	static void gtDFS(int cur, boolean[] visited)	{	// start 학생부터 자신보다 키가 큰 학생따라 탐색
	
		visited[cur] = true;
			
		for(int i=1; i<=N; i++) {	// 자신의 인접행렬 들여다보기
			if(adjMatrix[cur][i] == 1 && !visited[i]) {
				gtCnt++;	// 나보다 큰 학생 카운트
				gtDFS(i, visited);
			}
		}
	}
	static void ltDFS(int cur, boolean[] visited)	{	// start 학생부터 자신보다 키가 작은 학생따라 탐색
		
		visited[cur] = true;
			
		for(int i=1; i<=N; i++) {	// 자신의 인접행렬 들여다보기
			if(adjMatrix[i][cur] == 1 && !visited[i]) {
				ltCnt++;	// 나보다 작은 학생 카운트
				ltDFS(i, visited);
			}
		}
	}
}
