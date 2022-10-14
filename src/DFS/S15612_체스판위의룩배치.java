package DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S15612_체스판위의룩배치 {
	static int N;
	static char[][] map;
	static String ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = 8;
			map = new char[N][];
			ans = "no";
			for(int i=0; i<N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			dfs(0);
			System.out.println("#" + tc + " " + ans);
		}
	}
	static void dfs(int idx) {
		if(idx == N) {	//끝까지 온 경우
			ans = "yes";
			return;
		}
		for(int j=0; j<N; j++) {
			if(map[idx][j] == 'O') {
				//열 체크
				if(!Rcheck(idx)) return;
				//행 체크
				if(!Ccheck(j)) return;
				
				dfs(idx+1);
			}
		}
	}
	static boolean Rcheck(int nowj) {
		int cnt = 0;
		//같은 행에 룩이 2개이상이면 return false;
		for(int i=0; i<N; i++) {
			if(map[i][nowj] == 'O') cnt++;
			
			if(cnt >= 2) return false;
		}
		return true;
	}
	static boolean Ccheck(int nowi) {
		int cnt = 0;
		//같은 열에 룩이 2개이상이면 return false;
		for(int i=0; i<N; i++) {
			if(map[nowi][i] == 'O') cnt++;
			
			if(cnt >= 2) return false;
		}
		return true;
	}
}
