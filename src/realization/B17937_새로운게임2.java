package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B17937_새로운게임2 {
	static int N, K;
	static int[][] map;
	static ArrayList<King>[][] list;
	static King[] kingList;
	static int[] di = {0, 0, 0, -1, +1};
	static int[] dj = {0, +1, -1, 0, 0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 체스판의 크기 N
		K = Integer.parseInt(st.nextToken());	// 말의 개수 K
		
		map = new int[N+1][N+1];
		list = new ArrayList[N+1][N+1];
		
		for(int i=1; i<=N; i++) {	//체스판 입력 정보
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				list[i][j] = new ArrayList<>();
			}
		}
		kingList = new King[K+1];
		for(int k=1; k<=K; k++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			
			kingList[k] = new King(R, C, D);
			list[R][C].add(kingList[k]);
		}
		
		for(int k=1; k<=K; k++) {
			King king = kingList[k];
			int r = king.r;
			int c = king.c;
			int d = king.d;
			
			int nexti = r + di[d];
			int nextj = r + dj[d];
			
			if(nexti < 1 || nexti > N || nextj < 1 || nextj > N) continue;
			
			if(map[nexti][nextj] == 0) {	//흰색
				int idx = list[r][c].indexOf(king);
				System.out.println(idx);
				
				int listSize = list[r][c].size();
				for(int i=0; i<listSize; i++) {		//기준 말 위에거 다 옮기기
					list[nexti][nextj].add(list[r][c].remove(idx));
				}
				
				System.out.println(list[nexti][nextj].toString());
			}
		}
	}
	static int getNextDict(int nowd) {
		switch (nowd) {
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
	static class King{
		int r;
		int c;
		int d;
		public King(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
		@Override
		public String toString() {
			return "King [r=" + r + ", c=" + c + ", d=" + d + "]";
		}
		
		
	}
}
