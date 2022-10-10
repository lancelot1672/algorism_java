package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B6987월드컵 {
	static boolean Can;
	static Team[] team;
	static int[] TeamA = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
	static int[] TeamB = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 4;
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=4; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			team = new Team[6];
			Can = false;
			for(int i=0; i<6; i++) {
				int w = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int l = Integer.parseInt(st.nextToken());
				
				team[i] = new Team(w,d,l);
				
			}// end Input
		
			dfs(0);
			if(Can) {
				sb.append("1 ");
			}else {
				sb.append("0 ");
			}

		}
		System.out.println(sb.toString());
	}
	static boolean check() {
		
		for(Team t : team) {
			if(t.win != 0 || t.lose != 0 || t.draw != 0) {
				return false;
			}
		}
		return true;
	}
	static void dfs(int idx) {
		if(idx == TeamA.length) {
			if(check()) {
				Can = true;
			}
			return;
		}
		Team A = team[TeamA[idx]];
		Team B = team[TeamB[idx]];
		
		//TeamA의 승이 있고 TeamB의 패가 있으면
		if(A.win > 0 && B.lose > 0) {
			A.win--;
			B.lose--;
			
			dfs(idx+1);
			
			A.win++;
			B.lose++;
		}
		//TeamA의 패가 있고 TeamB의 승이 있으면
		if(A.lose > 0 && B.win > 0) {
			A.lose--;
			B.win--;
			
			dfs(idx+1);
			
			A.lose++;
			B.win++;
		}
		
		//둘 다 무승부가 있으면
		if(A.draw > 0 && B.draw > 0) {
			A.draw--;
			B.draw--;
			
			dfs(idx+1);
			
			A.draw++;
			B.draw++;
		}
	}
	static class Team{
		int win;
		int draw;
		int lose;
		public Team(int win, int draw, int lose) {
			this.win = win;
			this.draw = draw;
			this.lose = lose;
		}
		
		
	}
}
