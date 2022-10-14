package backtracking;

import java.util.Arrays;
import java.util.Scanner;

public class B9663_NQueen2_1차배열 {
	static int[] queen;
	static int N;
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		ans = 0;
		
		queen = new int[N];
		dfs(0);
		System.out.println(ans);
	}
	static void dfs(int idx) {
		if(idx == N) {	//끝까지 왔으면 되는거임~
			ans++;
			return;
		}
		
		for(int j=0; j<N; j++) {
			if(check(idx, j)) {
				queen[idx] = j;
				dfs(idx+1);
			}
		}
	}
	static boolean check(int idx, int j) {
		for(int i=0; i<idx; i++) {
			if(queen[i] == j) return false;
		}
		for(int i=0; i<idx; i++) {
			if(Math.abs(i - idx) == Math.abs(queen[i] - j)) return false;
		}
		return true;
	}
}
