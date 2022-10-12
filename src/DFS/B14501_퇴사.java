package DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14501_퇴사 {
	static int N;
	static Day[] arr;
	static int max;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new Day[N+1];
		max = Integer.MIN_VALUE;
		
		StringTokenizer st;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			
			arr[i] = new Day(day, price);
		}
		dfs(1, 0);
		
		System.out.println(max);
	}
	static void dfs(int idx, int sum) {
		if(idx == N+1) {
			max = Math.max(sum, max);
			return;
		}
		
		//해보고 가고
		if(idx + arr[idx].day <= N + 1) {
			dfs(idx + arr[idx].day, sum + arr[idx].price);
		}
		//안하고 가고
		dfs(idx+1, sum);
		
	}
	static class Day {
		int day;
		int price;
		
		public Day(int day, int price) {
			this.day = day;
			this.price = price;
		}
		
	}
}
