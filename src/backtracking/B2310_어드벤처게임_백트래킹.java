package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B2310_어드벤처게임_백트래킹 {
	static LinkedList<Integer>[] adjList;
	static int N;
	static ArrayList<Room> list;
	static boolean flag;
	static int money;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) {
				break;
			}
			// 초기화
			adjList = new LinkedList[N+1];
			list = new ArrayList<>();
			
			visited = new boolean[N+1];
			
			money = 0;
			flag = false;
			
			for(int i=1; i<=N; i++) {
				adjList[i] = new LinkedList<Integer>();
			}

			for(int n=1;n<=N; n++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				
				char c = st.nextToken().charAt(0);  // 빈방, 레프, 트롤
				int npc = 0;
				if(c == 'E') {
					npc = 0;
				}else if( c == 'L') {
					npc = 1;
				}else {
					npc = -1;
				}
				int quest = Integer.parseInt(st.nextToken()); // 퀘스트 금액 - 레프, 트롤에 따라 받기도 하고 주기도 한다.
				
				list.add(new Room(n, npc, quest));
				while(true) {
					int num =  Integer.parseInt(st.nextToken()); 	// 갈 수 있는 방 번호 
					
					if(num == 0) {		// 0 이 나올때까지 반복한다.
						break;
					} // end if
					
					if(num == n) {	// 제자리로 가도 뭐...
						continue;
					}
					adjList[n].add(num);
				} // end while
			} // end for
			
			dfs(1);
			
			if(flag) {
				System.out.println("Yes");
			}else {
				System.out.println("No");
			}
		}

	}
	static boolean[] visited;
	static void dfs(int roomIdx) {
		//트롤 인지 확인
		if(list.get(roomIdx-1).npc == -1) {
			if(list.get(roomIdx-1).quest <= money) {
				money -= list.get(roomIdx-1).quest;
			}else {
				return;
			}
		}
		//트롤에게 통행료 냈으니 통과 여부 결정
		if(roomIdx == list.size()) {
			flag = true;
			return;
		}
		
		//재귀
		if(list.get(roomIdx-1).npc == 1) {		//돈 내놔.
			if(list.get(roomIdx-1).quest > money) {
				money = list.get(roomIdx-1).quest;
			}
		}
		
		visited[roomIdx-1] = true;
		for(int n : adjList[roomIdx]) {
			if(!visited[n]) {
				dfs(n);
			}
		}
		visited[roomIdx-1] = false;
		
	}
	static class Room{
		int num;
		int npc;
		int quest;
		
		public Room(int num, int npc, int quest) {
			this.num = num;
			this.npc = npc;
			this.quest = quest;
		}
	}
}
