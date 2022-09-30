package dfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B1463_1로만들기_P {
	static int X, ans;
	static int[] visit;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		X = sc.nextInt();
		visit = new int[X+1];
		Arrays.fill(visit, Integer.MAX_VALUE);
		
		dfs(X, 0);
		
	}
	static void dp() {
		int[] memo = new int[X+1];
		
		//현재 i보다 하나작은 숫자로부터 +1 연산 한번 수행하면 지금숫자 만들어짐
		for(int i=2; i<=X; i++) {
			System.out.println(i + ":" + Arrays.toString(memo));
			memo[i] = memo[i-1] + 1;	// 현재 i보다 하나 작은 숫자로 부터 +1 연산 수행하면 지금 숫자 만들어짐
			if(i % 3 == 0) {
				memo[i] = Math.min(memo[i], memo[i/3] + 1);
			}
			if(i % 2 == 0) {
				memo[i] = Math.min(memo[i], memo[i/2] + 1);
			}
		}
		ans = memo[X];
	}
	static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visit = new boolean[X+1];
		
		queue.add(X);
		visit[X] = true;
		
		int cnt = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int s=0; s<size; s++) {
				int now = queue.poll();
				if(now == 1) {
					ans = cnt;
					return;
				}
				if(visit[now]) {
					continue;
				}
				
				if(now % 3 == 0){
					queue.add(now);
					visit[now/3] = true;
				}
				if(now % 2 == 0){
					queue.add(now);
					visit[now/2] = true;
				}
				queue.add(now-1);
				visit[now-1] = true;
			}
			cnt++;
		}
	}
	static void dfs(int num, int cnt) {
		// 이전에 1 만든적 있는데 그때보다 이미 연산을 더 많이 함? 그만해
		if(cnt >= ans) return;
		
		if(num == 1) {
			ans = Math.min(ans, cnt);
			return;
		}
		// 이전에 연산보다 많이 함? 끝	//DP
		if(visit[num] <= cnt) return;
		visit[num] = cnt;
		
		if(num % 3 == 0) {
			dfs(num / 3, cnt + 1);
		}
		if(num % 2 == 0) {
			dfs(num / 2, cnt + 1);
		}
		dfs(num-1, cnt+1);
	}
}
