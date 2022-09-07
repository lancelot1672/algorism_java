package backtracking;

import java.util.ArrayList;
import java.util.Scanner;

public class B15683_감시 {
	static int[][] map;
	static int[] dir = {1,2,3,4};
	static int N,M;
	static ArrayList<CCTV> list = new ArrayList<>();
	static int[] cctvDir;
	static int min= Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][M];
		
		
		int cnt=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] >=1 && map[i][j] <= 5) {
					list.add(new CCTV(i,j, map[i][j]));
				}
			}
		}// input End
		cctvDir = new int[list.size()];
		
		perm(0);
		System.out.println(min);
	}
	static void perm(int idx) {		//중복 순열로 방향 뽑아서 돌려보자
		if(idx == list.size()) {
			int[][] copy = deepcopy(map);
			for(int i=0; i<list.size(); i++) {
				CCTV cctv = list.get(i);
				switch (cctv.dir) {
				case 1:
					one(cctv, cctvDir[i]);
					break;
				case 2:
					two(cctv, cctvDir[i]);
					break;
				case 3:
					three(cctv, cctvDir[i]);
					break;
				case 4:
					four(cctv, cctvDir[i]);
					break;
				case 5:
					five(cctv, cctvDir[i]);
					break;
				}
			}
			//check
			int cnt=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(copy[i][j] == 0) {
						cnt++;
					}
				}
			}
			min = Math.min(cnt, min);
			return;
		}
		for(int i=1; i<=4; i++) {
			cctvDir[idx] = i;
			perm(idx+1);
		}
	}
	static int[][] copy;

	static int[] di = {0, -1, 0, +1, 0};		//1, 2, 3, 4
	static int[] dj = {0, 0, +1, 0, -1};
	
	static void one(CCTV c, int dir) {	//1번 CCTV 그리기	- 한 방향
		int nowi = c.i;
		int nowj = c.j;
		while(true) {
			int nexti = nowi + di[dir];
			int nextj = nowj + dj[dir];
			
			if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= M) {
				break;
			}
			if(copy[nexti][nextj] == 6) {
				break;
			}
			if(copy[nexti][nextj] == 0) {
				copy[nexti][nextj] = -1;
			}
			nowi = nexti;
			nowj = nextj;
		}
	}
	static void two(CCTV c, int dir) {
		if(dir == 1 || dir == 3) {	// 어차피 위 아래 니
			one(c, 1);
			one(c, 3);
		}else {
			one(c, 2);
			one(c, 4);
		}
	}
	static void three(CCTV c, int dir) {
		if(dir == 1) {
			one(c, 1);
			one(c, 2);
		}else if(dir == 2) {
			one(c, 2);
			one(c, 3);	
		}else if(dir == 3) {
			one(c, 3);
			one(c, 4);
		}else if(dir == 4) {
			one(c,4);
			one(c,1);
		}
	}
	static void four(CCTV c, int dir) {
		if(dir == 1) {
			one(c, 4);
			one(c, 1);
			one(c, 2);
	
		}else if(dir == 2) {
			one(c, 1);
			one(c, 2);
			one(c, 3);

		}else if(dir == 3) {
			one(c, 2);
			one(c, 3);
			one(c, 4);
		}else if(dir == 4) {
			one(c,3);
			one(c,4);
			one(c,1);
		}
	}
	static void five(CCTV c, int dir) {
		one(c, 1);
		one(c, 2);
		one(c, 3);
		one(c, 4);
	}
	static int[][] deepcopy(int[][] origin){
		copy = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copy[i][j] = origin[i][j];
			}
		}
		
		return copy;
	}
	static class CCTV {
		int i;
		int j;
		int dir;
		public CCTV(int i, int j, int dir) {
			this.i = i;
			this.j = j;
			this.dir = dir;
		}
		
	}
}
