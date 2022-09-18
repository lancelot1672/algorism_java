package backtracking;

import java.util.ArrayList;
import java.util.Scanner;

public class B15684_사다리조작2 {
	static int[][] map;
	static int N;
	static int M;
	static int H;
	static int I;
	static int J;
	static int C;
	static int[] di2 = {0, 0};
	static int[] dj2 = {2, -2};
	static ArrayList<Point> list = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();		//세로선의 갯수
		M =sc.nextInt();		// 가로선의 갯수
		H = sc.nextInt();
		
		I = H + 1;
		J = N + (N-1);
		map = new int[I][J];
		for(int i=0; i<I; i++) {
			for(int j=0; j<J; j++){
				if(j % 2 == 0) {
					map[i][j] = 1;
				}
			}
		}
		
		//가로선 긋기
		for(int m=0; m<M; m++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			map[a-1][b*2 -1] = 1;
		}
		// 맨 마지막 줄
		for(int j=0; j<J; j++) {
			if(j % 2 == 0) {
				map[I-1][j] = 1;
			}else {
				map[I-1][j] = -1;
			}
		}
		// 그을 수 있는 거 넣기
		for(int i=0; i<I; i++) {
			for(int j=0; j<J; j++) {
				
				if(map[i][j] == 0) {
					if(check2(i,j)) {
						list.add(new Point(i,j));
					}
				}
			}
		}

		if(check(map)) {							// 안뽑고 가능하다면 끝
			System.out.println(0);
			return;
		}
		for(int c=1; c<=list.size(); c++) {			//3개로 안됀다면 끝
			if(c == 4) {
				System.out.println(-1);
				return;
			}
			C = c;
			output = new Point[C];

			comb(0,0);
			if(!flag) {
				break;
			}
		}
	}
	static boolean check2(int i, int j) {
		for(int d=0; d<2; d++) {
			int nexti = i + di2[d];
			int nextj = j + dj2[d];
			if(nexti < 0 || nexti >= I || nextj < 0 || nextj >= J) {
				continue;
			}
			if(map[nexti][nextj] == 1) {
				return false;
			}
		}
		return true;
	}
	static Point[] output;
	static boolean flag = true;
	static void comb(int idx, int cnt ) {
		if(cnt == C) {
			if(!flag) {
				return;
			}
			func(0);
			return;
		}
		if(idx == list.size()) {
			return;
		}
		
		output[cnt] = list.get(idx);
		comb(idx+1, cnt+1);
		comb(idx+1, cnt);
	}
	static void func(int idx) {
		if(idx == C) {
			 if(check(map)) {
					System.out.println(C);
					flag = false;
			 }
			 return;
		}
		Point p = output[idx];
//		if(p.j -1 == 0) {	// 1, 2번줄 사이
//			if(p.j +2 < J && map[p.i][p.j + 2] == 1) {
//				return;
//			}
//			map[p.i][p.j] = 1;
//			func(idx+1);
//			map[p.i][p.j] = 0;
//			
//		}
//		else if(p.j + 1 == J -1) {
//			if(p.j - 2 >= 0 && map[p.i][p.j - 2] == 1) {
//				return;
//			}
//			map[p.i][p.j] = 1;
//			func(idx+1);
//			map[p.i][p.j] = 0;
//			
//		}else {
//			if(map[p.i][p.j + 2] == 1) {
//				return;
//			}
//			if(map[p.i][p.j - 2] == 1) {
//				return;
//			}
//			map[p.i][p.j] = 1;
//			func(idx+1);
//			map[p.i][p.j] = 0;
//		}
		for(int d=0; d<2; d++) {
			int nexti = p.i + di2[d];
			int nextj = p.j + dj2[d];
			if(nexti < 0 || nexti >= I || nextj < 0 || nextj >= J) {
				continue;
			}
			if(map[nexti][nextj] == 1) {
				return;
			}
		}
		map[p.i][p.j] = 1;
		func(idx+1);
		map[p.i][p.j] = 0;
	}
	static boolean check(int[][] copy) {
		for(int j=0; j<J; j++) {
			if(j % 2 == 0) {
				if(!dfs(new Point(0, j), copy)){
					return false;
				}
			}
		}
		return true;
	}
	static int[] di = {0, 0, 1, -1};			//오른 왼 아래
	static int[] dj = {1, -1, 0, 0};
	static boolean dfs(Point start, int[][] map) {
		boolean[][] visited = new boolean[I][J];
		int nowi = start.i;
		int nowj = start.j;
		
		int nexti = 0;
		int nextj = 0;
		while(true) {
			for(int d=0; d<3; d++) {
				nexti = nowi + di[d];
				nextj = nowj + dj[d];
				
				if(nexti < 0 || nexti >= I || nextj < 0 || nextj >= J) {		//범위체크
					continue;
				}
				if(visited[nexti][nextj]) {
					continue;
				}
				if(d == 2) {
					visited[nowi][nowj] = true;
					nowi = nexti;
					nowj = nextj;
				}else {		//좌우 확인해서 있어?
					if(map[nexti][nextj] == 1) {	//옆으로 갈 수 있어?
						//일단 내꺼 방문체크
						while(true) {
							visited[nowi][nowj] = true;
							nexti = nowi + di[d];
							nextj = nowj + dj[d];
							if(nexti < 0 || nexti >= I || nextj < 0 || nextj >= J) {		//범위체크
								break;
							}
							if(map[nexti][nextj] == 0) {
								break;
							}
							
							nowi = nexti;
							nowj = nextj;
						}
					}else {
						continue;
					}
				}
			}
			if(nexti == I-1) {
				if(nextj == start.j) {
					return true;
				}else {
					return false;
				}
			}

		}
	}
	static void print(int[][] map) {
		System.out.println();
		for(int i=0; i<I; i++) {
			for(int j=0; j<J; j++){
				System.out.print(map[i][j] + " ");
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
