package dfs;

import java.util.Scanner;

public class S1952수영장 {
	static int T, Ans;
	static int[] fee = new int[4];
	static int[] month = new int[12];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			for(int i=0; i<4; i++) {
				fee[i] = sc.nextInt();
			}
			for(int i=0; i<12; i++) {
				month[i] = sc.nextInt();
			}
			Ans = Integer.MAX_VALUE;
			
			//1년 사용권 금액
			Ans = fee[3];
			dfs(0, 0);
			System.out.printf("#%d %d\n",tc,Ans);
		}

	}
	static void dfs(int M, int sum) {
		if(sum >= Ans) {		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			return;
		}
		if(M == 12) {
			Ans = Math.min(Ans, sum);
			return;
		}
		if(month[M] == 0) {		//0이면 그냥 지나가
			dfs(M+1, sum);
		}
		//1달 이용권
		int monthFee = sum + fee[1];
//		if(Ans < monthFee) {
//			return;
//		}
		dfs(M+1, monthFee);
		
		//1일 이용권에 지금까지의 요금
		int dayFee = sum + fee[0] * month[M];
//		if(Ans < dayFee) {
//			return;
//		}
		dfs(M+1, dayFee);
		
		//3달 이용권
		if(M <= 9) {
			int month3Fee = sum += fee[2];
//			if(Ans < month3Fee) {
//				return;
//			}
			dfs(M+3, month3Fee);
		}
	}
}
