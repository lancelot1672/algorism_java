package professor;

import java.util.LinkedList;
import java.util.Queue;

public class B1600_말이되고픈원숭이_3차_교수님 {
	static int H, W, K;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1 ,-1};
	static boolean[][][] visited;
	static int[][] map;
	
	public static void main(String[] args) {
		
	}
	static int bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0,0,0));
		visited[0][0][0] = true;
		
		int time = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int s=0; s<size; s++) {
				Point now = q.poll();
				
				if(now.i == H-1 && now.j == W-1) {
					return time;
				}
				for(int d=0; d<4; d++) {	//걸어서 이동
					int nexti = now.i + di[d];
					int nextj = now.j + dj[d];
					
					if(nexti >= 0 && nextj>=0 && nexti < H && nextj < W && 
							map[nexti][nextj] == 0 && !visited[nexti][nextj][now.skill]) {
						visited[nexti][nextj][now.skill] = true;
						q.add(new Point(nexti,nextj, now.skill));
					}
				}
				if(now.skill +1 > K) {
					continue;
				}
				for(int d=0; d<8; d++) {	//말 처럼 이동
					int nexti = now.i + di[d];
					int nextj = now.j + dj[d];
					
					if(nexti >= 0 && nextj>=0 && nexti < H && nextj < W && 
							map[nexti][nextj] == 0 && !visited[nexti][nextj][now.skill + 1]) {
						visited[nexti][nextj][now.skill + 1] = true;
						q.add(new Point(nexti,nextj, now.skill + 1));
					}
				}
			}
		}
		return -1;
	}
	static class Point{
		int i;
		int j;
		int skill;
		public Point(int i, int j, int skill) {
			this.i = i;
			this.j = j;
			this.skill = skill;
		}
		
	}
}
