package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S무선충전 {
	static int M, A;
	static int[] userA;
	static int[] userB;
	static AP[] apList;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());	//이동시간
			A = Integer.parseInt(st.nextToken());	//BC의 갯수
			
			userA = new int[M];
			userB = new int[M];
			
			st = new StringTokenizer(br.readLine());	// 사용자 A
			for(int i=0; i<M; i++) {
				userA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());	// 사용자 B
			for(int i=0; i<M; i++) {
				userB[i] = Integer.parseInt(st.nextToken());
			}
			
			apList = new AP[A];
			//BC정보
			for(int a=0; a<A; a++) {
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				
				apList[a] = new AP(i,j,c,p);
			}
			////////////////////////////////////////////////
			
			AP nowA = new AP(1, 1);
			AP nowB = new AP(10, 10);
			
			dfs(0, 0, nowA, nowB);
			////////////////////////////////////////////////
		}
		
	}
	static int[] di = {0, -1, 0, 1, 0};	// 상 우 하 좌
	static int[] dj = {0, 0, 1, 0, -1};
	static void dfs(int time, int sum, AP nowA, AP nowB) {
		if(time == M) {
			
			return;
		}
		boolean isChargeA = false;
		boolean isChargeB = false;
		
		//AP 리스트들 중에서 연결되는 곳 찾기. (true false)
		boolean[] list_A = new boolean[A];
		for(int i=0; i<A; i++) {
			AP ap = apList[i];
			if(ap.c + 1 >= Math.abs(nowA.i - ap.i) + Math.abs(nowA.j - ap.j)) {
				//충전 범위 안에 든다면 true
				list_A[i] = true;
				isChargeA = true;
			}
		}
		
		//B의 다음 위치
		//AP 리스트들 중에서 연결되는 곳 찾기. (true false)
		boolean[] list_B = new boolean[A];
		for(int i=0; i<A; i++) {
			AP ap = apList[i];
			if(ap.c + 1 >= Math.abs(nowB.i - ap.i) + Math.abs(nowB.j - ap.j)) {
				//충전 범위 안에 든다면 true
				list_B[i] = true;
				isChargeB = true;
			}
		}
		
		int max = 0;
		
		//두개의 리스트를 비교
//		for(int i=0; i<A; i++) {
//			//같이 지나치는 경우 => 반감
//			if(list_A[i] && list_B[i]) {
//				max = Math.max(max, apList[i].p / 2);
//			}else if(list_A[i] && !list_B[i]) {
//				int power = apList[i].p;
//				//A가 지나치고 B가 안지나쳐
//				for(int j=0; j<A; j++) {
//					if(i == j) continue;	//자기 자신 제외
//					if(list_B[j]) {
//						power += apList[j].p;
//					}
//				}
//				max = Math.max(max, power);
//			}else if(!list_A[i] && list_B[i]) {
//				int power = apList[i].p;
//				//A가 지나치고 B가 안지나쳐
//				for(int j=0; j<A; j++) {
//					if(i == j) continue;	//자기 자신 제외
//					if(list_A[j]) {
//						power += apList[j].p;
//					}
//				}
//				max = Math.max(max, power);
//			}
//		}
		int powerA = 0;
		int powerB = 0;
		System.out.println("time : " + time);
		System.out.println("nowA[ i :" + nowA.i + ", j :" + nowA.j + "]");
		System.out.println("nowB[ i :" + nowB.i + ", j :" + nowB.j + "]");
		System.out.println("isChargeA : " + isChargeA);
		System.out.println("isChargeB : " + isChargeB);
		if(isChargeA && isChargeB) {
			
		}else if(!isChargeA && isChargeB) {
			for(int a=0; a<A; a++) {
				if(list_B[a])
					powerB = Math.max(powerB, apList[a].p);
			}
		}else if(isChargeA && !isChargeB) {
			for(int a=0; a<A; a++) {
				if(list_A[a])
					powerA = Math.max(powerA, apList[a].p);
			}
		}
		
		System.out.println("maxA[" + time + "] = " + powerA);
		System.out.println("maxB[" + time + "] = " + powerB);
		
		// max 가 min_value라면 둘다 충전 x
		AP nextA = new AP(nowA.i + di[ userA[time] ], nowA.j + dj[ userA[time] ]);
		AP nextB = new AP(nowB.i + di[ userB[time] ], nowB.j + dj[ userB[time] ]);
	
		dfs(time+1, sum, nextA, nextB);
	}
	static class AP{
		int i;
		int j;
		int c;	//충전 범위
		int p;	//파워
		
		public AP(int i, int j, int c, int p) {
			this.i = i;
			this.j = j;
			this.c = c;
			this.p = p;
		}

		public AP(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
		
	}
}
