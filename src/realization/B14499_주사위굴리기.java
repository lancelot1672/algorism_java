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
		int nowi = y;	//주사위 초기 좌표
		int nowj = x;
		for(int i=0; i<k; i++) {
			int order = K[i];
			
			int nexti = nowi + di[order];
			int nextj = nowj + dj[order];
			
			//바깥으로 나간다면 continue;
			if(nexti < 0 || nexti >= N || nextj < 0 || nextj >= M) continue;
			
			//안나가면 수행해
			//바닥 가져와~
			int bottomNum = getBottomNum(nowTop, order);
			
			if(map[nexti][nextj] == 0) {
				map[nexti][nextj] = dice[bottomNum];
			}else {
				dice[bottomNum] = map[nexti][nextj];
			}
			
			nowTop = getTopNum(bottomNum);
			//윗면에 수 출력
			System.out.println(dice[nowTop]);
			
			nowi = nexti;
			nowj = nextj;
		}
	}
	static int getTopNum(int bottom) {
		int num = 0;
		switch (bottom) {
		case 1:
			num = 6;
			break;
		case 2:
			num = 5;
			break;
		case 3:
			num = 4;
			break;
		case 4:
			num = 3;
			break;
		case 5:
			num = 2;
			break;
		case 6:
			num = 1;
			break;

		}
		return num;
	}
	
	// 주사위 위의 인덱스와 돌리는 방향으로 조사
	static int getBottomNum(int top, int d) {
		int num = 0;
		switch (top) {
		case 1:
			switch (d) {
			case 1:	//우측
				num = 3;
				break;
			case 2:	//좌측
				num = 4;
				break;
			case 3:	//북측
				num = 2;
				break;
			case 4:	//남측
				num = 5;
				break;
			}
			break;
		case 2:
			switch (d) {
			case 1:	//우측
				num = 3;
				break;
			case 2:	//좌측
				num = 4;
				break;
			case 3:	//북측
				num = 6;
				break;
			case 4:	//남측
				num = 1;
				break;
			}
			break;
		case 3:
			switch (d) {
			case 1:	//우측
				num = 6;
				break;
			case 2:	//좌측
				num = 1;
				break;
			case 3:	//북측
				num = 2;
				break;
			case 4:	//남측
				num = 5;
				break;
			}
			break;
		case 4:
			switch (d) {
			case 1:	//우측
				num = 1;
				break;
			case 2:	//좌측
				num = 6;
				break;
			case 3:	//북측
				num = 2;
				break;
			case 4:	//남측
				num = 5;
				break;
			}
			break;
		case 5:
			switch (d) {
			case 1:	//우측
				num = 3;
				break;
			case 2:	//좌측
				num = 4;
				break;
			case 3:	//북측
				num = 1;
				break;
			case 4:	//남측
				num = 6;
				break;
			}
			break;
		case 6:
			switch (d) {
			case 1:	//우측
				num = 3;
				break;
			case 2:	//좌측
				num = 4;
				break;
			case 3:	//북측
				num = 5;
				break;
			case 4:	//남측
				num = 2;
				break;
			}
			break;
		}
		
		return num;
	}
}
