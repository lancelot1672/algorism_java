package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class S_보물상자비밀번호 {
	static int N, K;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int stringSize = N / 4;
			Queue<Character> q = new LinkedList<>();
			Queue<Character> q2 = new LinkedList<>();
			Set<Long> set = new HashSet<>();
			//Set<String> set = new HashSet<>();
			char[] arr = br.readLine().toCharArray();
			for(int i=0; i<N; i++) {
				q.add(arr[i]);
			}
			for(int k=0; k< stringSize; k++) {
				//1사이클
				for(int n=0; n< 4; n++) {
					StringBuilder sb = new StringBuilder();
					for(int i=0; i < stringSize; i++) {	// 12/4 만큼의 길이의 문자열
						char c = q.poll();	// 큐에서 하나꺼내
						sb.append(c);	//문자열 만들기
						q2.add(c);	//맨뒤에 다시 넣어주기
					}
					set.add(Long.parseLong(sb.toString(), 16));
					//set.add(sb.toString());
				}
				q = q2;
				//rotate
				char c = q.poll();
				q.add(c);
			}//end k

			
			Long[] array = set.toArray(new Long[0]);
			Arrays.sort(array);

			long ans = 0;
			int idx = array.length - K;
			
			System.out.println("#" + tc + " " + array[idx]);
		}
	}
	
}
