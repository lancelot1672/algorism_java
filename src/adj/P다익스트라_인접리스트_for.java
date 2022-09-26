package adj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class P다익스트라_인접리스트_for {
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
        int[] D = new int[N+1];

        boolean[] visited = new boolean[N+1];
        
        Arrays.fill(D, Integer.MAX_VALUE);
        int start = 1;
        
        D[start] = 0;
        
        int min, current = 1;
        
        for(int i=1; i<=N; i++) {
        	//step1. 미방문 정점 중 출발지에서 자신으로의 비용이 최소인 정점 선택
			min = Integer.MAX_VALUE;
			for(int j=1; j<=N; j++) {
	        	for(Point now : adjList[j]) {
	            	if(!visited[now.num] && min > D[now.num]) {
	            		min = D[now.num];
	            		current = now.num;
	            	}
	            }
			}
        	// step2. 방문처리
        	visited[current] = true;
        	
        	// step3. 선택된 정점을 경유지로 해서 미방문 정점들로 가는 비용을 따져보고
			// 기존 최적해 보다 유리하면 갱신
        	for(Point now : adjList[current]) {
            	if(!visited[now.num] && D[now.num] > D[current] + now.weight) {
            		D[now.num] = D[current] + now.weight;
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