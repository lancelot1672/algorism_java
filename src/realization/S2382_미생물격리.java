package realization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2382_미생물격리 {
	static Point[][] map1;
	static Point[][] map2;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			map1 = new Point[N][N];
			
			for(int k=0; k<K; k++) {
				 st = new StringTokenizer(br.readLine());
				 int i = Integer.parseInt(st.nextToken());
				 int j = Integer.parseInt(st.nextToken());
				 int cnt = Integer.parseInt(st.nextToken());
				 int d = Integer.parseInt(st.nextToken());
				 
				 map1[i][j] = new Point(cnt, d);
			}
			
			for(int t=0; t<M; t++) {
				//미생물 이동.
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(map1[i][j] != null) {
							Point now = map1[i][j];
							int nexti = i + di[now.d];
							int nextj = j + dj[now.d];
							
							//
						}
					}
				}
			}
		}
	}
	static int[] di = {0, 1, -1, 0, 0};
	static int[] dj = {0, 0, 0, -1, 1};
	static class Point {
		int cnt;
		int d;
		public Point(int cnt, int d) {
			this.cnt = cnt;
			this.d = d;
		}
		
	}
}
