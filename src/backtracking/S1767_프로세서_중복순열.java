package backtracking;

import java.util.ArrayList;
import java.util.Scanner;

public class S1767_프로세서_중복순열{
	static int[][] map;
	static int[] output;
	static int N;
	static ArrayList<Node> list;
	static int connect;
	static int total_length;
	static int max;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<= T; tc++) {
			N = sc.nextInt();				// N x N
			
			map = new int[N][N];	//프로세서 맵
			
			list = new ArrayList<>();
			
			//초기화
			connect = 0;
			max = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
					
					// 중간에 있는 프로세서 넣기
					if(map[i][j] == 1) {
						if(i != 0 && i != N-1 && j != 0 && j != N-1) {	
							list.add(new Node(i,j));
						}
					}
				}
			}//end for
			
			output = new int[list.size()];		//중복순열에 사용
			perm(0);
			System.out.println(max);
		}
	}
	static int[][] copy;
	static int[] di = { -1, 0, +1, 0};		//상 우 하 좌
	static int[] dj = {0, +1, 0, -1};

	static void perm(int idx) {
		if(idx == list.size()) {
			total_length = 0;
			int con = 0;
			copy = deepcopy(map);		//배열 복사해서 돌려봐
			
			for(int i=0; i<list.size(); i++) {		//프로세서 하나씩 그려보지뭐
				Node n = list.get(i);
				if(check(n, output[i])) {
					draw(n, output[i]);
					con++;
				}
			}
			if(con > connect) {
				connect = con;	//총 연결 수
				max = total_length;
				
			}else if(con == connect) {
				if(max > total_length) {
					max = total_length;
				}
			}
			return;
		}
		for(int i=0; i<4; i++) {
			output[idx] = i;
			perm(idx+1);
		}
	}
	static boolean check(Node n, int dir) {		//연결할 수 있는지 체크 해보고
		int nowi = n.i;
		int nowj = n.j;
		
		while(true) {
			int nexti = nowi + di[dir];
			int nextj = nowj + dj[dir];
			
			if(nexti < 0 || nexti >= N || nextj < 0 || nextj >=N) {		//범위 초과
				break;
			}
			if(copy[nexti][nextj] == 1 || copy[nexti][nextj] == 2) {	//나 연결 못해~
				return false;
			}
			nowi = nexti;
			nowj = nextj;
		}
		return true;
	}
	static void draw(Node n, int dir) {		//기준으로 부터 상 하 좌 우 그려보자
		int nowi = n.i;
		int nowj = n.j;
		
		int count=0;
		while(true) {
			int nexti = nowi + di[dir];
			int nextj = nowj + dj[dir];
			
			if(nexti < 0 || nexti >= N || nextj < 0 || nextj >=N) {		//범위 초과
				break;
			}
			copy[nexti][nextj] = 2;	//연결해~
			count++;
			
			nowi = nexti;
			nowj = nextj;
		}
		total_length += count;
	}
	static int[][] deepcopy(int[][] origin) {
		int[][] copy = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				copy[i][j] = origin[i][j];
			}
		}
		return copy;
		
	}
	static void print(int[][] arr) {
		System.out.println("-----------------------------------------");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	static class Node{
		int i;
		int j;
		public Node(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
	}
	
}
