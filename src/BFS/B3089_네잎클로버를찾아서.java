package BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B3089_네잎클로버를찾아서 {
	static int N,M;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		HashMap<Integer, ArrayList<Integer>> mapX = new HashMap<>();
		HashMap<Integer, ArrayList<Integer>> mapY = new HashMap<>();
		
		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			
			if(mapX.get(X) == null) {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(Y);
				mapX.put(X, list);
			}else {
				ArrayList<Integer> list = mapX.get(X);
				list.add(Y);
				mapX.put(X, list);
			}
			
			if(mapY.get(Y) == null) {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(X);
				mapY.put(Y, list);
			}else {
				ArrayList<Integer> list = mapY.get(Y);
				list.add(X);
				mapY.put(Y, list);
			}
			
		}
		// end input
		char[] orderList = br.readLine().toCharArray();
		
		int nowX = 0, nowY = 0;
		for(char order : orderList) {
			if(order == 'U') {
				ArrayList<Integer> list = mapX.get(nowX);
				//System.out.println(list.toString());
				int minDist = Integer.MAX_VALUE;
				int minY = 0;
				
				for(int next : list) {
					if(next > nowY) {		// 위로 가는거면 더 커야함.
						int dist = Math.abs(nowY - next);
						if(dist == 0) continue;
						if(minDist > dist) { // 더 가까운게 있네?
							minY = next;
							minDist = dist;
						}
					}
				}	// end list
				nowY = minY;
			}// end U
			if(order == 'D') {
				ArrayList<Integer> list = mapX.get(nowX);
				//System.out.println(list.toString());
				int minDist = Integer.MAX_VALUE;
				int minY = 0;
				
				for(int next : list) {
					if(next < nowY) {		// 아래로 가는거면 더 작아야함.
						int dist = Math.abs(nowY - next);
						if(dist == 0) continue;
						if(minDist > dist) { // 더 가까운게 있네?
							minY = next;
							minDist = dist;
						}
					}
				}	// end list
				nowY = minY;
			}
			if(order == 'L') { // 왼쪽으로 가는경우
				ArrayList<Integer> list = mapY.get(nowY);
				//System.out.println(list.toString());
				int minDist = Integer.MAX_VALUE;
				int minX = 0;
				
				for(int next : list) {
					if(next < nowX) {		// 왼쪽으로 가는경우 나보다 작아야함.
						int dist = Math.abs(nowX - next);
						if(dist == 0) continue;
						if(minDist > dist) { // 더 가까운게 있네?
							minX = next;
							minDist = dist;
						}
					}
				}	// end list
				nowX = minX;
			}
			if(order == 'R') { // 오른쪽으로 가는경우
				ArrayList<Integer> list = mapY.get(nowY);
				//System.out.println(list.toString());
				int minDist = Integer.MAX_VALUE;
				int minX = 0;
				
				for(int next : list) {
					if(next > nowX) {		// 오른쪽으로 가는 경우 다음게 더 커야함
						int dist = Math.abs(nowX - next);
						if(dist == 0) continue;
						if(minDist > dist) { // 더 가까운게 있네?
							minX = next;
							minDist = dist;
						}
					}
				}	// end list
				nowX = minX;
			}
		}
		System.out.println(nowX + " " + nowY);
	}
}
