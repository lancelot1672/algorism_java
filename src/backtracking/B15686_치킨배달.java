package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B15686_치킨배달 {
	static int N, M;
	static int[][] map;
	static ArrayList<Point> home;
	static ArrayList<Point> bbq;
	static Point[] output;
	static int min;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		home = new ArrayList<>();
		bbq = new ArrayList<>();
		output = new Point[M];
		min = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) home.add(new Point(i,j));
				if(map[i][j] == 2) bbq.add(new Point(i,j));
			}
		}
		comb(0,0);
		
		System.out.println(min);
	}
	static void comb(int idx, int cnt) {
		if(cnt == M) {
			int sum = 0;
			// 각각의 집과 가장 가까운 치킨집
			for(Point h : home) {
				int min = Integer.MAX_VALUE;
				for(Point chi : output) {	// 집과 각각 치킨집 중제일 가까운 거리 갱신
					int dist = Math.abs(h.i - chi.i) + Math.abs(h.j - chi.j);
					min = Math.min(dist, min);	//최소 갱신
				}
				sum += min;
			}
			min = Math.min(sum, min);
			
			return;
		}
		if(idx == bbq.size()) {
			return;
		}
		
		output[cnt] = bbq.get(idx);
		comb(idx+1, cnt+1);
		comb(idx+1, cnt);
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
