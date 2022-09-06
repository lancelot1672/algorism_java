package BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ14502_연구소 {
	static int N,M;
	static int[][] arr;
	static boolean[][] visitied;
	static Point[] list;
	static Point[] output;
	static ArrayList<Point> virus;
	static int count0;
	static int max = 0;
	static int listIndex;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[N][M];
		visitied = new boolean[N][M];
		
		list = new Point[N*M];
		output = new Point[3];
		virus = new ArrayList<>();
		count0 = 0;
		listIndex = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j] == 0) {
					count0++;
					list[listIndex++] = new Point(i,j);
				}else if(arr[i][j] == 2) {
					virus.add(new Point(i,j));
					
					visitied[i][j] = true;
				}
				
			}
		}
		comb(0,0);
		System.out.println(max);
	}
	static void comb(int idx, int cnt) {
		if(cnt == 3) {
			func(output);
			
//			for(int i=0; i<output.length; i++) {
//				System.out.println(output[i]);
//			}
//			System.out.println();
			return;
		}
		
		if(idx == listIndex) {
			return;
		}
		output[cnt] = list[idx];
		comb(idx+1, cnt+1);
		
		comb(idx+1, cnt);
		
	}
	static void func(Point[] output) {
		int count = count0 - 3;
		int[][] temp = deepCopy(arr);
		for(int i=0; i<output.length; i++) {
			Point p = output[i];
			temp[p.i][p.j] = 1;

		}

		bfs(count, temp);
		
	}
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
//	static void dfs(int i, int j, int[][] temp) {
//		
//		
//		for(int d=0; d<4; d++) {
//			int nexti = now.i + di[d];
//			int nextj = now.j + dj[d];
//			
//			if(nexti >= 0 && nexti < N && nextj >= 0 && nextj < M && temp[nexti][nextj] == 0) {
//				q.add(new Point(nexti,nextj));
//				temp[nexti][nextj] = 2;
//				count--;
//			}
//		}
//
//	}
	static void bfs(int count, int[][] temp) {
		Queue<Point> queue = new LinkedList<Point>();
		for(int i=0; i<virus.size(); i++) {
			queue.add(virus.get(i));
		}
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nexti = now.i + di[d];
				int nextj = now.j + dj[d];
				
				if(nexti >= 0 && nexti < N && nextj >= 0 && nextj < M && temp[nexti][nextj] == 0) {
					queue.add(new Point(nexti,nextj));
					temp[nexti][nextj] = 2;
					count--;
				}
			}

		}
		if (max < count) {
			max = count;
		}
	}
	static int[][] deepCopy(int[][] origin){
		int[][] copy = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copy[i][j] = origin[i][j];
			}
		}
		
		return copy;
	}
	static void print(int[][] arr) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	static class Point{
		int i;
		int j;
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + "]";
		}
		
		
	}
}
