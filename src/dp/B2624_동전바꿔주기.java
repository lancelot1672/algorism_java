package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2624_동전바꿔주기 {
	static int T;
	static int K;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());	//금액
		
		K = Integer.parseInt(br.readLine());	//동전갯수
		int[][] coin = new int[K+1][2];
		int[][] D = new int[K+1][T+1];
		for(int k=1; k<=K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			coin[k][0] = Integer.parseInt(st.nextToken());
			coin[k][1] = Integer.parseInt(st.nextToken());
			

		}
		
		for(int k=1; k<=K; k++) {

			int price = coin[k][0];
			int cnt = coin[k][1];
			//코인 갯수만큼 돌리기
			for(int i=0; i<=cnt; i++) {
				for(int t=1; t<=T; t++) {
					if(t >= price * i) {
						D[k][t] = D[k-1][t - price * i] +1;
					}
				}
			}
		}
		for(int k=0; k<=K; k++) {
			System.out.println(Arrays.toString(D[k]));
		}
	}
}
