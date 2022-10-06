package BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S7793_오나의여신님 {
	static int N, M;
	static char[][] map;
	static Point su;
	static ArrayList<Point> hell;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new char[N][M];
			hell = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				char[] cArr = br.readLine().toCharArray();
				for (int j = 0; j < M; j++) {
					map[i][j] = cArr[j];
					if (map[i][j] == 'S')
						su = new Point(i, j);
					else if (map[i][j] == '*')
						hell.add(new Point(i, j));
				}
			} // end Input
			int time = bfs();
			if (time == -1) {
				System.out.println("#" + tc + " " + "GAME OVER");
			} else {
				System.out.println("#" + tc + " " + time);
			}
		}

	}

	static int[] di = { 1, -1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };

	static int bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visit_su = new boolean[N][M];
		boolean[][] visit_hell = new boolean[N][M];
		boolean flag = false;
		q.add(su);
		for(Point h : hell) {
			q.add(h);
			visit_hell[h.i][h.j] = true;
		}
		visit_su[su.i][su.j] = true;


		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Point now = q.poll();
				if (map[now.i][now.j] == 'S') { // 수연이
					for (int d = 0; d < 4; d++) {
						int nexti = now.i + di[d];
						int nextj = now.j + dj[d];

						if (nexti < 0 || nexti >= N || nextj < 0 || nextj >= M) {
							continue;
						}
						if (visit_su[nexti][nextj]) {
							continue;
						}
						if (map[nexti][nextj] == 'D') {
							return time+1;
							
						}
						if (map[nexti][nextj] == '.') {
							map[now.i][now.j] = '.'; // 지나온자리
							map[nexti][nextj] = 'S'; // 가는자리
							visit_su[nexti][nextj] = true; // 미리 방문
							q.add(new Point(nexti, nextj));
						}
					}
				}
				if (map[now.i][now.j] == '*') { // 악마의 손아귀!!!
					for (int d = 0; d < 4; d++) {
						int nexti = now.i + di[d];
						int nextj = now.j + dj[d];

						if (nexti < 0 || nexti >= N || nextj < 0 || nextj >= M) {
							continue;
						}
						if (visit_hell[nexti][nextj]) {
							continue;
						}

						if (map[nexti][nextj] != 'X' && map[nexti][nextj] != 'D') { // 수연이 잡기 가능
							// 퍼지는것이기 때문에 지나온자리 X
							map[nexti][nextj] = '*'; // 가는자리
							visit_hell[nexti][nextj] = true; // 미리 방문
							q.add(new Point(nexti, nextj));
						}
					}
				}
			}
			time++;
			// print();
		}
		return -1;
	}

	static void print() {
		System.out.println();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	static class Point {
		int i;
		int j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}

	}
}
