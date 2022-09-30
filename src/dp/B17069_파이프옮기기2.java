package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17069_파이프옮기기2 {
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

		long result = arr[0][N-1][N-1] + arr[1][N-1][N-1] + arr[2][N-1][N-1];
		System.out.println(result);
	}

	static class Point {
		int w=0;	//가로
		int h=0;	//세로
		int c=0;	//대각
	}
}
