package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import javax.print.attribute.standard.Media;

public class B6987_월드컵 {
	static int[][] arr;
	static ArrayList<Team> list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 4;
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			arr = new int[6][3];
			list = new ArrayList<>();
			
			boolean flag = true;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<6; i++) {
				arr[i][0] = Integer.parseInt(st.nextToken());	//승
				arr[i][1] = Integer.parseInt(st.nextToken());	//무
				arr[i][2] = Integer.parseInt(st.nextToken());	//패
				
				list.add(new Team(arr[i][0], arr[i][1], arr[i][2]));
			}
			//승 갯수로 내림차순 솔트
			Collections.sort(list);
			for(int i=0; i<6; i++) {
				Team me = list.get(i);
				
				for(int j=0; j<6; j++) {
					if(me.w == 0) {	// 내 팀 승 스코어 없으면 끝.
						break;
					}
					if(i != j) {	//자기 팀 스코어 까면 안돼~
						Team you = list.get(j);
						if(you.l > 0) {	// 다른 팀 패 스코어가 있으면
							// 내 승 스코어랑 하나 까자
							me.w--;		//내 승 스코어
							you.l--;	//상대 패 스코어
						}
					}
				}
				if(me.w != 0) {
					flag = false;
					break;
				}
				
				for(int j=0; j<6; j++) {
					if(me.l == 0) {	//내 팀 패 스코어 없으면 끝
						break;
					}
					if(i != j) {	//자기 팀 스코어 까면 안돼~
						Team you = list.get(j);
						if(you.w > 0) {	// 다른 팀 승 스코어가 있으면
							// 내 패 스코어랑 하나 까자
							me.l--;	// 내 패 스코어
							you.w--;	// 상대 승 스코어
						}
					}
				}
				if(me.l != 0) {
					flag = false;
					break;
				}
				for(int j=0; j<6; j++) {
					if(me.d == 0) {	//내 팀 무승부 없으면 끝
						break;
					}
					if(i != j) {	//자기 팀 스코어 까면 안돼~
						Team you = list.get(j);
						if(you.d > 0) {	// 다른 팀 무승부 스코어가 있으면
							// 내 패 스코어랑 하나 까자
							me.d--;	// 내 무승부 스코어
							you.d--;	// 상대 무승부 스코어
						}
					}
				}
				if(me.d != 0) {
					flag = false;
					break;
				}
				
				//print();
			}

			// check
			if(flag) {
				sb.append("1 ");
			}else {
				sb.append("0 ");
			}
		}
		System.out.println(sb.toString());

		
	}
	static void print() {
		System.out.println();
		for(int i=0; i<6; i++) {
			Team t = list.get(i);
			System.out.println(t.w + " " + t.d + " " + t.l);
		}
	}
	static boolean check() {
		for(int i=0; i<6; i++) {
			for(int j=0; j<3; j++) {
				if(arr[i][j] != 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	static class Team implements Comparable<Team>{
		int w;
		int d;
		int l;
		public Team(int w, int d, int l) {
			this.w = w;
			this.d = d;
			this.l = l;
		}
		@Override
		public int compareTo(Team o) {
			return o.w - this.w;
		}
		
	}
}
