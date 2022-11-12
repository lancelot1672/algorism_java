package BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S4193_수영대회결승전 {
	static int N;
	static int[][] map;
	static Point start, end;
	static int ans;
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = -1;
			//시작점, 도착
			StringTokenizer st = new StringTokenizer(br.readLine());
			start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			st = new StringTokenizer(br.readLine());
			end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
			bfs(start);
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static void bfs(Point start) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		q.add(start);
		visited[start.i][start.j] = true;
		
		int time = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s=0; s<size; s++) {
				Point now = q.poll();
				if(now.i == end.i && now.j == end.j) {
					ans = time;
					return;
				}
				
				if(map[now.i][now.j] == 2 && (time) % 3 != 0) {
					q.add(now);	//다시들어 가 ㅋ
					continue;
				}
				
				//
				for(int d=0; d<4; d++) {
					int nexti = now.i + di[d];
					int nextj = now.j + dj[d];
					
					if(nexti <0 || nexti >= N || nextj < 0 || nextj >= N) continue;
					if(visited[nexti][nextj]) continue;
					if(map[nexti][nextj] == 1) continue;	// 벽
					
					q.add(new Point(nexti, nextj));
					visited[nexti][nextj] = true;
				}
			}
			time++;
		}
	}
	static class Point{
		int i;
		int j;
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
}
