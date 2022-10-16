package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14499_주사위굴리기 {
	static int N, M, x, y, k;
	static int dice[];
	static int[][] map;
	static int[] K;
	static int[] di = {0, 0, 0, -1, 1};	//동 서 북 남
	static int[] dj = {0, 1, -1, 0, 0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		dice = new int[7];
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			 st = new StringTokenizer(br.readLine());
			 for(int j=0; j<M; j++) {
				 map[i][j] = Integer.parseInt(st.nextToken());
			 }
		}
		K = new int[k];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<k; i++) {
			K[i] = Integer.parseInt(st.nextToken());
		}
		int nowTop = 1;	// 주사위 초기 윗면
		int nowi = x;	//주사위 초기 좌표
		int nowj = y;
		for(int i=0; i<k; i++) {
			int order = K[i];
			
			int nexti = nowi + di[order];
			int nextj = nowj + dj[order];
			
			//바깥으로 나간다면 continue;
			if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= M) continue;
			
			//안나가면 수행해
			rollDice(order);
			
			//복사
			if(map[nexti][nextj] == 0) {
				map[nexti][nextj] = dice[6];
			}else {
				dice[6] = map[nexti][nextj];
				map[nexti][nextj] = 0;
			}
			
			//윗면에 수 출력
			System.out.println(dice[1]);
			
			nowi = nexti;
			nowj = nextj;
		}
	}
	
	// 주사위 위의 인덱스와 돌리는 방향으로 조사
	static void rollDice(int d) {
		int temp = dice[1];
		switch (d) {
		case 1:	//동쪽으로 돌려
			dice[1] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = temp;
			break;
		case 2:	 //서쪽으로 돌려
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = dice[4];
			dice[4] = temp;
			break;
		case 3:
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[2];
			dice[2] = temp;
			break;
		case 4:
			dice[1] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = temp;
			break;
		}
	}
}
