package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B17406_배열돌리기4 {
	static int N, M;
	static int K;
	static int[][] origin;
	static boolean[] selected;
	static ArrayList<Oper> list;
	static Oper[] output;
	static int min;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		origin = new int[N+1][M+1];
		list = new ArrayList<>();
		selected = new boolean[K];
		output = new Oper[K];
		min = Integer.MAX_VALUE;
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int r =  Integer.parseInt(st.nextToken());
			int c =  Integer.parseInt(st.nextToken());
			int s =  Integer.parseInt(st.nextToken());
			
			list.add(new Oper(r,c,s));
		}
		perm(0);
		
		System.out.println(min);
	}
	static void rotate(int r, int c, int s) {
		// 돌려돌려 배열!
		int temp = map[r-s][c-s];
		
		//맨 왼쪽 줄
		for(int i=r-s; i<r+s; i++)
			map[i][c-s] = map[i+1][c-s];
		
		//아래
		for(int j=c-s; j<c+s; j++) {
			map[r+s][j] = map[r+s][j+1];
		}
		//맨 오른쪽
		for(int i=r+s; i>r-s; i--)
			map[i][c+s] = map[i-1][c+s];
		
		//맨 위
		for(int j=c+s; j> c-s; j--)
			map[r-s][j] = map[r-s][j-1];
		
		map[r-s][c-s+1] = temp;
	}
	static int[][] map;
	static void perm(int cnt) {
		if(cnt == K) {
			map = deepcopy(origin);
			
			for(Oper oper : output) {	// 순서에 맞춰 실행
				for(int s=oper.s; s>0; s--) {
					rotate(oper.r, oper.c, s);
				}
				//print(map);
			}
			
	
			// 임시 최소
			int temp_min = Integer.MAX_VALUE;
			
			for(int i=1; i<= N; i++) {
				int sum = 0;
				for(int j=1; j<=M; j++) {
					sum += map[i][j];
				}
				temp_min = Math.min(temp_min, sum);
			}
			
			min = Math.min(min, temp_min);
			return;
		}
		
		for(int i=0; i<K; i++) {
			if(selected[i]) continue;
			
			selected[i] = true;
			output[cnt] = list.get(i);
			perm(cnt+1);
			selected[i] = false;
		}
	}
	static int[][] deepcopy(int[][] origin){
		int[][] copy = new int[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<= M; j++){
				copy[i][j] = origin[i][j];
			}
		}
		return copy;
		
	}
	static void print(int[][] map) {
		System.out.println("---------------------------");
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	static class Oper{
		int r;
		int c;
		int s;
		public Oper(int r, int c, int s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}
		@Override
		public String toString() {
			return "Oper [r=" + r + ", c=" + c + ", s=" + s + "]";
		}
		
		
	}
}
