package BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1953_탈주범검거2 {
	static int N, M, R, C, L;
	static Point start;
	static int[][] map;
	static int total;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			start = new Point(R, C);
			total = 1;
			map = new int[N][M];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}// end input
			bfs(start);
			System.out.println("#" + tc + " " + total);
			
		}
	}
	static int[] di = {-1, 1, 0, 0};	// 상 하 우 좌
	static int[] dj = {0, 0, 1, -1};
	static int[][] dontgo = {
			{3, 4, 7}, {3, 5, 6}, {2, 4, 5}, {2, 6, 7}
	};
	static boolean[][] visited;
	static void bfs(Point start) {
		Queue<Point > q = new LinkedList<>();
		visited = new boolean[N][M];
		q.add(start);	// 처음 시작
		visited[start.i][start.j] = true;

		int time = 1;
		while(!q.isEmpty()) {
			if(time == L) return;
			int size = q.size();
			for(int s=0; s<size; s++) {
				Point now = q.poll();
				
				int pipe = map[now.i][now.j];		//현재 위치 모양 정보
				
				switch (pipe) {
				case 2:
					for(int d=0; d<4; d++) {	//2은 상 0  하 1 갈 수 있음.
						if(d == 2 || d == 3) continue;
						int nexti = now.i + di[d];
						int nextj = now.j + dj[d];
						
						if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= M || visited[nexti][nextj] || map[nexti][nextj] == 0) continue;
						int check_Cnt = 3;
						for(int c=0; c<3; c++) {
							if(map[nexti][nextj] == dontgo[d][c]) check_Cnt--;
						}
						if(check_Cnt != 3) continue;
						
						// 갈 수 있음.
						total++;
						visited[nexti][nextj] = true;
						q.add(new Point(nexti, nextj));
						
					}
					break;
				case 1:
					for(int d=0; d<4; d++) {	//1은 4군데 다 갈 수 있음.
						int nexti = now.i + di[d];
						int nextj = now.j + dj[d];
						
						if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= M || visited[nexti][nextj] || map[nexti][nextj] == 0) continue;
						int check_Cnt = 3;
						for(int c=0; c<3; c++) {
							if(map[nexti][nextj] == dontgo[d][c]) check_Cnt--;
						}
						if(check_Cnt != 3) continue;
						
						// 갈 수 있음.
						total++;
						visited[nexti][nextj] = true;
						q.add(new Point(nexti, nextj));
						
					}
					break;
				case 3:
					for(int d=0; d<4; d++) {	//3은 우 2  좌 3 갈 수 있음.
						if(d == 0 || d == 1) continue;
						int nexti = now.i + di[d];
						int nextj = now.j + dj[d];
						
						if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= M || visited[nexti][nextj] || map[nexti][nextj] == 0) continue;
						int check_Cnt = 3;
						for(int c=0; c<3; c++) {
							if(map[nexti][nextj] == dontgo[d][c]) check_Cnt--;
						}
						if(check_Cnt != 3) continue;
						
						// 갈 수 있음.
						total++;
						visited[nexti][nextj] = true;
						q.add(new Point(nexti, nextj));
						
					}
					break;
				case 4:
					for(int d=0; d<4; d++) {	//4은 상0  우 2 갈 수 있음.
						if(d == 1 || d == 3) continue;
						int nexti = now.i + di[d];
						int nextj = now.j + dj[d];
						
						if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= M || visited[nexti][nextj] || map[nexti][nextj] == 0) continue;
						int check_Cnt = 3;
						for(int c=0; c<3; c++) {
							if(map[nexti][nextj] == dontgo[d][c]) check_Cnt--;
						}
						if(check_Cnt != 3) continue;
						
						// 갈 수 있음.
						total++;
						visited[nexti][nextj] = true;
						q.add(new Point(nexti, nextj));
						
					}
					break;
				case 5:
					for(int d=0; d<4; d++) {	//5은 하1  우 2 갈 수 있음.
						if(d == 0 || d == 3) continue;
						int nexti = now.i + di[d];
						int nextj = now.j + dj[d];
						
						if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= M || visited[nexti][nextj] || map[nexti][nextj] == 0) continue;
						int check_Cnt = 3;
						for(int c=0; c<3; c++) {
							if(map[nexti][nextj] == dontgo[d][c]) check_Cnt--;
						}
						if(check_Cnt != 3) continue;
						
						// 갈 수 있음.
						total++;
						visited[nexti][nextj] = true;
						q.add(new Point(nexti, nextj));
						
					}
					break;
				case 6:
					for(int d=0; d<4; d++) {	//6은 하1  좌 3 갈 수 있음.
						if(d == 0 || d == 2) continue;
						int nexti = now.i + di[d];
						int nextj = now.j + dj[d];
						
						if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= M || visited[nexti][nextj] || map[nexti][nextj] == 0) continue;
						int check_Cnt = 3;
						for(int c=0; c<3; c++) {
							if(map[nexti][nextj] == dontgo[d][c]) check_Cnt--;
						}
						if(check_Cnt != 3) continue;
						
						// 갈 수 있음.
						total++;
						visited[nexti][nextj] = true;
						q.add(new Point(nexti, nextj));
						
					}
					break;
					
				case 7:
					for(int d=0; d<4; d++) {	//6은 상0  좌 3 갈 수 있음.
						if(d == 1 || d == 2) continue;
						int nexti = now.i + di[d];
						int nextj = now.j + dj[d];
						
						if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= M || visited[nexti][nextj] || map[nexti][nextj] == 0) continue;
						int check_Cnt = 3;
						for(int c=0; c<3; c++) {
							if(map[nexti][nextj] == dontgo[d][c]) check_Cnt--;
						}
						if(check_Cnt != 3) continue;
						
						// 갈 수 있음.
						total++;
						visited[nexti][nextj] = true;
						q.add(new Point(nexti, nextj));
						
					}
					break;
				}// end switch
			}//end d
			time++;

		}
	}
	static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j]) {
					System.out.print("9 ");
				}else {
					System.out.print(map[i][j] + " ");
				}
				
			}
			System.out.println();
		}
	}
	static class Point{
		int i;
		int j;
		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
		
	}
}
