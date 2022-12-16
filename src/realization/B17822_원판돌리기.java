package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B17822_원판돌리기 {
	static ArrayList<Integer>[] deque;
	static Rotate[] rotateList;
	static int N,M,T;
	public static void main(String[] args) throws Exception{
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 원판 수
		M = Integer.parseInt(st.nextToken());	// 원판 길이
		T = Integer.parseInt(st.nextToken());	// T번 회전
		
		deque = new ArrayList[N+1];
		rotateList = new Rotate[T];
		
		for(int i=1; i<=N; i++) {
			deque[i] = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				deque[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());	//배수
			int d = Integer.parseInt(st.nextToken());	//방향
			int k = Integer.parseInt(st.nextToken()); 	// 몇 칸
			
			rotateList[t] = new Rotate(x, d, k);
		}
//		rotate(rotateList[0]);
//		print();
		for(int t=0; t<T; t++) {
			rotate(rotateList[t]);
//			System.out.println("--- 돌린 후 ---");
//			print();
			boolean[][] visited = new boolean[N+1][M];
			int  cnt = 0;
			//원판에 수가 남아 있으면, 인접하면서 수가 같은 것을 모두 찾는다.
			for(int a=1; a<=N; a++) {
				for(int b=0; b<M; b++) {
					int now = deque[a].get(b);
					if(now == -1) continue;
					if(a != N) {	//맨 바깥의 원판은 없음.
						if(now == deque[a+1].get(b)) {
							visited[a][b] = true;
							visited[a+1][b] = true;
							cnt++;
						}
					}
					// 양 옆 확인
					if(b==0) {	// 맨 처음 일때 맨뒤꺼도 확인
						
						
						if(now == deque[a].get(b+1)) {
							visited[a][b+1] = true;
							cnt++;
						}
						if(now == deque[a].get(M-1)) {
							visited[a][M-1] = true;
							cnt++;
						}
					}else if(b == M-1) {
						if(now == deque[a].get(b-1)) {
							visited[a][b-1] = true;
							cnt++;
						}
						if(now == deque[a].get(0)) {
							visited[a][0] = true;
							cnt++;
						}
					}else {
						if(now == deque[a].get(b+1)) {
							visited[a][b+1] = true;
							cnt++;
						}
						if(now == deque[a].get(b-1)) {
							visited[a][b-1] = true;
							cnt++;
						}
					}
				}
			}// 끝
			if(cnt == 0) {
				int sum = 0;
				int c= 0;
				for(int a=1; a<=N; a++) {
					for(int b=0; b<M; b++) {
						int num = deque[a].get(b);
						if(num == -1) continue;
						sum += num;
						c++;
					}
				}
				float aver = (float) sum / c;
				for(int a=1; a<=N; a++) {
					for(int b=0; b<M; b++) {
						int num = deque[a].get(b);
						if(num == -1) continue;
						if(num < aver) {
							deque[a].remove(b);
							deque[a].add(b, num+1);
						}else if(num > aver){
							deque[a].remove(b);
							deque[a].add(b, num-1);
						}
					}
				}
				
			}else {
				for(int a=1; a<=N; a++) {
					for(int b=0; b<M; b++) {
						if(visited[a][b]) {
							deque[a].remove(b);
							deque[a].add(b, -1);
						}
					}
				}
			}
			//
//			System.out.println("---- 수 없앤 후 ---");
//			print();
		}
		int total=0;
		for(int a=1; a<=N; a++) {
			for(int b=0; b<M; b++) {
				int num = deque[a].get(b);
				if(num == -1) continue;
				total += num;
			}
		}
		System.out.println(total);
	}
	static void rotate(Rotate r) {
		//번호가 x의 배수인 원판을 d 방향으로 k 칸 회전
		for(int n=1; n<=N; n++) {
			if(n % r.x != 0) continue;
			
			// K칸 회전
			for(int k=0; k<r.k; k++) {
				if(r.d == 1) {	// 시계 방향
					int temp = deque[n].remove(0);
					deque[n].add(temp);
				}else {	// 반시계 방향
					int temp = deque[n].remove(M-1);
					deque[n].add(0, temp);
				}
			}
		}
	}
	static class Rotate{
		int x;
		int d;
		int k;
		public Rotate(int x, int d, int k) {
			this.x = x;
			this.d = d;
			this.k = k;
		}
		
	}
	static void print() {
		System.out.println("------------------------");
		for(int a=1; a<=N; a++) {
			for(int b=0; b<M; b++) {
				int num = deque[a].get(b);
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}
}
