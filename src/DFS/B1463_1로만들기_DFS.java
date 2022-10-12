package DFS;
import java.util.Scanner;

public class B1463_1로만들기_DFS {
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		dfs(N, 0);
		System.out.println(min);
	}
	static void dfs(int x, int idx) {
		if(x == 1) {
			min = Math.min(idx, min);
			return;
		}
		if(idx >= min) {
			return;
		}
		if(x % 3 == 0) {
			dfs(x/3, idx+1);
		}
		if(x % 2 == 0) {		
			dfs(x/2, idx+1);
		}
		dfs(x-1, idx+1);
	}
}	
