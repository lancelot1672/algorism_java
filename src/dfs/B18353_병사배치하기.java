package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B18353_병사배치하기 {
	static int N;
	static int[] arr;
	static int minCnt = Integer.MIN_VALUE;
	static int D[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N+1];
		D = new int[2][N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<2; i++) {
			Arrays.fill(D[i], Integer.MIN_VALUE);
		}
		D[0][0] = 0;
		D[1][0] = 0;
		dfs(0, Integer.MAX_VALUE, 0);
		//dfs(0, Integer.MAX_VALUE, 1);
//		
//		System.out.println(Arrays.toString(D[0]));
//		System.out.println(Arrays.toString(D[1]));
		
		int max = Math.max(D[0][N], D[1][N]);
		
		System.out.println(N - max);
	}
	static void dfs(int idx, int before, int d) {
		if(idx == N) {
			return;
		}
		//뽑고 왔으면
		if(d == 0) {
			if(before > arr[idx + 1]) {	//전에 픽한것이 다음것 보다 크면
				//뽑고가
				if(D[0][idx + 1] < D[0][idx] + 1) {
					D[0][idx + 1] = D[0][idx] + 1;
					dfs(idx+1, arr[idx + 1], 0);
				}
			}
			//뽑지 말고 가
			if(D[1][idx + 1] < D[0][idx]) {
				D[1][idx + 1] = D[0][idx];
				dfs(idx+1, before, 1);
			}
		}else {// 안뽑고 왔으면
			if(before > arr[idx + 1]) {	//전에 픽한것이 다음것 보다 크면
				//뽑고가
				if(D[0][idx + 1] < D[1][idx] + 1) {
					D[0][idx + 1] = D[1][idx] + 1;
					dfs(idx+1, arr[idx + 1], 0);
				}
			}
			// 뽑지 말고가
			if(D[1][idx + 1] < D[1][idx]) {
				D[1][idx + 1] = D[1][idx];
				dfs(idx+1, before, 1);
			}
		}
		
	}
	
}
