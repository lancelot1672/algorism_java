package adj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1504_특정한최단경로_PQ {
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
		PriorityQueue<Point> pq = new PriorityQueue<>((p1, p2) -> p1.weight - p2.weight);
        int[] D = new int[N+1];
        Arrays.fill(D, Integer.MAX_VALUE);
        
        pq.add(new Point(start, 0));
        D[start] = 0;
        
        int min, current = start;
        while(!pq.isEmpty()) {
        	Point now = pq.poll();
        	
        	if(now.num == end) {
        		num += D[end];
        		return true;
        	}
        	// 경유지 찾기
        	for(Point next : adjList[now.num]) {
        		if(D[next.num] > D[now.num] + next.weight) {
        			D[next.num] = D[now.num] + next.weight;
        			pq.add(new Point(next.num, D[next.num]));
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
