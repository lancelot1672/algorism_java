package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B19236_청소년상어 {
	static Fish[][] map;
	static Fish Shark;
	static int index= 0;
	static int max;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = null;
		map = new Fish[4][4];
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				int a = Integer.parseInt(st.nextToken());	//물고기 남바
				int b = Integer.parseInt(st.nextToken());	//물고기 방향
				map[i][j] = new Fish(i ,j, a, b);
			}
		}
		//맨 처음 0,0 입성
		int fishNum = map[0][0].n;
		Shark = new Fish(0,0, 0, map[0][0].d);	// 고기번호 x
		map[0][0] = Shark;

		max = Integer.MIN_VALUE;
		//print();
		//moveFish();

		moveShark(fishNum, Shark);
		
		System.out.println(max);
	}
	static int[] di = {0, -1, -1, 0, +1, +1, +1, 0, -1};
	static int[] dj = {0, 0, -1, -1, -1, 0, +1, +1, +1};
	static void moveShark(int num, Fish shark) {

		//최댓값.

		
		int sharki = shark.i;
		int sharkj = shark.j;
		int sharkd = shark.d;
		//물고기 움직여
		moveFish();
		print();
		System.out.println(sharkd);
		int nowi = sharki;
		int nowj = sharkj;
		
		while(true) {	//방향에서 쭉 해봐야함.
			int nexti = nowi + di[shark.d];
			int nextj = nowj + dj[shark.d];
			
			if(nexti >= 0 && nexti < 4 && nextj >= 0 && nextj < 4) {
				if(map[nexti][nextj] != null) {//물고기 없는 칸.
					System.out.println(map[nexti][nextj]);
					//백트래킹...?
					//물고기 자리에 상어가 가고 방향을 뺏음.
					Fish fish = map[nexti][nextj];
					
					shark.i = fish.i;
					shark.j = fish.j;
					shark.d = fish.d;
					map[nexti][nextj] = shark;
					map[sharki][sharkj] = null;	//상어자리 null;
					print();
					index++;
					moveShark(num + fish.n, shark);
					
					//rollBack
					shark.i = sharki;
					shark.j = sharkj;
					shark.d = sharkd;
					map[nexti][nextj] = fish;
					map[sharki][sharkj] = shark;
					

				}	
			}else {	//범위 벗어나면 끝.
				break;
			}
			
			nowi = nexti;
			nowj = nextj;
		}
		max = Math.max(num, max);
		System.out.println(max);
	}
	static void moveFish() {
		PriorityQueue<Fish> pq = new PriorityQueue<>((f1, f2) -> f1.n - f2.n);
		//물고기 순서대로 입장~
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(map[i][j] != null && map[i][j].n != 0) {
					pq.add(map[i][j]);
				}
			}
		}
		while(!pq.isEmpty()) {
			//System.out.println(pq.toString());
			//print();
			Fish now = pq.poll();
			
			//물고기 이동
			int nowd = now.d;	//원래 방향
			int cnt = 0;
			
			while(true) {
				int nexti = now.i + di[nowd];
				int nextj = now.j + dj[nowd];
				
				//경계밖으로 나가거나 상어가 있는 칸이 아님.
				if(nexti >= 0 && nexti < 4 && nextj >= 0 && nextj < 4) {
					//빈칸이면?
					if(map[nexti][nextj] == null) {
						//빈칸으로 물고기 이동.
						map[now.i][now.j] = null;
						now.i = nexti;
						now.j = nextj;
						now.d = nowd;
						map[nexti][nextj] = now;
						
						break;
					}else {
						//빈칸이 아닌데
						if(map[nexti][nextj].n == 0) {	//상어가 있네?
							//상어는 못감
						}else {	//물고기 있나봄
							//물고기가 있는 칸이면 스왑
							
							Fish other = map[nexti][nextj];
							now.d = nowd;
							map[nexti][nextj] = now;
							map[now.i][now.j] = other;
							
							int tempi = now.i;
							int tempj = now.j;
							
							now.i = other.i;
							now.j = other.j;
							
							other.i = tempi;
							other.j = tempj;
							break;
						}
					}
					
					
				}
				//못가는곳이면
				cnt++;
				nowd = getNextDict(nowd);	//반시계 방향으로 돌려봄
				if(cnt == 8) {	// 한바퀴 다돔.
					break;	//안움직임
				}
			}
		}

	}
	static void print() {
		System.out.println("--------------------------");
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(map[i][j] != null) {
					System.out.print(map[i][j].n + " ");
				}else {
					System.out.print("0 ");
				}
					
			}
			System.out.println();
		}
	}
	static Fish[][] deepcopy(Fish[][] origin) {
		Fish[][] copy = new Fish[4][4];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				copy[i][j] = origin[i][j];
			}
		}
		return copy;
				
	}
	static int getNextDict(int now) {
		switch (now) {
		case 1: return 2;
		case 2: return 3;
		case 3: return 4;
		case 4: return 5;
		case 5: return 6;
		case 6: return 7;
		case 7: return 8;
		case 8: return 1;
		
		}
		return 0;
	}
	static class Fish{
		int i;
		int j;
		int n;
		int d;
		public Fish(int i, int j, int n, int d) {
			this.i = i;
			this.j = j;
			this.n = n;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Fish [i=" + i + ", j=" + j + ", n=" + n + ", d=" + d + "]";
		}

		
		
	}
}
