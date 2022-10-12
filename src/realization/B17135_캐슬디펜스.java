package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17135_캐슬디펜스 {
	static int N, M, D;
	static int[][] map;
	static ArrayList<Point> list;
	static int max;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		list = new ArrayList<>();
		max = Integer.MIN_VALUE;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//궁수 array
		for(int m=0; m<M; m++) {
			list.add(new Point(N, m));
		}
		output = new Point[3];
		comb(0,0);
		
		System.out.println(max);
	}
	static Point[] output;
	static int[][] copy;
	static void comb(int idx, int cnt) {
		if(cnt == 3) {
			//궁수 3명 뽑았으~
			copy = deepcopy(map);
			int killCnt = 0;
			
			//N만큼 반복할 걸??
			for(int n=0; n<N; n++) {
				//궁수 공격
				enemy = new ArrayList<>();
				for(Point archor : output) {
					bfs(archor);
				}
				
				// 공격 받은 적 사라져~
				for(Point e : enemy) {
					if(copy[e.i][e.j] == 1) {
						copy[e.i][e.j] = 0;
						killCnt++;
					}
				}
				//적 내려와
				for(int i=N-1; i>=1; i--) {
					for(int j=0; j<M; j++) {
						copy[i][j] = copy[i-1][j];
					}
				}
				//맨 윗줄 0으로 채우기
				Arrays.fill(copy[0], 0);
			}
			max = Math.max(max, killCnt);
			return;
		}
		if(idx == M) {
			return;
		}
		output[cnt] = list.get(idx);
		comb(idx+1, cnt+1);
		comb(idx+1, cnt);
	}
	static int[] di = {0, -1, 0};	//좌 상 우
	static int[] dj = {-1, 0, 1};
	static ArrayList<Point> enemy;
	static void bfs(Point start) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		//visited[start.i][start.j] = true;
		q.add(start);
		
		int dist=0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s=0; s<size; s++) {
				Point now = q.poll();
				
				for(int d=0; d<3; d++) {
					int nexti = now.i + di[d];
					int nextj = now.j + dj[d];
					if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= M) continue;
					if(visited[nexti][nextj]) continue;
					
					if(copy[nexti][nextj] == 1) {
						//제일 가까운 적 발견
						enemy.add(new Point(nexti, nextj));
						return;
					}
					if(copy[nexti][nextj] == 0) {
						q.add(new Point(nexti, nextj));
						visited[nexti][nextj] = true;
					}
				}
			}
			dist++;
			if(dist == D) {
				return;
			}
		}
	}
	static int[][] deepcopy(int[][] origin){
		int[][] copy = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				copy[i][j] = origin[i][j];
			}
		}
		return copy;
	}
	static class Point{
		int i;
		int j;
		
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + "]";
		}
		
	}
}
