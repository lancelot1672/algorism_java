package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class S프로세서 {
	static int N;
	static int[][] map;
	static ArrayList<Point> list;
	static int max_connect, min_cnt;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			list = new ArrayList<>();
			max_connect = Integer.MIN_VALUE;
			min_cnt = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1 && i != 0 && j != 0 && i != N-1 && j != N-1) {
						//끝에 있는건 안셈
						list.add(new Point(i, j));
					}
				}
			} // end input
			dfs(0,0,0);
			
			System.out.println("#" + tc + " " + min_cnt);
		}
		
	}
	static void dfs(int idx, int connect, int sum) {
		if(idx == list.size()) {	// 다 갔을 때
			if(max_connect < connect) {
				//더 많이 연결됬나??
				max_connect = connect;
				min_cnt = sum;	// 상관없이 덮어버림
				
			}else if(max_connect == connect) {
				//연결 갯수가 같으면 간선 더 작은거 써야지??
				min_cnt = Math.min(sum, min_cnt);
			}
			return;
		}
		Point now = list.get(idx);
		for(int d=0; d<4; d++) {
			//4방향으로 그려볼거양~
			if(check(now.i, now.j, d)) {
				int cnt = draw(now.i, now.j, d, 2);
				dfs(idx+1, connect+1, sum + cnt);
				draw(now.i, now.j, d, 0);	//rollback
			}else {	// 안돼도 가봐야지
				dfs(idx+1, connect, sum);
			}
		}
	}
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	static boolean check(int i, int j, int d) {
		int nowi = i;
		int nowj = j;
		
		while(true) {
			int nexti = nowi + di[d];
			int nextj = nowj + dj[d];
			
			if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= N) break;
			
			if(map[nexti][nextj] != 0) return false;
			
			nowi = nexti;
			nowj = nextj;
		}
		
		return true;
	}
	static int draw(int i, int j, int d, int num) {
		int nowi = i;
		int nowj = j;
		int cnt = 0;
		while(true) {
			int nexti = nowi + di[d];
			int nextj = nowj + dj[d];
			if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= N) break;
			
			//이미 체크 완료 - 그리기만 하면 됌
			map[nexti][nextj] = num;
			cnt++;
			nowi = nexti;
			nowj = nextj;
			
		}
		return cnt;
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
