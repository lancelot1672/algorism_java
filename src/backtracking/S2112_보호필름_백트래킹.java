package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S2112_보호필름_백트래킹 {
	static int D, W, K;
	static int[][] film;
	static boolean[] selected;
	static int min;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			min = Integer.MAX_VALUE;
			
			film = new int[D][W];
			selected = new boolean[D];
			
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dfs(0, 0);
			System.out.println("#" + tc + " " + min);
		}
	}
	static void dfs(int idx, int cnt) {
		if(idx == D) {
		
			for(int j=0; j<W; j++) { // 오른쪽으로 하나씩 가면서 검사
				if(!check(film, j)) {
					return;
				}
			}
//			System.out.println(cnt);
//			print(film);
			min = Math.min(min, cnt);
			return;
		}
		int[] origin = new int[W];
		for(int j=0; j<W; j++) {
			origin[j] = film[idx][j];
		}
		//안뽑고가
		dfs(idx+1, cnt);
		
		// A로 바꿔
		for(int j=0; j<W; j++) {
			film[idx][j] = 0;
		}
		dfs(idx+1, cnt+1);
		for(int j=0; j<W; j++) {
			film[idx][j] = origin[j];
		}
		
		// B로 바꿔
		for(int j=0; j<W; j++) {
			film[idx][j] = 1;
		}
		dfs(idx+1, cnt+1);
		for(int j=0; j<W; j++) {
			film[idx][j] = origin[j];
		}
	}
	static boolean check(int[][] map, int j) {
		int Acnt = 0;
		int Bcnt = 0;
		
		for(int i=0; i<D; i++) {
			if(map[i][j] == 0) {	//A
				Acnt++;
				Bcnt = 0;
				if(Acnt >= K) return true;
			}
			else {	//B
				Bcnt++;
				Acnt = 0;
				if(Bcnt >= K) return true;
			}
			
		}		
		return false;
	}
	static void print(int[][] map) {
		System.out.println("-----------------");
		for(int i=0; i<D; i++) {
			for(int j=0; j<W; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
