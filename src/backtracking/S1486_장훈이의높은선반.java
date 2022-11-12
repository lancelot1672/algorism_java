package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1486_장훈이의높은선반 {
	static int N, B;
	static int[] clark;
	static int min;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			min = Integer.MAX_VALUE;
			
			clark = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int n=0; n<N; n++) {
				clark[n] = Integer.parseInt(st.nextToken());
			}
			dfs(0,0);
			System.out.println("#" + tc+ " " + min);
		}
	}
	static void dfs(int idx, int sum) {
		if(idx == N) {
			// 탑의 높이와 B의 차이가 가장 작은 것
			// 탑의 높이가 B 이상이여야 함.
			if(sum >= B) {
				min = Math.min(min, sum - B);
			}
			return;
		}
		
		//점원을 뽑고 가고 안뽑고 가고
		dfs(idx+1, sum + clark[idx]);
		dfs(idx+1, sum);
	}
}
