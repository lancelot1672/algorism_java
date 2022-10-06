package BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1953_탈주범검거 {
	static int N, M, R, C, L;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지도 i
			M = Integer.parseInt(st.nextToken()); // 자도 j
			R = Integer.parseInt(st.nextToken()); // 멘홀 R
			C = Integer.parseInt(st.nextToken()); // 맨홀 C
			L = Integer.parseInt(st.nextToken()); // 시간

			map = new int[N][M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int ans = bfs(R, C);
			//print();
			System.out.println("#" + tc + " " + ans);
		}
	}

	static int[] di = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dj = { 0, 0, -1, 1 };

	static int bfs(int starti, int startj) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		q.add(new Point(starti, startj, map[starti][startj]));
		map[starti][startj] = 9;
		
		int cnt = 1;
		int time = 1;
		while (!q.isEmpty()) {
			if(time == L) {
				break;
			}
			
			int size = q.size();
			for (int s = 0; s < size; s++) {

				Point now = q.poll();
				//System.out.println(now);
				int nowi = now.i;
				int nowj = now.j;
				int tunnel = now.tunnel;

				switch (tunnel) {
				case 1: // 상 0 하 1 좌 2 우 3 다갈 수 있음
					for (int d = 0; d < 4; d++) {
						int nexti = nowi + di[d];
						int nextj = nowj + dj[d];

						if (nexti < 0 || nexti >= N || nextj < 0 || nextj >= M) {
							continue;
						}
						
						if(map[nexti][nextj] != 0 && map[nexti][nextj] != 9) {
							// 갈곳 확인해야됨.
							if(d == 0 && (map[nexti][nextj] == 3 || map[nexti][nextj] == 4 || map[nexti][nextj] == 7)) {
								continue;
							}
							if(d == 1 && (map[nexti][nextj] == 3 || map[nexti][nextj] == 5 || map[nexti][nextj] == 6)) {
								continue;
							}
							if(d == 2 && (map[nexti][nextj] == 2 || map[nexti][nextj] == 6 || map[nexti][nextj] == 7)) {
								continue;
							}
							if(d == 3 && (map[nexti][nextj] == 2 || map[nexti][nextj] == 4 || map[nexti][nextj] == 5)) {
								continue;
							}
							q.add(new Point(nexti, nextj, map[nexti][nextj]));
							map[nexti][nextj] = 9;
							cnt++;
						}

					}
					break;
				case 2: // 상 0 하 1 갈 수 있음
					for (int d = 0; d < 2; d++) {
						int nexti = nowi + di[d];
						int nextj = nowj + dj[d];

						if (nexti < 0 || nexti >= N || nextj < 0 || nextj >= M) {
							continue;
						}

						if(map[nexti][nextj] != 0 && map[nexti][nextj] != 9) {
							// 갈곳 확인해야됨.
							
							if(map[nexti][nextj] == 3) {
								continue;
							}
							//상일때
							if(d == 0 && (map[nexti][nextj] == 4 || map[nexti][nextj] == 7)) {
								continue;
							}
							//하 일때
							if(d == 1 && (map[nexti][nextj] == 5 || map[nexti][nextj] == 6)) {
								continue;
							}
							q.add(new Point(nexti, nextj, map[nexti][nextj]));
							map[nexti][nextj] = 9;
							cnt++;
						}
					}
					break;
				case 3: // 좌 2 우 3 갈 수 있음
					for (int d = 2; d < 4; d++) {
						int nexti = nowi + di[d];
						int nextj = nowj + dj[d];

						if (nexti < 0 || nexti >= N || nextj < 0 || nextj >= M) {
							continue;
						}

						if(map[nexti][nextj] != 0 && map[nexti][nextj] != 9) {
							// 갈곳 확인해야됨.
							
							if(map[nexti][nextj] == 2) {
								continue;
							}
							// 좌 일때
							if(d == 2 && (map[nexti][nextj] == 6 || map[nexti][nextj] == 7)) {
								continue;
							}
							// 우 일때
							if(d == 3 && (map[nexti][nextj] == 4 || map[nexti][nextj] == 5)) {
								continue;
							}
							q.add(new Point(nexti, nextj, map[nexti][nextj]));
							map[nexti][nextj] = 9;
							cnt++;
						}
					}
					break;
				case 4: // 상 0 우 3 갈 수 있음
					for (int d = 0; d < 4; d++) {
						if (d == 1 || d == 2)
							continue;
						int nexti = nowi + di[d];
						int nextj = nowj + dj[d];

						if (nexti < 0 || nexti >= N || nextj < 0 || nextj >= M) {
							continue;
						}
						
						if(map[nexti][nextj] != 0 && map[nexti][nextj] != 9) {
							// 상 일때
							if(d == 0 && (map[nexti][nextj] == 3 || map[nexti][nextj] == 4 || map[nexti][nextj] == 7)) {
								continue;
							}
							// 우 일때
							if(d == 3 && (map[nexti][nextj] == 2 || map[nexti][nextj] == 4 || map[nexti][nextj] == 5)) {
								continue;
							}
							
							q.add(new Point(nexti, nextj, map[nexti][nextj]));
							map[nexti][nextj] = 9;
							cnt++;
						}
					}
					break;
				case 5: // 하 1 우 3 갈 수 있음
					for (int d = 0; d < 4; d++) {
						if (d == 0 || d == 2)
							continue;
						int nexti = nowi + di[d];
						int nextj = nowj + dj[d];

						if (nexti < 0 || nexti >= N || nextj < 0 || nextj >= M) {
							continue;
						}
						if(map[nexti][nextj] != 0 && map[nexti][nextj] != 9) {
							
							// 하 일때
							if(d == 1 && (map[nexti][nextj] == 3 || map[nexti][nextj] == 5 || map[nexti][nextj] == 6)) {
								continue;
							}
							// 우 일때
							if(d == 3 && (map[nexti][nextj] == 2 || map[nexti][nextj] == 5 || map[nexti][nextj] == 4)) {
								continue;
							}
							q.add(new Point(nexti, nextj, map[nexti][nextj]));
							map[nexti][nextj] = 9;
							cnt++;
						}
					}
					break;
				case 6: // 하 1 좌 2 갈 수 있음
					for (int d = 0; d < 4; d++) {
						if (d == 0 || d == 3)
							continue;
						int nexti = nowi + di[d];
						int nextj = nowj + dj[d];

						if (nexti < 0 || nexti >= N || nextj < 0 || nextj >= M) {
							continue;
						}

						if(map[nexti][nextj] != 0 && map[nexti][nextj] != 9) {
							
							// 하 일때
							if(d == 1 && (map[nexti][nextj] == 3 || map[nexti][nextj] == 5 || map[nexti][nextj] == 6)) {
								continue;
							}
							// 좌 일때
							if(d == 2 && (map[nexti][nextj] == 2 || map[nexti][nextj] == 7 || map[nexti][nextj] == 6)) {
								continue;
							}
							q.add(new Point(nexti, nextj, map[nexti][nextj]));
							map[nexti][nextj] = 9;
							cnt++;
						}
					}
					break;
				case 7: // 상 0 좌 2 갈 수 있음
					for (int d = 0; d < 4; d++) {
						if (d == 1 || d == 3)
							continue;
						int nexti = nowi + di[d];
						int nextj = nowj + dj[d];

						if (nexti < 0 || nexti >= N || nextj < 0 || nextj >= M) {
							continue;
						}
						if(map[nexti][nextj] != 0 && map[nexti][nextj] != 9) {
							
							// 상 일때
							if(d == 0 && (map[nexti][nextj] == 3 || map[nexti][nextj] == 4 || map[nexti][nextj] == 7)) {
								continue;
							}
							// 좌 일때
							if(d == 2 && (map[nexti][nextj] == 2 || map[nexti][nextj] == 6 || map[nexti][nextj] == 7)) {
								continue;
							}
							q.add(new Point(nexti, nextj, map[nexti][nextj]));
							map[nexti][nextj] = 9;
							cnt++;
						}
					}
					break;
				}// end Switch
			}
			time++;

		}
		return cnt;
	}
	static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	static class Point {
		int i;
		int j;
		int tunnel;

		public Point(int i, int j, int tunnel) {
			this.i = i;
			this.j = j;
			this.tunnel = tunnel;
		}

		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", tunnel=" + tunnel + "]";
		}

	}
}
