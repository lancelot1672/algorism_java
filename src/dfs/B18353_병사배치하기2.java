package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B18353_병사배치하기2 {
	static int N;
	static int[] arr;
	static int minCnt = Integer.MIN_VALUE;
	static int D[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		D = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		//Arrays.fill(D, Integer.MIN_VALUE);
		

		D[0] = 1;
		
		for(int i=0; i<N; i++) {
			
		}
		//System.out.println(N - D[N-1]);
	}
}
