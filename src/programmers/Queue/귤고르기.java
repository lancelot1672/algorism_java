package programmers.Queue;

import java.util.PriorityQueue;

public class 귤고르기 {
	public static void main(String[] args) {
	}
	public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        int[] count = new int[10000001];    // 귤 사이즈
        
        //갯수를 센다.
        for(int tan : tangerine){
            count[tan]++;
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int c : count){
            if(c == 0) continue;
            pq.add(c);
        }
        
        // k개 남기고 다 삭제
        int removeCnt = tangerine.length - k;
        
        while(true){
            int tanCnt = pq.poll();    // 한개씩 꺼내서 삭제
            if(removeCnt - tanCnt >= 0){
                removeCnt -= tanCnt;
            }else{
                break;
            }
        }
        answer = pq.size()+1;
        return answer;
    }
}
