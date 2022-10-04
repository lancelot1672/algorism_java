package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S_벽돌깨기_DFS {
	static int N, W, H;
	static int[][] map;
	static int total;
	static int min;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			output = new int[N];	// 구슬
			min = Integer.MAX_VALUE;
			total = 0;
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] != 0) {
						total++;
					}
				}
			}//end Input
			perm(0);
			System.out.println("#" + tc + " " + min);
		}
	}
	//중복순열
	static int[] output;
	static int[][] copy;
	static void perm(int idx) {
		if(idx == N) {
			int totalCnt = 0;	// 총 깬 벽돌 갯수
			copy = deepcopy(map);
			
			for(int j : output) {
				for(int i=0; i<H; i++) {
					if(copy[i][j] != 0) {
						// 1~9
						//벽돌 깨기
						totalCnt += broke(i, j, copy[i][j]);
						break;
					}
				}
				//내리기
				down();
			}
			//남은 벽돌 갯수
			int temp = total - totalCnt;
			min = Math.min(temp, min);
			
			return;
		}
		for(int i=0; i<W; i++) {
			output[idx] = i;
			perm(idx+1);
		}
	}
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, -1, 1};

	static int broke(int starti, int startj, int num) {
		int cnt = 0;
		//자기자신 때리기
		copy[starti][startj] = 0;
		cnt++;
		for(int i=0; i<num; i++) {
			for(int d=0; d<4; d++) {
				int nexti = starti + di[d] * i;
				int nextj = startj + dj[d] * i;
				
				if(nexti < 0 || nexti >= H || nextj < 0 || nextj >= W) {
					continue;
				}
				// 숫자를 만나면 또 부시러가야댐
				if(copy[nexti][nextj] != 0) {
					cnt += broke(nexti, nextj, copy[nexti][nextj]);
				}
			}

		}
		return cnt;
	}
	static void down() {
		for(int j=0; j<W; j++) {
			int zero = 0;
			for(int i=H-1; i>= 0; i--) {
				if(copy[i][j] == 0) {
					zero++;
				}else {
					if(zero != 0) {
						//벽돌 나오면 zero만큼 내려
						copy[i + zero][j] = copy[i][j];
						copy[i][j] = 0;
					}
				}
			}
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
	static void print(int[][] arr) {
		System.out.println("--------------------------");
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
