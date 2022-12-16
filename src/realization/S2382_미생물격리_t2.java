package realization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2382_미생물격리_t2 {
	static Point[][] map1;
	static Point[][] map2;
	static Point[][] dist;
	static long ans;
	static int N;
	static int M;
	static int K;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map1 = new Point[N][N];

			ans = 0;
			
			for(int k=0; k<K; k++) {
				 st = new StringTokenizer(br.readLine());
				 int i = Integer.parseInt(st.nextToken());
				 int j = Integer.parseInt(st.nextToken());
				 int cnt = Integer.parseInt(st.nextToken());
				 int d = Integer.parseInt(st.nextToken());
				 
				 map1[i][j] = new Point(cnt, d);
			}// end input
			
			for(int m=0; m<M; m++) {
				map2 = new Point[N][N];
				dist = new Point[N][N];
				
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(map1[i][j] == null) continue;	//미생물 없으면 패스
						
						int nexti = i + di[map1[i][j].d];
						int nextj = j + dj[map1[i][j].d];
						
						if(nexti == 0 || nexti == N-1 || nextj == 0 || nextj == N-1) {
							//여기는 미생물 구역입니다.
							map1[i][j].cnt = map1[i][j].cnt / 2;
							
							//방향 바꾸세요
							map1[i][j].d = turn(map1[i][j].d);
						}
						// 여기는 일반 구역입니다.
						// 방향 먼저 매핑
						if(dist[nexti][nextj] != null) {	// 이미 온 놈이 있네?
							if(dist[nexti][nextj].cnt < map1[i][j].cnt) {	//내가 더 커 방향 내걸로 해
								dist[nexti][nextj] = new Point(map1[i][j].cnt, map1[i][j].d);
							}
						}else {	//내가 처음이야?
							dist[nexti][nextj] = new Point(map1[i][j].cnt, map1[i][j].d);
						}
						
						if(map2[nexti][nextj] != null) {	// 이미 온 놈이 있네?
							map2[nexti][nextj].cnt += map1[i][j].cnt;	// 합치기
							map1[i][j] = null;
							
						}else {
							map2[nexti][nextj] = new Point(map1[i][j].cnt, map1[i][j].d);
							map1[i][j] = null;
						}
						//끝
						
					}
				}// end 이중 포문
				//방향 조정해
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(map2[i][j] != null && dist[i][j] != null) {
							map2[i][j].d = dist[i][j].d;
						}
					}
				}
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(map2[i][j] != null) {
							map1[i][j] = map2[i][j];
						}
					}
				}
				///////////////////////////////////////////////// end M
			}
			//다 돌았어??
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map1[i][j] != null)
						ans += map1[i][j].cnt;
				}
			}	
			System.out.println("#" + tc + " " + ans);
		}
	}
	static int turn(int d) {
		switch (d) {
		case 1:
			return 2;
		case 2:
			return 1;
		case 3:
			return 4;
		case 4:
			return 3;
		}
		return -1;
	}
	static int[] di = {0, -1, 1, 0, 0};
	static int[] dj = {0, 0, 0, -1, 1};
	static class Point {
		long cnt;
		int d;
		public Point(long cnt, int d) {
			this.cnt = cnt;
			this.d = d;
		}
		
	}
	static void print() {
		System.out.println("--------------------------");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map1[i][j] != null) {
					System.out.print(map1[i][j].d + " ");
				}else {
					System.out.print("0 ");
				}

			}
			System.out.println();
		}// end  copy
	}
}
