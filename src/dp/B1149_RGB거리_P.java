package dp;

import java.util.Scanner;

public class B1149_RGB거리_P {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[][] price = new int[N][3];
		for(int i=0; i<N; i++) {
			price[i][0] = sc.nextInt();	// i번 집의 Red
			price[i][1] = sc.nextInt();	// i번 집의 Green
			price[i][2] = sc.nextInt();	// i번 집의 Blue
		}
		int[][] sum = new int[N][3];
		sum[0][0] = price[0][0];	// 0번집은 내 앞집이 없으니까. 그냥 자기집 비용으로 셋팅
		sum[0][1] = price[0][1];
		sum[0][2] = price[0][2];
		
		for(int i = 1; i<N; i++) {
			sum[i][0] = Math.min(sum[i-1][1], sum[i-1][2]) + price[i][0];		//우리집이 R인경우 앞집은 G or B
			sum[i][1] = Math.min(sum[i-1][0], sum[i-1][2]) + price[i][1];		//우리집이 G인경우 앞집은 R or B
			sum[i][2] = Math.min(sum[i-1][0], sum[i-1][1]) + price[i][2];		//우리집이 B인경우 앞집은 R or G
		}
		
		int ans = Math.min(sum[N-1][0], sum[N-1][1]);
		ans = Math.min(ans, sum[N-1][2]);
		
		System.out.println(ans);
	}
}
