package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14503_로봇청소기 {
	static int N,M;
	static int[][] map;
	static boolean[][] visited;
	static Robot robot;
	static int total;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//로봇 청소기 처음 상태
		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());
		
		robot = new Robot(R,C,dir);
		map = new int[N][M];
		visited = new boolean[N][M];
		total = 0;
		
		for(int i=0;i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while(true) {
			
			//1. 현재 위치를 청소한다
			visited[robot.i][robot.j] = true;
			total++;
			
			//2. 현재 위치 현재 방향 기준 탐색 진행
			while(true) {
				int nextd = getNextDict(robot.d);
				int nexti = robot.i + di[nextd];
				int nextj = robot.j + dj[nextd];
				
				//2-1. 왼쪽 방향에 아직 청소하지 않은 공간
				if(map[nexti][nextj] == 0 && !visited[nexti][nextj]) {
					robot.d = nextd;	// 그 방향으로 회전
					robot.i = nexti;	// 한 칸 전진
					robot.j = nextj;
					break;	//1번 부터 다시 시작
				}
				// 이게 먼저야..? << 이게 먼저였네 문제 개오바!
				//2-3. 네방향 모두 청소가 이미 되어있거나 벽인 경우
				// 방향 유지, 한칸 후진
				int check = 0;
				for(int d=0; d<4; d++) {
					int ni = robot.i + di[d];
					int nj = robot.j + dj[d];
					
					if(map[ni][nj] == 1 || visited[ni][nj])check++;
				}
				if(check == 4) {
					// 후진 가능한지??	// b : back
					int bd = getBackDict(robot.d);
					int bi = robot.i + di[bd];
					int bj = robot.j + dj[bd];
					
					if(map[bi][bj] == 1) {	// 뒤에도 벽이냐?
						//후진못해?? 끝내.
						System.out.println(total);
						return;
					}else {	//다행하게 벽은 아닌듯?
						//방향 유지 후진하고 2번으로 돌아가
						robot.i = bi;
						robot.j = bj;
						continue;
					}
					
				}
				// 이게 먼저야?
				//2-2. 왼쪽 방향에 청소할 공간이 없다면 그방향으로 회전하고 2번으로 돌아간다.
				// 이미 청소한 곳이고? 벽이면?
				if(map[nexti][nextj] == 1 || visited[nexti][nextj]) {
					robot.d = nextd;	// 그 방향으로 회전
					continue; //2번으로 돌아간다.
				}
				

				////
			}
		}
	}
	static int[] di = {-1, 0, 1, 0};	// 상 우 하 좌
	static int[] dj = {0, 1, 0, -1};
	static int getBackDict(int now) {
		int next = 0;
		switch (now) {
		case 0:
			next = 2;
			break;
		case 2:
			next = 0;
			break;
		case 1:
			next = 3;
			break;
		case 3:
			next = 1;
			break;
		}
		return next;
	}
	static int getNextDict(int now){
		int next = 0;
		switch (now) {
		case 0:
			next = 3;
			break;
		case 1:
			next = 0;
			break;
		case 2:
			next = 1;
			break;
		case 3:
			next = 2;
			break;
		}
		
		return next;
	}
	static class Robot{
		int i;
		int j;
		int d;
		public Robot(int i, int j, int d) {
			this.i = i;
			this.j = j;
			this.d = d;
		}
		
	}
}
