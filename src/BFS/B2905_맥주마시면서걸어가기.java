package BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B2905_맥주마시면서걸어가기 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static Point home;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		N = sc.nextInt();
		map = new int[65536][65536];
		visited = new boolean[65536][65536];
		
		//상근이네 집
		
		// 편의점
		
		// 펜타포트 락 페스티벌
		
		for(int i=0; i<N+2; i++) {
			int x = sc.nextInt() + 32768;
			int y = sc.nextInt() + 32768;
			
			map[y][x] = 1;
			if(i == 0) {
				home = new Point(y,x);
			}
		}
		
		Queue<Point> q = new LinkedList<>();
		q.add(home);
		visited[home.i][home.j] = true;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			
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
