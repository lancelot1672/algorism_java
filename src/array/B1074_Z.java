package array;

import java.util.Scanner;

public class B1074_Z {
	static int N, r, c;
	static int[][] arr = new int[2][2];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		
		arr[0][0] = 1;
		arr[0][1] = 2;
		arr[1][0] = 3;
		arr[1][1] = 4;
		if(N > 2) {
			int n1 = (int) Math.pow(2, N) / 4;
			System.out.println(n1);
			//16 수 구하기
			int r1 = (r / 4) * n1;
			int c1 = (c / 4);
			System.out.println(r1 + c1);
			int sum = (r1 + c1) * 16;
			sum--;
			
			int r2 = ((r % 4) / 2) * 2;
			int c2 = (r % 4) / 2;
			
			sum += (r2 + c2) * 4;
			System.out.println(r2 + c2);
			
			int r3 = r % 2;
			int c3 = c % 2;
			
			sum += arr[r3][c3];
			
			System.out.println(sum);
		}
	}
}
