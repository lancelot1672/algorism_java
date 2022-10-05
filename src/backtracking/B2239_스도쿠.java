package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B2239_스도쿠 {
	static int[][] map;
	static int C;
	static ArrayList<Point> list;
	static int[][] ans;
	static boolean flag;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		map = new int[9][9];
		ans = new int[9][9];
		list = new ArrayList<>();
		C = 0;
		flag = true;
		for(int i=0; i<9; i++) {
			char[] input = br.readLine().toCharArray();
			for(int j=0;j<9;j++) {
				map[i][j] = input[j] - '0';
				if(map[i][j] == 0) {		// 채워야하는 공간 갯수
					C++;
					list.add(new Point(i, j));
				}
			}
		}// Input End
		dfs(0);

	}
	static void dfs(int idx) {
		if(!flag) {
			return;
		}
		if(idx == list.size()) {
			//81자리 자리가 제일 작은 경우........????
			//앞에서 부터 했으면 제일 작은수가 되지 않나..
			//맨뒤자리 비교해
			print();
			flag = false;
			return;
		}
		
		Point p = list.get(idx);
		for(int n=1; n<=9; n++) {		//숫자 하나씩 넣어봐
			// 세조건에 부합 하면 일단 넣어봐
			if(!checkS(p.i, p.j, n)) {
				continue;
			}
			if(!checkH(p.j, n)) {
				continue;
			}
			if(!checkW(p.i, n)) {
				continue;
			}
			map[p.i][p.j] = n;
			dfs(idx+1);	// 가보고
			map[p.i][p.j] = 0;	// 다시 빼
		}
	}
	static boolean checkH(int j, int checkNum) {		//세로 검사
		
		for(int i=0; i<9; i++) {
			if(map[ i ][ j ] == checkNum) {
				return false;
			}
		}
		return true;
	}
	static boolean checkW(int i, int checkNum) {		// 가로 검사
		for(int j=0; j<9; j++) {
			if(map[ i ][ j ] == checkNum) {
				return false;
			}
		}
		return true;
	}
	static boolean checkS(int ci, int cj, int checkNum) {
		for(int i=ci/3*3; i < ci/3*3 + 3; i++) {
			for(int j=cj/3*3; j < cj/3*3 + 3; j++) {
				if(map[i][j] == checkNum) {
					return false;
				}
			}
		}
		return true;
	}
	static void print() {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	static class Point{
		int i;
		int j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
	}
	static void deepcopy(int[][] origin) {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				ans[i][j] = origin[i][j];
			}
			
		}
	}
}
