package adj;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class B1325_효율적인해킹 {
	static LinkedList<Integer>[] adjList;
	static PriorityQueue<Point> list = new PriorityQueue<>((p1, p2) -> p2.cnt - p1.cnt);
	static int N,M;
	static int max = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		// LinkedList 배열 초기화
		adjList = new LinkedList[N+1];
		for(int i=1; i<N+1; i++) {
			adjList[i] = new LinkedList<>();
		}
		
		for(int m=0; m<M; m++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			
			adjList[B].add(A);
		}
		for(int i=1; i<=N; i++) {
			bfs(i);
		}
		for(Point p : list) {
			if(max != p.cnt) {
				break;
			}
			System.out.print(p.num + " ");
		}
	}
	static void bfs(int start) {
		boolean[] visit = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visit[start] = true;
		
		int cnt = 1;
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int n : adjList[now]) {
				if(!visit[n]) {
					cnt++;		//해킹 갯수
					q.add(n);
				}
			}
		}
		if(max <= cnt) {
			//리스트에 추가
			list.add(new Point(start, cnt));
			max = cnt;
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
