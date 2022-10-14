package BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B3055_탈출 {
	static char[][] map;
	static int R, C;
	static Point biber;
	static ArrayList<Point> water;
	static int ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer s = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(s.nextToken());
		C = Integer.parseInt(s.nextToken());
		map = new char[R][C];
		ans = 0;
		water = new ArrayList<>();
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				if(map[i][j] == 'S') {
					biber = new Point(i, j);
				}else if(map[i][j] == '*') {
					water.add(new Point(i, j));
				}
			}
		}
		bfs();

		if(ans == 0) {
			System.out.println("KAKTUS");
		}else {
			System.out.println(ans);
		}
	}
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited_water = new boolean[R][C];
		boolean[][] visited_biber = new boolean[R][C];
		// 비버 이동
		q.add(biber);
		visited_biber[biber.i][biber.j] = true;
		
		//물 먼저 이동
		for(Point w : water) {
			q.add(w);
			visited_water[w.i][w.j] = true;
		}
		
		int time = 1;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s=0; s < size; s++) {
				Point now = q.poll();
				
				for(int d=0; d<4; d++) {
					int nexti = now.i + di[d];
					int nextj = now.j + dj[d];
					if(nexti < 0 || nexti >= R || nextj < 0 || nextj >= C) continue;
					
					if(map[now.i][now.j] == '*') {	// 물이다!!!
						if(visited_water[nexti][nextj]) continue;
						if(map[nexti][nextj] == 'X' || map[nexti][nextj] == 'D') continue;
						
						map[nexti][nextj] = '*';	//물로 변경
						visited_water[nexti][nextj] = true;
						q.add(new Point(nexti, nextj));
					}
					else if(map[now.i][now.j] == 'S') {	// 슴도치
						if(visited_biber[nexti][nextj]) continue;
						if(map[nexti][nextj] == 'X' || map[nexti][nextj] == '*') continue;
						
						if(map[nexti][nextj] == 'D') {
							ans = time++;
							return;
						}
						map[nexti][nextj] = 'S';		//슴도치로 변경
						visited_biber[nexti][nextj] = true;
						q.add(new Point(nexti, nextj));
					}
				}

			}
			time++;
			//print();
		}
	}
	static void print() {
		System.out.println("--------------");
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	static class Point{
		int i;
		int j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
		
	}
}
