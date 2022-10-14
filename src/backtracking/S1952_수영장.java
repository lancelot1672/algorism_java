package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1952_수영장 {
	static int day, month, three, year;
	static int[] plan;
	static int ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<= T; tc++) {
			plan = new int[12];
			StringTokenizer st = new StringTokenizer(br.readLine());
			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			three = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			ans = year;	// 연간 회원권 썼을때 가격
			dfs(0, 0);
			
			System.out.println("#" + tc+ " " + ans);
		}
	}
	static void dfs(int idx, int sum) {
		if(sum >= ans) {
			return;
		}
		if(idx == 12) {	// 끝까지 왔을 때.
			ans = Math.min(ans, sum);
			return;
		}
		//1일 권 다쓰기
		dfs(idx+1, sum + day * plan[idx]);
		//1월 권 쓰기
		dfs(idx+1, sum + month);
		
		//3달권 쓰기
		if(idx <= 9) {
			dfs(idx+3, sum + three);
		}
		
	}
}
