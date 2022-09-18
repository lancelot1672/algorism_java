package backtracking;

import java.util.Arrays;
import java.util.Scanner;

public class B15654_N과M5 {
	static int N,M;
	static int[] arr;
	static boolean[] selected;
	static int[] output;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[N];
		output = new int[M];
		selected = new boolean[N];
		for(int n=0;n<N;n++) {
			arr[n] = sc.nextInt();
		}
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		
		comb(0, 0);
	}
	static void comb(int idx, int cnt) {
		if(cnt == M) {
			System.out.println(Arrays.toString(output));
			return;
		}
		if(idx == N) {
			return;
		}
		output[cnt] = arr[idx];
		comb(idx+1, cnt+1);
		comb(idx+1, cnt);
	}
}
