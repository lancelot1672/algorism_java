package professor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1194_달이차오른다가자 {
	static int N,M;
	static char[][] map;
	static boolean[][][] visit;
	static Queue<Point> queue;
	
	static int[] di = {0, 0, -1, 1};
	static int[] dj = {1, -1 ,0, 0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][];
		
		queue = new LinkedList<>();
		visit = new boolean[N][M][1<<6];	// 000000:0 ~111111:63 까지 사용가능한 배열 크기
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j] == '0') {
					map[i][j] = '.';	//나중에 원숭이가 열쇠들고 출발점 다시 지나가는 경우 0이면 귀찮음.
					visit[i][j][0] = true;
					queue.add(new Point(i,j,0)); 		//출발점 좌표 큐에 추가
				}
			}
		}
		System.out.println(bfs());
	}
	private static int bfs() {
		int dist = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int s=0; s<size; s++) {
				Point now = queue.poll();
				if(map[now.i][now.j] == '1') {
					return dist;
				}
				for(int d=0; d<4; d++) {
					int nexti = now.i + di[d];
					int nextj = now.j + dj[d];
					
					if(nexti < 0 || nexti >= N || nextj <0 || nextj >= M
							|| map[nexti][nextj] == '#' || visit[nexti][nextj][now.key]) continue;
					
					//a~f 열쇠, A~F문, .평지, 1탈출구
					if(map[nexti][nextj] >= 'a' && map[nexti][nextj] <= 'f') {
						int newKey = 1 << (map[nexti][nextj] - 'a');
						int addedKey = now.key | newKey;	//비트연산
						queue.add(new Point(nexti, nextj, addedKey));
						visit[nexti][nextj][addedKey] = true;
					}else if(map[nexti][nextj] >= 'A' && map[nexti][nextj] <= 'F') {
						int door = 1<<(map[nexti][nextj] - 'A');	// 문를 A=0, B=1, C=2 숫자로 만들기
						if((door & now.key)>0) {
							queue.add(new Point(nexti, nextj, now.key));
							visit[nexti][nextj][now.key] = true;
						}
					}else {
						queue.add(new Point(nexti, nextj, now.key));
						visit[nexti][nextj][now.key] = true;
					}
				}
			}
			dist++;
		}
		return -1;
	}
	static class Point {
		int i, j, key;

		public Point(int i, int j, int key) {
			this.i = i;
			this.j = j;
			this.key = key;
		}
		
		
	}
}
