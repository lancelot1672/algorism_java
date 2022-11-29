package BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3089_네잎클로버를찾아서 {
	static int N,M;
	static int[][] map;
	static char[] order;
	static Point sung;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[201][201];
		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			int j = Integer.parseInt(st.nextToken()) + 100;
			int i = Integer.parseInt(st.nextToken()) + 100;
			
			map[i][j] = 1;
		}
		order = br.readLine().toCharArray();
		sung = new Point(0, 0);
		
		for(char o : order) {
			switch (o) {
			case 'U':
				find(sung, 0);
				break;
			case 'D':
				find(sung, 1);
				break;
			case 'R':
				find(sung, 3);
				break;
			case 'L':
				find(sung, 2);
				break;
			}
			
		}
		System.out.println(sung.i + " " + sung.j);
	}
	static int[] di = {-1, 1, 0, 0};	// 0 1 2 3
	static int[] dj = {0, 0, -1, 1};	//U D L R
	
	static void find(Point now, int d) {
		int nexti = 0;
		int nextj = 0;
		int nowi = now.i;
		int nowj = now.j;
		while(true) {
			
			nexti = nowi + di[d];
			nextj = nowj + dj[d];
			
			if(nexti <= -100000 || nexti >= 100000 || nextj <= -100000 || nextj >= 100000) {
				return;
			}
			if(map[nexti][nextj] == 1) {
				// 최초 발견!!!
				sung.i = nexti;
				sung.j = nextj;
				break;
			}
			
			nowi = nexti;
			nowj = nextj;
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
