package realization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2382_미생물격리 {
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
			}
			
			for(int t=0; t<M; t++) {
				map2 = new Point[N][N];
				dist = new Point[N][N];
				print();
				//미생물 이동.
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(map1[i][j] != null) {
							Point now = map1[i][j];
							map1[i][j] = null;
							
							int nexti = i + di[now.d];
							int nextj = j + dj[now.d];
							
							// 가장자리 가면
							if(nexti == 0 || nexti == N-1 || nextj == 0 || nextj == N-1) {
								now.cnt = now.cnt / 2;
								switch (now.d) {
								case 1:
									now.d = 2;
									break;
								case 2:
									now.d = 1;
									break;
								case 3:
									now.d = 4;
									break;
								case 4:
									now.d = 3;
									break;
								}
							}
							//이동했을 때 누군가 있으면??
							if(map2[nexti][nextj] != null) {
								Point next = map2[nexti][nextj];
								if(next.cnt > now.cnt) {	//원래 있던 놈이 더 큰 군집
									next.cnt += now.cnt;	//군집 합쳐짐
									
								}else {
									now.cnt += next.cnt;
									map2[nexti][nextj] = now;	// 내것의 방향으로 바뀜
									
								}
								//방향 체크 다시
								if(dist[nexti][nextj].cnt > now.cnt) {
									dist[nexti][nextj] = new Point(next.cnt, next.d);	// 큰놈의 cnt와 방향 저장.
								}else {
									dist[nexti][nextj] = new Point(now.cnt, now.d);
								}
								
								
							}else {	//없으면 내가 짱
								map2[nexti][nextj] = now;
								dist[nexti][nextj] = new Point(now.cnt, now.d);
							}
						}
					} // end i
				} // end j
				// map2 -> map1
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(map2[i][j] != null) {
							map2[i][j].d = dist[i][j].d;
							map1[i][j] = map2[i][j];
						}
					}
				}// end  copy

			}//end t
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map1[i][j] != null) {
						ans += map1[i][j].cnt;
					}
				}
			}// end  copy
			print();
			System.out.println("#" + tc + " " + ans);
		}
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
