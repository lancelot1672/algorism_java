package adj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B1504_특정한최단경로2 {
	static int N, M;
    static LinkedList<Point>[] adjList;
    static int num = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
        adjList = new LinkedList[N+1];
        
        for(int n=1; n<=N; n++) {
            adjList[n] = new LinkedList<Point>();
        }
        for(int m=0; m<M; m++) {
        	st = new StringTokenizer(br.readLine());	
        	
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            //양방향
            adjList[from].add(new Point(to, w));
            adjList[to].add(new Point(from, w));
        }
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        
        boolean t1_1 = func(1, a);
        boolean t1_2 = func(a, b);
        boolean t1_3 = func(b, N);
        int ans1 = num;
        num = 0;
        
        boolean t2_1 = func(1, b);
        boolean t2_2 = func(b, a);
        boolean t2_3 = func(a, N);
        int ans2 = num;
        
        if(t1_1 && t1_2 && t1_3) {
        	System.out.println(Math.min(ans1, ans2));
        }else {
        	System.out.println(-1);
        }
        
        
	}
	static boolean func(int start, int end) {
		boolean[] visited = new boolean[N+1];
		
        int[] D = new int[N+1];
        Arrays.fill(D, Integer.MAX_VALUE);
        
        D[start] = 0;
        
        int min, current = start;
        for(int i=1; i<=N; i++) {
        	min = Integer.MAX_VALUE;
        	// 미방문 정점 중 출발지에서 자신으로의 비용이 최소인 정점 선택
        	for(int j=1; j<=N; j++) {
        		
        		for(Point next : adjList[j]) {
	        		if(!visited[next.num] && min > D[next.num]) {
	        			min = D[next.num];
	        			current = next.num;
	        		}
	        	}        	
        	}
        	
        	// 방문처리
        	visited[current] = true;
        	
        	// 내가 end를 갈 수 있으면 return true
        	if(current == end) {
        		num += D[end];
        		return true;
        	}
        	
        	//선택된 정점으로 부터 다음거 최소
        	for(Point next : adjList[current]) {
        		if(!visited[next.num] && D[next.num] > D[current] + next.weight) {
        			D[next.num] = D[current] + next.weight;
        		}
        	}
        }
        return false;
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
