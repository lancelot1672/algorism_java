package dp;
import java.util.Arrays;
import java.util.Scanner;

public class B1463_1로만들기_DP {
	static int min = Integer.MAX_VALUE;
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		arr = new int[N + 1];
		
		Arrays.fill(arr, Integer.MAX_VALUE);
		arr[N] = 0;
		dfs(N);
		
		System.out.println(min);
	}
	static void dfs(int N) {
		if(N == 1) {
			min = Math.min(arr[1], min);
			return;
		}
		
		if(N % 3 == 0) {
			if(arr[N / 3] > arr[N] + 1) {
				arr[N / 3] = arr[N] + 1;
				dfs(N/3);
			}
		}
		
		if(N % 2 == 0) {
			if(arr[N / 2] > arr[N] + 1) {
				arr[N / 2] = arr[N] + 1;
				dfs(N/2);
			}
		}
		if(arr[N-1] > arr[N] + 1) {
			arr[N-1] = arr[N] + 1;
			dfs(N-1);
		}
	}
}	
