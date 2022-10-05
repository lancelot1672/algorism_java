package BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class B17143_낚시왕 {
	static int R, C, M;
	static Shark[][] map;
	static int sharkCnt;
	static ArrayList<Shark> list;
	static int sum = 0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Shark[R][C];
		list = new ArrayList<>();
		sharkCnt = 0;
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) -1;
			int c = Integer.parseInt(st.nextToken()) -1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			Shark shark = new Shark(r,c,s,d,z);	//맵에 상어 추가
			list.add(shark);

		}
		// map이 null이라면 상어가 없는 것이야.
		for(int c=0; c<C; c++) {
			//해당 열에 상어가 있으면 잡자
//			for(int r=0; r<R; r++) {
//				if(map[r][c] != null) {	// 상어가 있어요~
//					Shark s = map[r][c];
//					sharkCnt--;	// 잡았죠?
//					list.remove(s);
//					break;		//한마리씩 밖에 못잡음.
//				}
//			}
			Collections.sort(list);
			//상어 잡자 - 리스트에서 꺼내서 확인
			for(Shark shark : list) {	// 땅에서 가까운 순으로 정렬되어 있음.
				if(shark.c == c) {
					list.remove(shark);
					sum += shark.z;
					break;
				}
			}
			map = new Shark[R][C];
			// 상어 이동 - 리스트에 있는 것들 이동시키기
			for(Shark shark : list) {
				if(shark.d == 1) {	// 위로 가는데 -1, 0
					if(shark.r + (shark.s * -1) < 0) {	// 밖으로 몬나가.

						
						
					}else {		// 정상 움직임
						shark.r = shark.r + (shark.s * -1);
						if(shark.r == 0) {
							shark.d = 2;
						}
					}
					
				}
				else if(shark.d == 2) {	// 아래로 가는데 1, 0
					if(shark.r + (shark.s * 1) >= R) {	// 밖으로 몬나가.

						
						
					}else {		// 정상 움직임
						shark.r = shark.r + (shark.s * 1);
						if(shark.r == R-1) {
							shark.d = 1;
						}
					}
				}
				else if(shark.d == 3) {	// 우로 가는데 0, 1
					if(shark.c + (shark.s * 1) >= C) {	// 밖으로 몬나가.
						int a = (shark.s - shark.c) / (C-1);	//몫
						int b = (shark.s - shark.c) % (C-1);	//나머지
						
						if(a % 2 == 0) {
							shark.d = 4;
						}
						shark.c = b;
					}else {		// 정상 움직임
						shark.c = shark.c + (shark.s * 1);
						if(shark.c == C-1) {
							shark.d = 4;
						}
					}
					System.out.println(shark.c);
				}
				else if(shark.d == 4) {	// 좌로 가는데 0, -1
					if(shark.c + (shark.s * -1) < 0) {	// 밖으로 몬나가.
						shark.c = 0 - (shark.c + shark.s * -1);
						shark.d = 3;
					}else {		// 정상 움직임
						shark.c = shark.c + (shark.s * -1);
						if(shark.c == 0) {
							shark.d = 3;
						}
					}
					System.out.println(shark.c);
				}
				System.out.println(shark.r);

				//갈곳에 상어가 없으면 그냥 들어가고.
				if(map[shark.r][shark.c] == null) {
					map[shark.r][shark.c] = shark;
				}else {		//기존에 상어가 있으면 붙어봐야지
					Shark first = map[shark.r][shark.c];
					if(first.z < shark.z) {	//새로운 것이 더 큰 경우
						map[shark.r][shark.c] = shark;
						
					}
				}
				
			}
			list.clear();
			//상어 끼리 잡아믁혀~
			for(int r=0; r<R; r++) {
				for(int cc=0; cc<C; cc++) {
					if(map[r][cc] != null) {
						list.add(map[r][cc]);
					}
				}
			}// end for
		}
		
		System.out.println(sum);
	}
	static class Shark implements Comparable<Shark>{
		int r;
		int c;
		int s;
		int d;
		int z;
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		@Override
		public int compareTo(Shark o) {
			return 0 - o.r;
		}
	}
}	
