package bf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class B1025_제곱수찾기 {
	static int N, M;
	static String[][] map;
	static int max;
	static Set<Integer> set = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new String[N][M];
		max = Integer.MIN_VALUE;
		
		for(int i=0; i<N; i++) {
			char[] arr = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				map[i][j] = "" + arr[j];
			}
		}// end Input
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				for(int d=0; d<4; d++) {
					//i 기준 더 가는 경우
					for(int x=1; x<N; x++) {
						forward(i, j, d, x, true);
					}
					// j 기준 더 가는 경우
					for(int x=1; x<M; x++) {
						forward(i, j, d, x, false);
					}
				}
			}
		}
		//System.out.println(set.toString());
		//하나씩 뽑아서 완전 제곱수 검사.
		for(int num : set) {
			check(num);
		}
		if(N == 1 && M == 1) {
			System.out.println(map[0][0]);
			return;
		}
		if(max == Integer.MIN_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(max);
		}

		
	}
	static void check(int num) {
		// root 때려서 정수 아니면 짤
		if(Math.sqrt(num) % 1 == 0) {
			max = Math.max(max, num);
		}
	}
	static int[] di = {0, 1, 1, -1};
	static int[] dj = {1, 1, 0, +1};
	
	static void forward(int i, int j, int d, int x, boolean ij) {
		int nowi = i;
		int nowj = j;
		int nexti = 0;
		int nextj = 0;
		String num = map[i][j];
		set.add(Integer.parseInt(num));
		while(true) {
			if(ij) {
				nexti = nowi + di[d] * x;
				nextj = nowj + dj[d];
			}else {
				nexti = nowi + di[d];
				nextj = nowj + dj[d] * x;
			}
			
			if(nexti < 0 || nexti >=N || nextj < 0 || nextj >= M) {
				break;
			}
			num += map[nexti][nextj];
			set.add(Integer.parseInt(num));
			
			StringBuilder sb = new StringBuilder();
			sb.append(num);
			//뒤집어서도 넣어봐야함
			set.add(Integer.parseInt(sb.reverse().toString()));
			
			nowi = nexti;
			nowj = nextj;
		}
	}
}
