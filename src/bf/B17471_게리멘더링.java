package bf;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B17471_게리멘더링 {
	static int N;
	static boolean[] selected;
	static int[] population;
	static ArrayList<Integer>[] adjList;
	static ArrayList<Integer> listA;
	static ArrayList<Integer> listB;
	static long min;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		population = new int[N+1];
		selected = new boolean[N+1];
		adjList = new ArrayList[N+1];
		min = Integer.MAX_VALUE;
		
		for(int n=1; n<=N; n++) {
			population[n] = sc.nextInt();
		}
		for(int n=1; n<=N; n++) {
			adjList[n] = new ArrayList<>();
		}
		
		for(int n=1; n<=N; n++) {
			int nn = sc.nextInt();
			for(int j=1; j<=nn; j++) {
				int to = sc.nextInt();
				adjList[n].add(to);
				adjList[to].add(n);
			}
		}
		
		subset(1);
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}

	}
	static void subset(int cnt) {
		if(cnt == N+1) {
			listA = new ArrayList<>();
			listB = new ArrayList<>();
			
			for(int i=1; i<=N; i++) {
				if(selected[i]) {
					listA.add(i);
				}else {
					listB.add(i);
				}
			}
		
			if(listA.size() == 0 || listB.size() == 0) {
				return;
			}
			if(isConnection(listA) && isConnection(listB)) {
				
				long sumA = 0;
				for(int n : listA) {
					sumA += population[n];
				}
				
				long sumB = 0;
				for(int n : listB) {
					sumB += population[n];
				}
				min = Math.min(Math.abs(sumA - sumB), min);
				
			}
			return;
		}
		
		selected[cnt] = true;
		subset(cnt+1);
		selected[cnt] = false;
		subset(cnt+1);
	}
	static boolean isConnection(ArrayList<Integer> list) {	// 리스트의 것들이 연결되어 있으면 true
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		int start = list.get(0);
		q.add(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int next : adjList[now]) {
				if(!visited[next] && list.contains(next)) {
					visited[next] = true;
					q.add(next);
				}
			}
		}// end while
		
		//연결되어 있는지 확인
		for(int num : list) {
			if(!visited[num]) return false;
		}
		return true;
	}
}
