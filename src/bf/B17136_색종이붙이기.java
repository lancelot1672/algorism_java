package bf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17136_색종이붙이기 {
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	static int[] paper = {0, 5, 5, 5, 5, 5};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[10][10];
		for(int i=0; i<10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}	// end Input
		
		int starti = 0;
		int startj = 0;

		// 제일 큰 색종이 덮어
		dfs(0,0, 0);
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
	}
	static void dfs(int i, int j, int cnt) {
		if(j >= 10) {
			j = 0;
			i++;
		}
		if(i == 10) {
			min = Math.min(min, cnt);
			return;
		}
		if(map[i][j] == 0) {
			// 다음 거 해
			dfs(i , j+1, cnt);
		}
		for(int d=5; d>=1; d--) {	// 색종이 몇칸? // 그리고 제일 큰거 저장
			if(check(i,j,d)) {
				//System.out.println("i : " + i + ", j : " + j + ", can :  " + d);
				// 색종이 붙여보고
				if(paper[d] == 0) return;
				for(int a=i; a< i + d; a++) {
					for(int b=j; b< j + d; b++) {
						map[a][b] = 0;
					}
				}
				paper[d]--;
				dfs(i, j + d, cnt+1);
				
				// 다시 돌려놔
				for(int a=i; a< i + d; a++) {
					for(int b=j; b< j + d; b++) {
						map[a][b] = 1;
					}
				}
				paper[d]++;
			}
		}
	}
	static void print() {
		System.out.println();
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	static boolean check(int starti, int startj, int d) {
		for(int i=starti; i<starti + d; i++) {
			for(int j=startj; j<startj + d; j++) {
				if(i >= 10 || j >= 10) {
					return false;
				}
				if(map[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
}
