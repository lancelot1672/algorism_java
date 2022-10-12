package professor;

import java.util.Scanner;

public class B15961_회전초밥_교수님 {
	static int[] sushi;
	static int N, d, k, c; 	// 접시수, 초밥최대번호, 연속접시 수, 쿠폰번호
	static int ans;	//최대 초밥 가짓수
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		d = sc.nextInt();
		k = sc.nextInt();
		c = sc.nextInt();
		
		sushi = new int[N];
		for(int i=0; i<N; i++) {
			sushi[i] = sc.nextInt();
		}
		int[] cnt = new int[d+1];	// 내가 먹은 초밥 카운트.
		cnt[c]++;	//쿠폰 초밥은 일단 먹고 시작하자.
		
		int tmp = 1;
		
		for(int i=0; i<k; i++) {
			if(cnt[sushi[i]] == 0) {	// 오 처음 먹는 초밥쿠
				tmp++;
			}
			cnt[sushi[i]]++;	// 처음 먹든 두번째 먹든 해당번호 초밥 카운트 ++
			
		}
		for(int i=1; i<N; i++) {
			cnt[sushi[i-1]]--;	// 현재 시작접시 앞 접시의 초밥 카운트 빼기
			if(cnt[sushi[i-1]] == 0) tmp--;
			
			if(cnt[sushi[(i+k-1) % N]] == 0) tmp++;
			cnt[sushi[(i+k-1) % N]]++;
			ans = Math.max(ans, tmp);
		}
	}
}
