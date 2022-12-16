package programmers.Queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class 프린터 {
	public static void main(String[] args) {
//		int[] priorities = {2, 1, 3 ,2};
		int[] priorities = {1, 1, 9, 1, 1, 1};
		solution(priorities, 0);
	}
    static int solution(int[] priorities, int location) {
        int answer = 0;
        ArrayList<Integer> list = new ArrayList<>();
        Queue<Integer> pq = new LinkedList<>();
        Queue<Integer> print = new LinkedList<>();
        
        int cnt = 0;
        
        for(int num : priorities) {
        	list.add(num);
        	pq.add(num);
        	print.add(cnt++);
        }
        Collections.sort(list, Collections.reverseOrder());
        
        System.out.println(list.toString());
        while(!pq.isEmpty()) {
        	int nowPri = list.get(0);
        	
        	int priority = pq.poll();	// 검사해 볼 우선 순위
        	int printNum = print.poll();	// 인덱스
        	
        	if(nowPri == priority) {	// 지금 프린트 될 문서니?
        		answer++;
        		list.remove(0);
        		if(printNum == location) {	// 내가 요청한 문서니? 끝
        			break;
        		}
        	}else {
        		pq.add(priority);
        		print.add(printNum);
        	}
        }
        System.out.println(answer);
        return answer;
    }
}
