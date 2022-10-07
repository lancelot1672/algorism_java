package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S활주로건설 {
	static int N, X;
	static int[][] map;
	static boolean[][] visited;
	static boolean[] visitedi;
	static boolean[] visitedj;
	static int total;
	static int max;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			visitedi = new boolean[N];
			visitedj = new boolean[N];
			total = 0;
			max = Integer.MIN_VALUE;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}// end Input
			
			// 한줄 동일 숫자 있으면 
			
			for(int i=0; i<N; i++) {		//옆으로 확인 = i
				int test = map[i][0];
				int cnt = 0;
				for(int j=0; j<N; j++) {
					if(map[i][j] == test) {
						cnt++;
					}
				}
				if(cnt == N) {
					total++;
					visitedi [ i ]  = true;
				}
			}
			for(int j=0; j<N; j++) {		// 아래로 확인 = j
				int test = map[0][j];
				int cnt = 0;
				for(int i=0; i<N; i++) {
					if(map[i][j] == test) {
						cnt++;
					}
				}
				if(cnt == N) {
					total++;
					visitedj[j] = true;
				}
			}
			System.out.println("#" + tc + " " + max);
		}
	}
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	static void dfs() {
		
	}
	static void print() {

		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
