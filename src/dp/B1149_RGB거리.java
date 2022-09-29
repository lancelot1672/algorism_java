package dp;

import java.util.Arrays;
import java.util.Scanner;

public class B1149_RGB거리 {
	static int N;
	static int[][] arr;
	static int[] D;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		arr = new int[N+1][3];
		D = new int[N+1];
		Arrays.fill(D, Integer.MAX_VALUE);
		D[0] = 0;
		
		for(int i=1; i<=N; i++) {
			for(int j=0; j<3; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		dfs(0, 0);	//R
		dfs(0, 1);	//G
		dfs(0, 2);	//B
		System.out.println(D[N]);
	}
	static void dfs(int n, int color) {
		if(n == N) {
			return;
		}
		System.out.println(Arrays.toString(D));
		if(color == 0) {	//전에 온곳이 빨간색
			if(D[n+1] > D[n] + arr[n+1][1]) {	// 그린
				D[n+1] = D[n] + arr[n+1][1];
				dfs(n+1, 1);
			}
			if(D[n+1] > D[n] + arr[n+1][2]) {	// 파랑
				D[n+1] = D[n] + arr[n+1][2];
				dfs(n+1, 2);
			}
		}else if(color == 1) {	//전에 온곳이 그린
			if(D[n+1] > D[n] + arr[n+1][0]) {	// 빨강
				D[n+1] = D[n] + arr[n+1][0];
				dfs(n+1, 0);
			}
			if(D[n+1] > D[n] + arr[n+1][2]) {	// 파랑
				D[n+1] = D[n] + arr[n+1][2];
				dfs(n+1, 2);
			}
			
		}else {	//전에 온곳이 파랑
			if(D[n+1] > D[n] + arr[n+1][1]) {	// 그린
				D[n+1] = D[n] + arr[n+1][1];
				dfs(n+1, 1);
			}
			if(D[n+1] > D[n] + arr[n+1][0]) {	// 빨강
				D[n+1] = D[n] + arr[n+1][0];
				dfs(n+1, 0);
			}
		}
		
	}
}
