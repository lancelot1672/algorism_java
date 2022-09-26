package adj;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P다익스트라_인접행렬 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int V = Integer.parseInt(br.readLine());
		
		int[][] adjMatrix = new int[V][V];
		
		for(int i=0; i<V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<V; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
					
		}//end Input
		
		// start -> end로의 최단경로
		int start = 0;
		int end = V-1;
		
		int[] D = new int[V];		//출발지에서 자신으로 오는데 소요되는 최소 비용
		boolean[] visited = new boolean[V];	//처리한 정점 여부
		
		Arrays.fill(D, Integer.MAX_VALUE);
		
		// 출발정점 처리
		D[start] = 0;
		
		int min, current = 0;
		
		for(int i=0; i<V; i++) {
			//step1. 미방문 정점 중 출발지에서 자신으로의 비용이 최소인 정점 선택
			min = Integer.MAX_VALUE;
			for(int j=0; j<V; j++) {
				if(!visited[j] && min > D[j]) {
					min = D[j];
					current = j;
				}
			}
			
			//step2. 방문처리
			visited[current] = true;
			
			// 목적지가 만 구할거면!
			if(current == end) break;
			
			// step3. 선택된 정점을 경유지로 해서 미방문 정점들로 가는 비용을 따져보고
			// 기존 최적해 보다 유리하면 갱신
			for(int j=0; j<V; j++) {
				if(!visited[j] && 
					adjMatrix[current][j] > 0 && 
					D[j] > D[current] + adjMatrix[current][j]) {
					
					D[j] = D[current] + adjMatrix[current][j];
				}
			}
			
		}
		System.out.println(Arrays.toString(D));
	}
}
