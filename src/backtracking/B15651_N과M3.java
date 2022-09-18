package backtracking;

import java.util.Scanner;

public class B15651_N과M3 {
	static int N, M;
	static int[] output;
	static StringBuilder sb;
	public static void main(String[] args) {

		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();		// 1~N까지 자연수
		M = sc.nextInt();		// M개
		
		output = new int[M];
		sb = new StringBuilder();
		perm(0);
		System.out.println(sb.toString());
	}
	static void perm(int idx) {
		if(idx == M) {
			for(int i=0; i<M; i++) {
				sb.append(output[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for(int i=1; i<=N; i++) {
			output[idx] = i;
			perm(idx+1);
		}
		
	}
}
