package BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_전기차충전소 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<House> houseList;
	static ArrayList<House> list;
	static int max = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			//집의 갯수
			N = sc.nextInt();
			
			// 초기화
			map = new int[32][32];
			visited = new boolean[32][32];
			houseList = new ArrayList<>();
			list = new ArrayList<>();
			
			for(int n=1; n<=N; n++) {
				//집의 좌표 , 거리
				int j = sc.nextInt() + 15;
				int i = sc.nextInt() + 15;
				int d = sc.nextInt();
				House h = new House(i, j, d);
				houseList.add(h);
				bfs(h);
			}// end input
			// house 갯수 만큼 checked;

			print();
			comb(0, 0);		// n개중 2개를 뽑아서 진행
			System.out.println(max);
		}
	}
	static House[] output = new House[2];
	static boolean[] checked;
	static void comb(int idx, int cnt) {
		if(cnt == 2) {
			if(max == 3) {
				return;
			}
			checked = new boolean[N];
			//충전소 좌표 두개를 돌려보자
			int cnt1 = 0;
			for(int i=0; i<houseList.size(); i++) {
				House h = houseList.get(i);
				
				int dist = Math.abs(output[0].i - h.i) + Math.abs(output[0].j - h.j);
				if(h.d >= dist) {
					checked[i] = true;
					cnt1++;
				}
			}
			//house 갯수만큼 가능하면 끝
			if(cnt1 == houseList.size()) {
				max = 3;
				return;
			}
			// 아니면 두번째꺼도 돌려봐야지
			for(int i=0; i<houseList.size(); i++) {
				House h = houseList.get(i);
				
				int dist = Math.abs(output[1].i - h.i) + Math.abs(output[1].j - h.j);
				if(h.d >= dist) {
					checked[i] = true;
				}
			}
			// 모든 집이 true 이면 2
			for(int i=0; i<houseList.size(); i++) {
				if(!checked[i]) {
					return;
				}
			}
			max = 2;
			return;
		}
		if(idx == list.size()) {

			return;
		}
		output[cnt] = list.get(idx);
		comb(idx+1, cnt+1);
		comb(idx+1, cnt);
	}
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};

	static void bfs(House now) {
		//모든 집의 범위만큼 돌린다
		// 범위가 되는 좌표에서 체크하기 위함
		
		Queue<House> q = new LinkedList<>();
		q.add(now);
		visited[now.i][now.j] = true;
		
		int dist=0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s=0;s<size; s++) {
				House h = q.poll();
				
				for(int d=0; d<4; d++) {
					int nexti = h.i + di[d];
					int nextj = h.j + dj[d];
					
					if(nexti < 0 || nexti > 31 || nextj < 0 || nextj > 31) {
						continue;
					}
					if(visited[nexti][nextj]) {
						continue;
					}
					House next = new House(nexti, nextj, -1);
					
					q.add(next);
					visited[nexti][nextj] = true;
					map[nexti][nextj] = 1;
					
					list.add(next);		//범위 리스트에 추가
				}
			}
			dist++;
			if(dist == now.d) {
				break;
			}
		}
	}
	static void print() {
		for(int i=0; i<32; i++) {
			for(int j=0; j<32; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	static class House{
		int i;
		int j;
		int d;
		public House(int i, int j, int d) {
			this.i = i;
			this.j = j;
			this.d= d;
		}
		
		
	}
}
