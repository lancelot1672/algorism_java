package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S2112_보호필름 {
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
			subset(0);
			System.out.println("#" + tc + " " + min);
		}
	}
	static void subset(int idx) {
		if(idx == D) {
			int[][] copy = deepcopy(film);
			func(0, copy, selected, 0);
			return;
		}
		
		selected[idx] = true;
		subset(idx+1);
		selected[idx] = false;
		subset(idx+1);
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
	static void func(int idx, int[][] map, boolean[] select, int cnt) {
		if(idx == D) {
			for(int j=0; j<W; j++) { // 오른쪽으로 하나씩 가면서 검사
				if(!check(map, j)) {
					return;
				}
			}
			//print(map);
			min = Math.min(min, cnt);
			return;
		}
		
		if(select[idx]) {	//여기 변경해볼거임.
			for(int i=0; i<W; i++) {	//A로 변경
				map[idx][i] = 0;
			}
			func(idx+1, map, select, cnt+1);
			
			for(int i=0; i<W; i++) {	//B로 변경
				map[idx][i] = 1;
			}
			func(idx+1, map, select, cnt+1);
		}else {
			func(idx+1, map, select, cnt);
		}
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
	private static int[][] deepcopy(int[][] film2) {
		int[][] copy = new int[D][W];
		
		for(int i=0; i<D; i++) {
			for(int j=0; j<W; j++) {
				copy[i][j] = film2[i][j];
			}
		}
		return copy;
	}
}
