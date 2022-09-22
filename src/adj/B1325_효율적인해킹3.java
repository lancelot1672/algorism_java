package adj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class B1325_효율적인해킹3 {
	static LinkedList<Integer>[] adjList;
	static PriorityQueue<Point> list = new PriorityQueue<>((p1, p2) -> p2.cnt - p1.cnt);
	static int N,M;
	static int max = 0;
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		// LinkedList 배열 초기화
		adjList = new LinkedList[N+1];
		arr = new int[N+1];

		Arrays.fill(arr, -1);
		for(int i=1; i<N+1; i++) {
			adjList[i] = new LinkedList<>();
		}
		
		for(int m=0; m<M; m++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			
			adjList[B].add(A);
		}
		for(int i=1; i<N+1; i++) {
			visited = new boolean[N+1];
			cnt = 0;
			dfs(i);
			if(max <= cnt) {
				//리스트에 추가
				list.add(new Point(i, cnt));
				max = cnt;
			}
			
			//System.out.println(cnt);
		}
		for(Point p : list) {
			if(max != p.cnt) {
				break;
			}
			System.out.print(p.num + " ");
		}
		
	}
	static int cnt;
	static boolean[] visited;
	static void dfs(int num) {
		for(int n : adjList[num]) {
			if(!visited[n]) {
				visited[n] = true;
				cnt++;
				dfs(n);
			}
		}
	}
	static class Point{
		int num;
		int cnt;
		public Point(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
		@Override
		public String toString() {
			return "Point [num=" + num + ", cnt=" + cnt + "]";
		}
	}
}
