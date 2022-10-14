package backtracking;

import java.util.Scanner;

public class B9663_NQueen2_2차배열 {
	static int[][] map;
	static int N;
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		ans = 0;
		
		map = new int[N][N];
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
				map[idx][j] = 1;
				dfs(idx+1);
				map[idx][j] = 0;
			}
		}
	}
	static boolean check(int starti, int startj) {
		// ------------
		for(int j=0; j<N; j++) {
			if(map[starti][j] == 1) return false;
		}
		// | 세로 확인
		for(int i=0; i<N; i++) {
			if(map[i][startj] == 1) return false;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1) {
					int dist1 = Math.abs(starti - i);
					int dist2 = Math.abs(startj - j);
					if(dist1 == dist2) return false;
				}
			}
		}
		return true;
	}
}
