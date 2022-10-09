package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, ans, amount, connected;
    static int[][] map;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static ArrayList<Point> cell;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
        	sb =  new StringBuilder();
            N = Integer.parseInt(br.readLine());

            ans = Integer.MAX_VALUE;
            amount = 0;
            map = new int[N][N];
            visited = new boolean[N][N];
            cell = new ArrayList<Point>();
            connected = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int area = Integer.parseInt(st.nextToken());
                    map[i][j] = area;
                    if (area == 1 && i != 0 && j != 0 && i != N-1 && j != N-1) {
                        amount++;
                        cell.add(new Point(i, j)); // 셀 위치
                    }
                }
            }
            //System.out.println(amount);
//            print(map);
            // 입력 종료

            dfs(0, 0, 0);
            if (ans == Integer.MAX_VALUE) ans = 0;

            // 출력
            System.out.println("#" + tc + " " + ans);
        }
        
    }
    static boolean check(Point n, int dir) {		//연결할 수 있는지 체크 해보고
		int nowi = n.i;
		int nowj = n.j;
		
		while(true) {
			int nexti = nowi + di[dir];
			int nextj = nowj + dj[dir];
			
			if(nexti < 0 || nexti >= N || nextj < 0 || nextj >=N) {		//범위 초과
				break;
			}
			if(map[nexti][nextj] == 1 || map[nexti][nextj] == 2) {	//나 연결 못해~
				return false;
			}
			nowi = nexti;
			nowj = nextj;
		}
		return true;
	}
	static int draw(Point n, int dir) {		//기준으로 부터 상 하 좌 우 그려보자
		int nowi = n.i;
		int nowj = n.j;
		
		int count=0;
		while(true) {
			int nexti = nowi + di[dir];
			int nextj = nowj + dj[dir];
			
			if(nexti < 0 || nexti >= N || nextj < 0 || nextj >=N) {		//범위 초과
				break;
			}
			map[nexti][nextj] = 2;	//연결해~
			count++;
			
			nowi = nexti;
			nowj = nextj;
		}
		return count;
	}
	static void back(Point n, int dir) {		//기준으로 부터 상 하 좌 우 그려보자
		int nowi = n.i;
		int nowj = n.j;
		
		int count=0;
		while(true) {
			int nexti = nowi + di[dir];
			int nextj = nowj + dj[dir];
			
			if(nexti < 0 || nexti >= N || nextj < 0 || nextj >=N) {		//범위 초과
				break;
			}
			if(map[nexti][nextj] == 1 || map[nexti][nextj] == 0) {	//나 2만 지울거야
				return;
			}
			map[nexti][nextj] = 0;
			count++;
			nowi = nexti;
			nowj = nextj;
		}
		
	}

    private static void dfs(int idx, int cnt, int connect) {
        if (idx == amount) {
        	//System.out.println(connect + "," + cnt);
            if (connect > connected) {
                ans = cnt;
                connected = connect;
            }
            if (connect == connected) {
                ans = Math.min(ans, cnt);
            }
            return;
        }

        int nowi = cell.get(idx).i;
        int nowj = cell.get(idx).j;

       // print(map);
//        System.out.println(ans);

        for (int d = 0; d < 4; d++) {
            if (check(cell.get(idx), d)) {
                int dist = draw(cell.get(idx), d);
                dfs(idx + 1, cnt + dist, connect + 1);
                back(cell.get(idx), d);
            }else {
            	dfs(idx+1, cnt, connect);
            }

        }
    }
//
//    private static int draw(int nowi, int nowj, int d) {
//        int dist = 0;
//
//        for (int i = 0; i < N; i++) {
//            int nexti = nowi + di[d] * (i+1);
//            int nextj = nowj + dj[d] * (i+1);
//            if (inBoundary(nexti, nextj)) {
//                dist++;
//                map[nexti][nextj] = 2;
//            }
//        }
//
//        return dist;
//    }
//
//    private static boolean check(int nowi, int nowj, int d) {
//
//        for (int i = 0; i < N; i++) {
//            int nexti = nowi + di[d] * (i+1);
//            int nextj = nowj + dj[d] * (i+1);
//            if (inBoundary(nexti, nextj)) {
//                if (map[nexti][nextj] != 0) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    private static boolean inBoundary(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }

//    private static void rollback(int nowi, int nowj, int d) {
//        for (int i = 0; i < N; i++) {
//            int nexti = nowi + di[d] * (i+1);
//            int nextj = nowj + dj[d] * (i+1);
//            if (inBoundary(nexti, nextj)) {
//                if (map[nexti][nextj] == 2) {
//                    map[nexti][nextj] = 0;
//                }
//            }
//        }
//    }

    private static void print(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

//    private static void copy(boolean[][] copy, boolean[][] origin) {
//        for (int i = 0; i < N; i++) {
//            System.arraycopy(origin[i], 0, copy[i], 0, N);
//        }
//    }

    private static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}