package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17069_파이프옮기기2_재귀 {
	static int N;
	static int[][] map;
	static Point[][] d;
	static long[][][] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		d = new Point[N][N];
		arr = new long[3][N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				d[i][j] = new Point();
			}
		}
//		dp(0,1,0);	//가로로 시작
		
//		System.out.println(arr[0][N-1][N-1]);
//		System.out.println(arr[1][N-1][N-1]);
//		System.out.println(arr[2][N-1][N-1]);
//		System.out.println(arr[0][N-1][N-1] + arr[1][N-1][N-1] + arr[2][N-1][N-1]);
		
		d[0][1].w = 1;	// 맨처음 가로
		arr[0][0][1] = 1;
		
		for(int i=0; i<N; i++) {
			for(int j=1; j<N; j++) {
				if(d[i][j].w == 1) {	//가로로 왔으면
					if(j + 1 < N && map[i][j + 1] == 0) {	//가로로 가자
						arr[0][i][j + 1] += arr[0][i][j];
						d[i][j+1].w = 1;
					}
					//대각선으로 가자
					if(i + 1 < N && j + 1 < N && map[i][j + 1] == 0 && map[i + 1][j + 1] == 0 && map[i + 1][j] == 0)
					{
						arr[2][i+1][j+1] += arr[0][i][j];
						d[i+1][j+1].c = 1;
					}
				}
				if(d[i][j].h == 1) {	//세로로 왔으면
					if(i + 1 < N && map[i + 1][j] == 0) {	//세로로 가자
						arr[1][i + 1][j]  += arr[1][i][j];
						d[i+1][j].h = 1;
					}
					//대각선으로 가자
					if(i + 1 < N && j + 1 < N && map[i][j + 1] == 0 && map[i + 1][j + 1] == 0 && map[i + 1][j] == 0)
					{
						arr[2][i+1][j+1]  += arr[1][i][j];
						d[i+1][j+1].c = 1;
					}
				}
				if(d[i][j].c == 1) {	//대각으로 왔으면
					if(j + 1 < N && map[i][j + 1] == 0) {	//가로로 가자
						arr[0][i][j + 1] += arr[2][i][j];
						d[i][j+1].w = 1;
					}
					if(i + 1 < N && map[i + 1][j] == 0) {	//세로로 가자
						arr[1][i + 1][j] += arr[2][i][j];
						d[i+1][j].h = 1;
					}
					//대각선으로 가자
					if(i + 1 < N && j + 1 < N && map[i][j + 1] == 0 && map[i + 1][j + 1] == 0 && map[i + 1][j] == 0)
					{
						arr[2][i+1][j+1] += arr[2][i][j];
						d[i+1][j+1].c = 1;
					}
				}
			}// end j
		}// end i
//		System.out.println(arr[0][N-1][N-1]);
//		System.out.println(arr[1][N-1][N-1]);
//		System.out.println(arr[2][N-1][N-1]);
		long result = arr[0][N-1][N-1] + arr[1][N-1][N-1] + arr[2][N-1][N-1];
		System.out.println(result);
	}
	static void dp(int nowi, int nowj, int d) {
		if(nowi == N-1 && nowj == N-1) {

			return;
		}
		
		if(d == 0) {	// 전에 왔던 곳이 가로
			//가로
			if(nowj + 1 < N && map[nowi][nowj + 1] == 0) {
				arr[0][nowi][nowj + 1]++;
				dp(nowi, nowj + 1, 0);
			}

			// 대각선
			if(nowi + 1 < N && nowj + 1 < N && map[nowi][nowj + 1] == 0 && map[nowi + 1][nowj + 1] == 0 && map[nowi + 1][nowj] == 0) {
				arr[2][nowi + 1][nowj + 1]++;
				dp(nowi + 1, nowj + 1, 2);
			}
		}
		if(d == 1) { // 전에 왔던 곳이 세로
			//세로
			if(nowi + 1 < N && map[nowi + 1][nowj] == 0) {
				arr[1][nowi + 1][nowj]++;
				dp(nowi + 1, nowj, 1);
			}
			// 대각선
			if(nowi + 1 < N && nowj + 1 < N && map[nowi][nowj + 1] == 0 && map[nowi + 1][nowj + 1] == 0 && map[nowi + 1][nowj] == 0) {
				arr[2][nowi + 1][nowj + 1]++;
				dp(nowi + 1, nowj + 1, 2);
			}
		}
		if(d == 2) { // 전에 왔던 곳이 대각선
			//가로
			if(nowj + 1 < N && map[nowi][nowj + 1] == 0) {
				arr[0][nowi][nowj + 1]++;
				dp(nowi, nowj + 1, 0);
			}
			
			//세로
			if(nowi + 1 < N && map[nowi + 1][nowj] == 0) {
				arr[1][nowi + 1][nowj]++;
				dp(nowi + 1, nowj, 1);
			}
			// 대각선
			if(nowi + 1 < N && nowj + 1 < N && map[nowi][nowj + 1] == 0 && map[nowi + 1][nowj + 1] == 0 && map[nowi + 1][nowj] == 0) {
				arr[2][nowi + 1][nowj + 1]++;
				dp(nowi + 1, nowj + 1, 2);
			}
		}
	}
	static class Point {
		int w=0;	//가로
		int h=0;	//세로
		int c=0;	//대각
	}
}
