package DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S5656_벽돌깨기 {
	static int N, W, H;
	static int[][] origin;

	static int[] output;
	static int min;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			origin = new int[H][W];
			output = new int[N];
			min = Integer.MAX_VALUE;
			
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					origin[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 중복순열
			perm(0);
			System.out.println("#" + tc + " " + min);
		}
	}
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static int[][] map;
	static void dfs(int starti, int startj, int s) {
		int nowi = starti;
		int nowj = startj;
		
		//자기 것을 0로 바꾸고 cnt--;
		map[nowi][nowj] = 0;
		
		for(int ss=1; ss<s; ss++) {
			for(int d=0; d<4; d++) {	// 4방향 탐색
				int nexti = nowi + di[d] * ss;
				int nextj = nowj + dj[d] * ss;
				
				if(nexti < 0 || nexti >= H || nextj < 0 || nextj >= W) {
					continue;
				}
				if(map[nexti][nextj] != 0) {
					//다음 벽돌
					dfs(nexti, nextj, map[nexti][nextj]);
				}
			}
		}
	}
	static int total;
	static void perm(int idx) {
		if(idx == N) {
			//System.out.println("-----------------시작-----------------");
			//System.out.println(Arrays.toString(output));
			//deepcopy
			map = deepcopy(origin);

			//int[] output = {2, 2, 6};
			for(int j : output) {
				for(int i=0; i<H; i++) {	//위에서 부터 탐색
					if(map[i][j] != 0) {	// 벽돌 만남.
						dfs(i, j, map[i][j]);
						drop();
						break;
					}
				}
			}
			//남은 벽돌의 갯수
			total = getArrayCnt();
			min = Math.min(total, min);
			return;
		}
		for(int i=0; i<W; i++) {
			output[idx] = i;
			perm(idx+1);
		}
	}
	static void drop() {
		//이제 벽돌 내리기!
		for(int j=0; j<W; j++) {
			int zeroCnt = 0;
			for(int i=H-1; i>=0; i--) {
				if(map[i][j] == 0) {
					zeroCnt++;
				}else {
					map[i + zeroCnt][j] = map[i][j];
					if(zeroCnt != 0) {
						map[i][j] = 0;
					}

				}
			}
		}
	}
	static int getArrayCnt() {
		int cnt = 0;
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(map[i][j] != 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	static void print() {
		System.out.println("--------------------");
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	static int[][] deepcopy(int[][] origin){
		int[][] copy = new int[H][W];
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				copy[i][j] = origin[i][j];
			}
		}
		return copy;
	}
}
