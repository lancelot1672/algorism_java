package bf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S전기차충전소 {
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Point> houseList;
	static ArrayList<Point> list;
	static Point[] output;
	static int N;
	static boolean isOne;
	static boolean isTwo;
	static int min1; 
	static int min2;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			
			map = new int[31][31];
			visited = new boolean[31][31];
			output = new Point[2];
			isOne = false;
			isTwo = false;
			
			min1 = Integer.MAX_VALUE;	// 한개만 지어도 될 경우
			min2 = Integer.MAX_VALUE;	// 두개 지어도 될 경우
			houseList = new ArrayList<>();
			list = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) + 15;
				int y = Integer.parseInt(st.nextToken()) + 15;
				int d = Integer.parseInt(st.nextToken());
				
				Point house = new Point(y,x,d);
				map[house.i][house.j] = 9;	//집은 9로
				houseList.add(house);
			}
			for(Point house : houseList) {
				bfs(house);
			}
			// 범위에서 2개 뽑아 진행
			comb(0,0);

			if(isOne) {				
				System.out.println("#" + tc+ " " + min1);
			}else if(isTwo) {
				System.out.println("#" + tc+ " " + min2);
			}else {
				System.out.println("#" + tc+ " " + -1);
			}
		}

	}
	static void comb(int idx, int cnt) {
		if(cnt == 2) {
			//하나로 다 갈 수 있으면
			int sum1 = 0;
			int sum2 = 0;
			int count1 = 0;
			int count2 = 0;
			int total = 0;
			for(Point house : houseList) {
				// 충전소1 과 집의 거리
				int dist1 = calc(output[0], house);
				int dist2 = calc(output[1], house);
				
				// 둘다 안돼면 리턴
				if(dist1 > house.d && dist2 > house.d) return;
				
				//만약 sum1 
				if(dist1 <= house.d) {
					sum1 += dist1;
					count1++;
					total += dist1;
				}
				//만약 sum2
				if(dist2 <= house.d) {
					sum2 += dist2;
					count2++;
					total += dist2;
				}
				
				if(dist1 <= house.d && dist2 <= house.d) {	// 둘다 되는 경우 큰거 빼자
					total -= Math.max(dist1, dist2);	
				}
			}
//			System.out.println("둘다 됌!");
//			System.out.println(output[0]);
//			System.out.println(output[1]);
//			System.out.println("total : " + houseList.size());
//			System.out.println("count1 : " + count1);
//			System.out.println("count2 : " + count2);
			if(count1 == houseList.size()) {	//한개로 해결되면
				isOne = true;
				min1 = Math.min(min1, sum1);
			}
			if(count2 == houseList.size()) {
				isOne = true;
				min1 = Math.min(min1, sum2);
				return;
			}
			// 두개 써야하는 경우이면
			isTwo = true;
			min2 = Math.min(min2, total);
			

			return;
		}
		if(idx == list.size()) {
			
			return;
		}
		output[cnt] = list.get(idx);
		comb(idx+1, cnt+1);
		comb(idx+1, cnt);
	}
	static int calc(Point p1, Point p2) {
		return Math.abs(p1.i - p2.i) + Math.abs(p1.j - p2.j);
	}
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static void bfs(Point house) {
		Queue<Point> q = new LinkedList<>();
		
		q.add(house);
		
		int dist = 0;
		while(!q.isEmpty()) {
			if(dist == house.d) {
				return;
			}
			int size = q.size();
			for(int s=0; s<size; s++) {
				Point now = q.poll();
				
				for(int d=0; d<4; d++) {
					int nexti = now.i + di[d];
					int nextj = now.j + dj[d];
					
					if(nexti < 0 || nexti >= 31 || nextj < 0 || nextj >= 31) {
						continue;
					}
					if(map[nexti][nextj] == 0) {
						map[nexti][nextj] = 1;
						list.add(new Point(nexti,nextj, house.d));
						q.add(new Point(nexti,nextj, house.d));
					}
				}
			}
			dist++;
		}
	}
	static void print() {
		for(int i=0; i<31; i++) {
			for(int j=0; j<31; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	static class Point {
		int i;
		int j;
		int d;
		public Point(int i, int j, int d) {
			this.i = i;
			this.j = j;
			this.d = d;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", d=" + d + "]";
		}
		
	}
}
