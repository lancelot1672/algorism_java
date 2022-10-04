package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class S4008_숫자만들기 {
	static int N;
	static int[] oper;
	static ArrayList<Character> operList;
	static boolean[] selected;
	static char[] output;
	static int[] arr;
	
	static int max;
	static int min;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			oper = new int[4];
			arr = new int[N];
			operList = new ArrayList<>();
			selected = new boolean[N-1];
			output = new char[N-1];
			
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				oper[i] = Integer.parseInt(st.nextToken());
			}
			for(int i=0; i<4; i++) {
				for(int j=0; j<oper[i]; j++) {
					switch (i) {
					case 0:
						operList.add('+');
						break;
					case 1:
						operList.add('-');
						break;
					case 2:
						operList.add('*');
						break;
					case 3:
						operList.add('/');
						break;
					}
				}
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			//input End
			//perm(0);
			dfs(0, arr[0]);
			
			System.out.println("#" + tc + " " + (max - min));
		}
	}
	static void dfs(int idx, int sum) {
		if(idx == N-1) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(oper[i] > 0) {
				oper[i]--;
				switch (i) {
				case 0:
					dfs(idx+1, sum + arr[idx+1]);
					break;
				case 1:
					dfs(idx+1, sum - arr[idx+1]);
					break;
				case 2:
					dfs(idx+1, sum * arr[idx+1]);
					break;
				case 3:
					dfs(idx+1, sum / arr[idx+1]);
					break;
				}
				oper[i]++;
			}
		}
	}
	static void perm(int idx) {
		if(idx == N-1) {
			int[] operCnt = new int[4];
			for(int i=0; i<4; i++) {
				operCnt[i] = oper[i];
			}
			int sum = arr[0];	// 첫 번째 숫자 집어 넣기
			for(int i=1; i<N; i++) {
				switch (output[i-1]) {
				case '+':
					sum += arr[i];
					oper[0]--;
					break;
				case '-':
					sum -= arr[i];
					oper[1]--;
					break;
				case '/':
					sum /= arr[i];
					oper[3]--;
					break;
				case '*':
					sum *= arr[i];
					oper[2]--;
					break;
				}
				
			}
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		
		for(int i=0; i<N-1; i++) {
			if(!selected[i]) {
				selected[i] = true;
				output[idx] = operList.get(i);
				perm(idx+1);
				selected[i] = false;
			}
		}
	}
	
}
