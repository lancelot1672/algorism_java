package programmers.heap;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class PRO_디스크_컨트롤러 {
	public static void main(String[] args) {
		
		solution(new int[][] {{24, 10}, {28, 39}, {43, 20}, {37, 5}, {47, 22}, {20, 47}, {15, 34}, {15, 2}, {35, 43}, {26, 1}} );
	}
	public static int solution(int[][] jobs) {
        int answer = 0;
        
        ArrayList<int[]> jobList = new ArrayList<>();
        
        // 맨 처음 것들 큐
        PriorityQueue<int[]> stf = new PriorityQueue<>((j1, j2) -> j1[0] - j2[0]);
        
        for(int[] job : jobs){
            stf.add(job);
        }
        // job
        PriorityQueue<int[]> sjf = new PriorityQueue<>((j1, j2) -> j1[1] - j2[1]);
        int end = 0;
        int sum = 0;
        
        int count = 0;
        while(true){
        	PriorityQueue<int[]> temp = new PriorityQueue<>((j1, j2) -> j1[0] - j2[0]);
            // 작업 시간이 짧은 것 중 end 보다 작거나 같은 것
            int size = stf.size();
            for(int s=0; s<size; s++){
                int[] job = stf.poll();
                //만약 대기 하는 것들이 있다면 pq에 넣기
                if(end >= job[0]){
                    sjf.add(job);
                }else{
                    temp.add(job);
                }
            }
            
            // sjf 큐가 비어있지 않다면 
            if(!sjf.isEmpty()){
                int[] job = sjf.poll();
                sum += (end - job[0]) + job[1];
                end += job[1];  // 작업의 소요시간 더하기
                count++;
                if(count == jobs.length) break;
            }
            
            // while(!sjf.isEmpty()){
            //     // 나머지 임시 큐에 집어 넣기
            //     temp.add(sjf.poll());
            // }
            stf = temp;
        }
        answer = sum / jobs.length;
        System.out.println(answer);
        return answer;
    }
	    
}
