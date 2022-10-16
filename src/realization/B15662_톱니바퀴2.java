package realization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B15662_톱니바퀴2 {
	static ArrayList<Character>[] deque;
	static int T, K;
	static int N, D;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		deque = new ArrayList[T+1];
		for(int i=1; i<=T; i++) {
			deque[i] = new ArrayList<>();
		}
		for(int i=1; i<=T; i++) {
			char[] arr = br.readLine().toCharArray();
			for(int j=0; j<arr.length; j++) {
				deque[i].add(arr[j]);
			}
		}
		K = Integer.parseInt(br.readLine());
		for(int k=0; k<K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//돌릴 톱니
			D = Integer.parseInt(st.nextToken());	// 방향
			
			//오른쪽에 거 확인해
			if(N+1 <= T) {
				char meR = deque[N].get(2);
				char nextL = deque[N+1].get(6);
				if(meR != nextL) { // 두개 다르면 오른쪽 거 돌려
					dfs(N+1, 'R', D * -1);
				}
			}
			// 왼쪽 것 확인해
			if(N-1 >= 1) {
				char meL = deque[N].get(6);
				char nextR = deque[N-1].get(2);
				if(meL != nextR) {
					dfs(N-1, 'L', D * -1);
				}
			}
			//내거 돌려
			rotate(N, D);
		}
		int cnt = 0;
		for(int t=1; t<= T; t++) {
			if(deque[t].get(0) == '1') {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
	static void dfs(int n, char look, int d) {

		
		if(n+1 <= T && look == 'R') {	//오른쪽 것이 있음.
			char meR = deque[n].get(2);
			char nextL = deque[n+1].get(6);
			
			if(meR != nextL) {
				
				//다음거 봐
				dfs(n+1, 'R', d * -1);
			}
		}
		if(n-1 >= 1 && look == 'L') {	// 왼쪽 것이
			char meL = deque[n].get(6);
			char nextR = deque[n-1].get(2);
			
			if(meL != nextR) {
				//다음거 봐
				dfs(n-1, 'L', d * -1);
			}
		}
		//내꺼 돌리고
		rotate(n, d);

	}
	static void rotate(int n, int d) {
		if(d == 1) {	//시계방향
			char num = deque[n].remove(7);	//마지막거 빼기
			deque[n].add(0, num);	//마지막거 맨앞에 넣기
			
		}else {
			char num = deque[n].remove(0);	//맨앞에거 빼기
			deque[n].add(num);	//마지막거 맨뒤에 넣기
		}
	}
}
