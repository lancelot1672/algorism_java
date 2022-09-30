package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17136_색종이붙이기 {
	static int[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[10][10];
		
		for(int i=0; i<10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(map[i][j] == 1) {
					for(int n=2; n<=5; n++) {
						if(!check(i, j, n)) {
							System.out.println(n-1);
							//2로 했는데 안돼면 1로 덮어야 함.
							//draw(i, j, n-1);
							break;
						}
					}
				}

			}
		}
	}
	static boolean check(int nowi, int nowj, int n) {
		for(int i=nowi; i<nowi + n; i++) {
			for(int j=nowj; j<nowj + n; j++) {
				if(i >= 10 || j >= 10) {
					return false;
				}
				if(map[i][j] == 0) {
					return false;
				}

			}
		}
		return true;
	}
	static void draw(int nowi, int nowj, int n) {
		for(int i=nowi; i<nowi + n; i++) {
			for(int j=nowj; j<nowj + n; j++) {
				map[i][j] = 0;
			}
		}
	}
	static class Point{
		int i;
		int j;
		int size;
		
		public Point(int i, int j, int size) {
			this.i = i;
			this.j = j;
			this.size = size;
		}
		
		
	}
}
