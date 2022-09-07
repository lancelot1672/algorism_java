package backtracking;

import java.util.Scanner;

public class B9663_NQueen {
	static int[] queen;
	static int N;
	static int cnt = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		queen = new int[N];
		for(int i=0; i<N; i++) {	// 맨윗줄은 놓고 시작하니깐~
			queen[0] = i;
			dfs(0);
		}
		System.out.println(cnt);
	}
	static void dfs(int idx) {
		if(!isVaild(idx, queen[idx])) {	// 검사해~
			return;
		}
		if(idx == N-1) {
			cnt++;
			return;
		}
		
		for(int i=0; i<N; i++) {
			queen[idx+1] = i;	//먼저 뽑아 놓고 다음거에서 확인해볼래
			dfs(idx+1);
		}
	}
	static boolean isVaild(int idx, int j) {
		for(int i=0; i<idx; i++) {	//전에 뽑았던 것으로 부터 하자
			if(queen[i] == j) {	// 위 아래로 있으면
				return false;
			}
			if(Math.abs(i - idx) == Math.abs(queen[i] - j)) {
				return false;
			}
		}
		
		return true;
	}
}
