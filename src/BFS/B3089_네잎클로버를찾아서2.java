package BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B3089_네잎클로버를찾아서2 {
	static int N,M;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ArrayList<Point> clova = new ArrayList<>();
		clova.add(new Point(0,0));
		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			
			clova.add(new Point(X,Y));
		}
		// 연결하기
		for(Point now : clova) {
			Point minUP = null;
			int minUpDist = Integer.MAX_VALUE;
			Point minDown = null;
			int minDownDist = Integer.MAX_VALUE;
			Point minRight = null;
			int minRightDist = Integer.MAX_VALUE;
			Point minLeft = null;
			int minLeftDist = Integer.MAX_VALUE;
			
			for(Point next : clova) {
				if(now == next) continue;
				if(now.x == next.x && now.y != next.y) {
					if(now.y < next.y) {	// 위에 있는 것.
						if(minUpDist > next.y - now.y) {
							minUpDist = next.y - now.y;
							minUP = next;
						}
						
					}else if(now.y > next.y){	// 아래에 있는 것
						if(minDownDist > now.y - next.y) {
							minDownDist = now.y - next.y;
							minDown = next;
						}
					}
				}
				if(now.y == next.y && now.x != next.x) {
					if(now.x < next.x) {	//오른쪽에 있는 것
						if(minRightDist > next.x - now.x) {
							minRightDist = next.x - now.x;
							minRight = next;
						}
					}else if(now.x > next.x) {	// 왼쪽에 있는 것
						if(minLeftDist > now.x - next.x) {
							minLeftDist = now.x - next.x;
							minLeft = next;
						}
					}
				}
			}
			now.UP = minUP;
			now.DOWN = minDown;
			now.LEFT = minLeft;
			now.RIGHT = minRight;
		}
		
		char[] orderList = br.readLine().toCharArray();
		Point now = clova.get(0);	/// 0, 0
		for(char order : orderList) {
			if(order == 'U') {
				now = now.UP;
			}else if(order == 'D') {
				now = now.DOWN;
			}else if(order == 'R') {
				now = now.RIGHT;
			}else if(order == 'L') {
				now = now.LEFT;
			}
		}
		System.out.println(now.x + " " + now.y);
	}
	static class Point{
		int x;
		int y;
		Point UP;
		Point LEFT;
		Point RIGHT;
		Point DOWN;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
