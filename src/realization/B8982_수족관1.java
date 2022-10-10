package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B8982_수족관1 {
	static int N, K;
	static int[][] input;
	static int[] water;
	static Hole[] holes;
	static int size;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	//꼭짓점 갯수
		input = new int[N][2];
		
		StringTokenizer st;
		
		for(int n=0;n<N; n++) {
			st = new StringTokenizer(br.readLine());
			input[n][0] = Integer.parseInt(st.nextToken());
			input[n][1] = Integer.parseInt(st.nextToken());
		}
		size = input[N-1][0];	// 수족관 사이즈 : 8
		water = new int[size];
		
		//처음
		water[0] = Math.abs(input[0][1] - input[1][1]);
		int beforeWater = water[0];
		
		//네모난거임
		if(N == 4) {
			Arrays.fill(water, beforeWater);
		}else {
			//임시배열
			int[] temp = new int[size+1];
			for(int i=1; i<size-1; i++) {
				int index = input[i*2][0];
				temp[index] = input[i*2][1] - input[i*2 + 1][1];
			}
			
			for(int i=1; i<size; i++) {
				if(temp[i] != 0) {
					water[i] = beforeWater - temp[i];
					beforeWater = water[i];
				}else {
					water[i] = beforeWater;
				}
			}
		}

		//끝
		water[size-1] = Math.abs(input[N-2][1] - input[N-1][1]); 
		//System.out.println(Arrays.toString(water));
		
		int sum = 0;
		int[] copy = new int[size];
		for(int i=0; i<size; i++) {
			copy[i] = water[i];
		}
		K = Integer.parseInt(br.readLine());
		holes = new Hole[K];
		
		for(int k=0; k <K; k++) {
			st = new StringTokenizer(br.readLine());
			int startj = Integer.parseInt(st.nextToken());
			int starti = Integer.parseInt(st.nextToken());
			int endj = Integer.parseInt(st.nextToken());
			int endi = Integer.parseInt(st.nextToken());
			
			holes[k] = new Hole(starti, startj);
		}
		Arrays.sort(holes);
		// i 가 낮은것부터 == 맨 위 홀
		int line = holes[0].i; 	//기준 선
		for(int i=0; i<K; i++) {
			Hole h = holes[i];
			
			int index = h.j;
			
			// 왼쪽
			for(int j=index; j>= 0; j--) {
				//조건
				if(water[j] < h.i) break;
				copy[j] -= line;
			}
			//오른쪽
			for(int j=index+1; j<size; j++) {
				if(water[j] < h.i) break;
				copy[j] -= line;
			}
			
			if(i != K-1) {			
				line = Math.abs(line - holes[i+1].i);
			}
			//System.out.println(Arrays.toString(copy));
		}
		
		for(int n : copy) {
			sum += n;
		}
		System.out.println(sum);
	}
	static class Hole implements Comparable<Hole>{
		int i;
		int j;
		public Hole(int i, int j) {
			this.i = i;
			this.j = j;
		}
		@Override
		public int compareTo(Hole o) {
			return this.i - o.i;
		}
		
	}
}
