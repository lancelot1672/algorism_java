package DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class B2531_회전초밥2 {
	static int N, d, k, c;
	static int[] arr;
	static int[] bab;
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
		bab = new int[d+1];	//초밥의 가짓수
		D = new int[N+1];
		max = Integer.MIN_VALUE;
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		//처음 k개
		int cnt = 0;
		for(int i=0; i<k; i++) {
			if(bab[arr[i]] == 0) {	//처음먹는 초밥
				cnt++;
			}
			bab[arr[i]]++;
		}
		if(bab[c] == 0) {	//쿠폰이 미 포함되어있냐?
			max = Math.max(cnt+1, max);
		}
		
		
		for(int i=1; i<N; i++) {
			// i-1 빼주자
			if(bab[arr[i-1]] == 1) {
				cnt--;
			}
			bab[arr[i-1]]--;
			
			//뒤에거 더할건데
			if(i + k > N) {	// 넘어가면
				if(bab[arr[(i+k-1) - N]] == 0) { //새로운 초밥~
					cnt++;
				}
				bab[arr[(i+k-1) - N]]++;
				
			}else {
				if(bab[arr[i+(k-1)]] == 0) {	//새로운 초밥~
					cnt++;
				}
				bab[arr[i+(k-1)]]++;
			}
			
			//쿠폰 초밥 안먹었니?
			if(bab[c] == 0) {
				max = Math.max(cnt+1, max);
			}else {
				max = Math.max(cnt, max);
			}
			//System.out.println(Arrays.toString(bab) + ", cnt : " + cnt);
			
		}// end for
		
		System.out.println(max);
	}

}
