package DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class B2531_회전초밥 {
	static int N, d, k, c;
	static int[] arr;
	static int[] D;
	static int max;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		D = new int[N+1];
		max = Integer.MIN_VALUE;
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		for(int i=0; i<N; i++) {
			Set<Integer> set = new HashSet<>();
			// i : 시작 초밥, cnt : 먹은 횟수, set : 초밥 리스트
			dfs(i, 0, set);
		}
		
		System.out.println(max);
	}
	static void dfs(int idx, int cnt, Set<Integer> set) {
		if(cnt == k) {	//연속해서 먹는 접시 수 k
			
			System.out.println(set.toString());
			set.add(c);	// 쿠폰 번호

			max = Math.max(max, set.size());
			return;
		}
		
		if(idx == N) {	// 맨끝 -> 뒤로
			idx = 0;
		}
		set.add(arr[idx]); // 초밥 리스트 (중복 제거)
		if(D[cnt+1] <= set.size()) {
			D[cnt+1] = set.size();
			dfs(idx+1, cnt+1, set);
		}


	}
}
