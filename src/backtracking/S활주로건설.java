package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S활주로건설 {
	static int N, X;
	static int[][] map, map2;
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
			N = Integer.parseInt(st.nextToken());	// 배열 크기
			X = Integer.parseInt(st.nextToken());	// 경사로 밑변 길이
			map = new int[N][N];
			map2 = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}// end Input

		}
	}
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	static int process() {
		int count = 0;
		for(int i=0; i<N; i++) {
			if(makeRoad(map[i])) count++;
			if(makeRoad(map2[i])) count++;
		}
		
		return count;
	}
	private static boolean makeRoad(int[] road) {
		int beforeHeight = road[0], size = 0;
		int j = 0;
		
		while( j < N) {
			if(beforeHeight == road[j]) {	//동일 높이
				size++;
				j++;
			}else if(beforeHeight+1 == road[j]) {	// 이전높이보다 1 높음 : 오르막 경사로 설치 체크
				if(size < X) return false;	// X길이 미만이면 활주로 건설 불가.
			
				beforeHeight++;
				size = 1;
				j++;
			}else if(beforeHeight-1 == road[j]) {	// 이전 높이보다 1 작음 :
				int count = 0;
				for(int k=j; j<N; j++) {
					if(road[k] != beforeHeight-1) return false;
					if(++count == X) break;
				}
				
				if(count < X) return false;
				
				beforeHeight--;
				j += X;
				size = 0;
				
			}else {	//높이가 2이상 차이
				return false;
			}
		}
		return true;
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
