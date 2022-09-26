package adj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P다익스트라_인접리스트_PQ {
    static int N,M;
    static LinkedList<Point>[] adjList;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        
        adjList = new LinkedList[N+1];
        for(int n=1; n<=N; n++) {
            adjList[n] = new LinkedList<Point>();
        }
        for(int m=0; m<M; m++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int w = sc.nextInt();
            adjList[from].add(new Point(to, w));
            adjList[to].add(new Point(from, w));
        }
        
        func();
    }
    static void func() {
    	PriorityQueue<Point> pq = new PriorityQueue<>((p1, p2) -> p1.weight - p2.weight);
        int[] D = new int[N+1];

        boolean[] visited = new boolean[N+1];
        
        Arrays.fill(D, Integer.MAX_VALUE);
        int start = 1;
        pq.add(new Point(start, 0));
        
        D[start] = 0;
        
        while(!pq.isEmpty()) {
        	Point now = pq.poll();
           	visited[now.num] = true;

           	for(Point next : adjList[now.num]) {
        		if(!visited[next.num] && D[next.num] > D[now.num] + next.weight) {
        			 D[next.num] = D[now.num] + next.weight;
        			 pq.add(new Point(next.num, D[next.num]));
        		}
        	}
        }
        
        System.out.println(Arrays.toString(D));
    }
    static class Point{
    	int num;
    	int weight;
		public Point(int num, int weight) {
			super();
			this.num = num;
			this.weight = weight;
		}
    }
}