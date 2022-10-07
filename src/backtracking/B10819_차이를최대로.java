package backtracking;

import java.util.Arrays;
import java.util.Scanner;

public class B10819_차이를최대로 {
	static int[] arr;
	static int N;
	static int[] output;
	static boolean[] visited;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		arr = new int[N];
		visited = new boolean[N];
		output = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		
		dfs(0);
		System.out.println(max);
	}
	static void dfs(int idx) {
		if(idx == N) {
			//System.out.println(Arrays.toString(output));
			int sum = 0;
			for(int i=0; i<N-1; i++) {
				sum += Math.abs(output[i] - output[i+1]);
			}
			max = Math.max(sum, max);
			return;
		}
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				output[i] = arr[idx];
				dfs(idx+1);
				visited[i] = false;
			}
		}
	}
}
