package DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B18353_병사배치하기 {
	static int N;
	static int[] arr;
	static int minCnt = Integer.MIN_VALUE;
	static int D[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N+1];
		D = new int[2][N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
//		dfs(0, Integer.MAX_VALUE ,0);
//		System.out.println( N - minCnt);
		arr[0] = Integer.MAX_VALUE;
	
	}
}
