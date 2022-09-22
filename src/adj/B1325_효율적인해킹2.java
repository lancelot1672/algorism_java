package adj;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class B1325_효율적인해킹2 {
	static LinkedList<Integer>[] adjList;
	static int N,M;
	static int max = 0;
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		// LinkedList 배열 초기화
		adjList = new LinkedList[N+1];
		arr = new int[N+1];
		visited = new boolean[N+1];
		
		Arrays.fill(arr, -1);
		for(int i=1; i<N+1; i++) {
			adjList[i] = new LinkedList<>();
		}
		
		for(int m=0; m<M; m++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			
			adjList[B].add(A);
		}
		for(int i=1; i<N+1; i++) {
			dfs(i);
		}

		for(int i=1; i<N+1; i++) {
				System.out.println(arr[i]);
		}
		
	}
	static boolean[] visited;
	static int dfs(int num) {
		if(arr[num] != -1) {
			return 0;
		}
		if(visited[num]) {
			return 0;
		}
		visited[num ] = true;
		int cnt = 0;
		arr[num] = 0;
		for(int n : adjList[num]) {
			if(!visited[n]) {
				cnt++;
			
			if(arr[n] == -1) {
				arr[n] = dfs(n);
				
				arr[num] += arr[n];
			}else {
				
				arr[num] += arr[n];
			}
			
			}
		}
		arr[num] += cnt;
		max = Math.max(max, arr[num]);
		return arr[num];
	}
}
