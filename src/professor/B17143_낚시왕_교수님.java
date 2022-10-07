package professor;

import java.util.Scanner;

public class B17143_낚시왕_교수님 {
	static Shark[][] start, end;
	static int[] di = {0, -1, +1, 0, 0};		//1:up 2:down 3:right 4:left
	static int[] dj = {0, 0, 0, +1, -1};
	static int R, C, M; // t세로, 가로, 전체 마릿수
	static int fisher;
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();		// 세로
		C = sc.nextInt();		// 가로
		M = sc.nextInt();	// 마릿수
		
		start = new Shark[R+1][C+1];
		end = new Shark[R+1][C+1];
		ans = 0;
		for(int m=0; m<M; m++) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			int speed = sc.nextInt();
			int dir = sc.nextInt();
			int size = sc.nextInt();
			
			start[i][j] = new Shark(speed, dir, size);
		}
		fisher = 0;
		while(true) {
			fisher++;
			if(fisher > C) break;
			fishing();	// 가까운 상어 낚시
			move();
		}
		System.out.println(ans);
	}
	private static void move() {
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				if(start[i][j] != null) {	// 상어 출발!
					Shark now = start[i][j];
					
					int nexti = i + di[now.dir] * now.speed;	// 슈퍼 직진.
					int nextj = j + dj[now.dir] * now.speed;
					
					while(nexti > R || nexti < 1 ) {	// 위 아래로 뛰쳐나갔다면? 여기 반복
						now.dir = reverse(now.dir);
						if(nexti > R) {	// 크게 뛰쳐나간 부분 음수로 계산되서 돌아오게끔
							nexti = R + (R - nexti);	// nexti가 R보다 커서 R - nexti 는 음수
						}else if(nexti < 1){
							nexti = 1 + (1 - nexti);		// nexti가 음수라서 1-nexti하면 양수.
						}
					}
					while(nextj > C || nextj < 1) {		// 좌우로 뛰쳐나간거니? 여기 반복
						now.dir = reverse(now.dir);
						if(nextj > C) {
							nextj = C + (C - nextj);
						}else if(nextj < 1) {
							nextj = 1 + (1 - nextj);
						}
					}
					
					start[i][j] = null;	// 이동 전 상어가 떠나요~
					if(end[nexti][nextj] == null || end[nexti][nextj].size < now.size) {
						end[nexti][nextj] = now;
					}
				}
			} // end for j
		} // end for i
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				start[i][j] = end[i][j];
				end[i][j] = null;
			}
		}
	}
	private static int reverse(int dir) {
		switch (dir) {
		case 1:
			dir = 2;
			break;
		case 2:
			dir = 1;
			break;
		case 3:
			dir = 4;
			break;
		case 4:
			dir = 3;
			break;
		}
		
		return dir;
	}
	private static void fishing() {
		for(int i=0; i<=R; i++) {
			if(start[i][fisher] != null) {
				ans += start[i][fisher].size;
				start[i][fisher] = null;
				break;
			}
		}
	}
	static class Shark{
		int speed, dir, size;

		public Shark(int speed, int dir, int size) {
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
		
		
	}
}	
