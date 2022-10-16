package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B12865_베낭채우기 {
	static int N, K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[][] ns = new int[N+1][2];	// [0] 무게 [1] value
		for(int n=1; n<=N; n++){
			st = new StringTokenizer(br.readLine());
			ns[n][0] = Integer.parseInt(st.nextToken());
			ns[n][1] = Integer.parseInt(st.nextToken());
		}	// end input
		
		int[][] D = new int[N+1][K+1];	//K무게 만큼	
		
	
		for(int n=1; n<=N; n++) {
			int w = ns[n][0];
			int v = ns[n][1];
			
			// k = 가방 무게
			for(int k=0; k<=K; k++) {
				if( k < w) {	// 물건이 더 크면 못담
					D[n][k] = D[n-1][k];		//전의 최적 해
				}else {
					D[n][k] = Math.max(D[n-1][k], D[n-1][k - w] + v);
				}
			}
			//System.out.println(Arrays.toString(D[n]));
		}
		System.out.println(D[N][K]);
	}
}
