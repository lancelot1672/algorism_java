package realization;

import java.util.Arrays;
import java.util.Scanner;

public class B17281_야구 {
	static int N;
	static int[][] taja;
	static int[][] taja4;
	static int max = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		taja4 = new int[1][N+1];	//4번타자
		taja = new int[10][N + 1];
		for(int n=0; n<N; n++) {
			taja4[0][n] = sc.nextInt();	//1번이 4번타자
			for(int m=1; m <= 9; m++) {	//나머지 타자 8명
				if(m == 4) {
					continue;
				}
				taja[m][n] = sc.nextInt();
			}
		}
		perm(0);
//		func();
		System.out.println(max);
	}

	static boolean[] selected = new boolean[8];
	static int[] arr = { 1, 2, 3, 5, 6, 7, 8, 9 };
	static int[] output = new int[8];

	static void perm(int cnt) {
		if (cnt == 8) {
			int[] taBun = new int[9];
			taBun[3] = 4;
			for (int i = 0; i < 3; i++) {
				taBun[i] = output[i];
			}
			for (int i = 4; i < 9; i++) {
				taBun[i] = output[i-1];
			}
			//System.out.println(Arrays.toString(taBun));
			func(taBun);
			return;
		}

		for (int i = 0; i < 8; i++) {
			if (selected[i] != true) {
				selected[i] = true;
				output[cnt] = arr[i];
				perm(cnt + 1);
				selected[i] = false;

			}
		}
	}

	static void func(int[] taBun) {
//	static void func() {
//		int[] taBun = { 5,6,7,4, 1,2,3,8,9};
		// [9, 8, 7, 4, 6, 5, 3, 2, 1]
		int total = 0;
		int inning = 0; // 현재 이닝수
		int ta = 1; // 타자 1~9
		int outCount = 0; // 아웃 카운트
		int[] lu = new int[4]; // 1루, 2루, 3루

		while (true) {
			int tajaNum = taBun[ta - 1]; // 타자 넘버 ex ) 9번 선수가 1번 타석
			int swing = 0;
			
			if(tajaNum == 4) {
				swing = taja4[0][inning];	// 4번 타자
			}else {
				swing = taja[tajaNum][inning]; // n번선수가 휘두르는 결과
			}
			//System.out.println(tajaNum + " : " + swing);
			switch (swing) {
			case 1:
				// 안타
				if (lu[3] == 1) {
					total++; // 3루수 들어오고
					lu[3] = 0; // 3루 비워주고
				}
				if (lu[2] == 1) {
					lu[3] = 1; // 2루수 3루 진출
					lu[2] = 0; // 2루 비워주고
				}
				if (lu[1] == 1) {
					lu[2] = 1; // 1루수 2루 진출
					lu[1] = 0; // 1루 비워주고
				}
				lu[1] = 1;	// 1루 도착!
				break;
			case 2:
				// 2루타
				if (lu[3] == 1) {
					total++; // 3루수 들어오고
					lu[3] = 0; // 3루 비워주고
				}
				if (lu[2] == 1) {
					total++; // 2루수 들어오고
					lu[2] = 0; // 2루 비워주고
				}
				if (lu[1] == 1) {
					lu[3] = 1; // 1루수 3루 진출
					lu[1] = 0; // 1루 비워주고
				}
				lu[2] = 1;	// 2루 도착!
				break;
			case 3:
				// 3루타
				if (lu[3] == 1) {
					total++; // 3루수 들어오고
					lu[3] = 0;	// 3루 비워
				}
				if (lu[2] == 1) {
					total++; // 2루수 들어오고
					lu[2] = 0; // 2루 비워
				}
				if (lu[1] == 1) {
					total++; // 1루수 들어오고
					lu[1] = 0; // 1루 비워주고
				}
				lu[3] = 1; // 타자 3루 까지 진출
				break;
			case 4:
				if (lu[3] == 1) {
					total++; // 3루수 들어오고
					lu[3] = 0;
				}
				if (lu[2] == 1) {
					total++; // 2루수 들어오고
					lu[2] = 0;
				}
				if (lu[1] == 1) {
					total++; // 1루수 들어오고
					lu[1] = 0;
				}
				total++; // 타자 들어오고
				// 홈런
				break;
			case 0:
				// 아웃
				outCount++; // 아웃 카운트 증가
				break;
			}
			// 다음 타자!
			ta++;
			if (ta == 10) { // 9번했으면
				ta = 1; // 1번타자 시작
			}

			if (outCount == 3) {
				outCount = 0;
				inning++;	//새 이닝
				
				Arrays.fill(lu, 0); // 새 이닝 시작되면 주자는 없다.
				if (inning == N) {
					break;
				}
			}
		} // end While
		max = Math.max(total, max);
	}
}
