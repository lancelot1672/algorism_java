package backtracking;

import java.util.ArrayList;
import java.util.Scanner;

public class B2580_스도쿠 {
	static int[][] map;
	static int[] num = {1,2,3,4,5,6,7,8,9};
	static int arrIdx = 0;
	static boolean flag = false;
	static ArrayList<Point> points = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new int[9][9];
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 0) {
					points.add(new Point(i,j));
				}
			}
		} // end input
		dfs(0);	// 0번째부터 시작
	}
	static void dfs(int idx) {
		if(idx == points.size()) {
			for(int i=0; i<9; i++) {
				int sum = 0;
				for(int j=0; j<9; j++) {
					sum += map[i][j];
				}
				if(sum != 45) {
					return;
				}
			}
			if(!flag) {
				print();
				flag = true;
			}
			return;
		}
		Point now = points.get(idx);
		
		for(int i=1; i<=9;  i++) {
			if(!checkRow(now, i)) {
				continue;
			}
			if(!checkCol(now, i)) {
				continue;
			}
			if(!checkSquare(now, i)) {
				continue;
			}
			map[now.i][now.j] = i;

			dfs(idx+1);
			map[now.i][now.j] = 0;		//백트래킹
		}
	}
	static  boolean checkRow(Point now, int checkNum) {  // 세로
		for(int i=0; i<9; i++) {
			if(map[i][now.j] == checkNum) {
				return false;
			}
		}
		return true;
	}
	static boolean checkCol(Point now, int checkNum) { // 가로
		for(int j=0; j<9; j++) {
			if(map[now.i][j] == checkNum) {
				return false;
			}
		}
		return true;
	}
	static boolean checkSquare(Point now, int checkNum) {	//사각형
		int starti = now.i / 3 * 3;
		int startj = now.j / 3 * 3;
		int endi = starti + 3;
		int endj = startj + 3;
		
		for(int i = starti; i< endi; i++) {
			for(int j= startj; j < endj; j++) {
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
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	static class Point{
		int i;
		int j;
		ArrayList<Integer> list = new ArrayList<>();
		
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
	}
}
