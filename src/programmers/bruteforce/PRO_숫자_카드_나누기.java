package programmers.bruteforce;

import java.util.ArrayList;

public class PRO_숫자_카드_나누기 {
	public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        // arrayA[0]의 공약수 구하기
        ArrayList<Integer> divisorA = new ArrayList<>();
        for(int i=2; i<= arrayA[0]; i++){
            if(arrayA[0] % i == 0){
                divisorA.add(i);
            }
        }
        
        // arrayB[0]의 공약수 구하기
        ArrayList<Integer> divisorB = new ArrayList<>();
        for(int i=2; i<= arrayB[0]; i++){
            if(arrayB[0] % i == 0){
                divisorB.add(i);
            }
        }
        
        for(int num : divisorA){
            // 다 나누어져야함.
            boolean checkA = isDivision(arrayA, num);
            if(!checkA) continue;
            
            boolean checkB = notDivision(arrayB, num);
            
            if(checkB){ // 둘다 통과하면?
                answer = Math.max(answer, num);
            }
        }
        
        for(int num : divisorB){
            // 다 나누어져야함.
            boolean checkB = isDivision(arrayB, num);
            if(!checkB) continue;
            
            boolean checkA = notDivision(arrayA, num);
            
            if(checkA){ // 둘다 통과하면?
                answer = Math.max(answer, num);
            }
        }
        return answer;
    }
    public boolean isDivision(int[] array, int num){
        for(int i=0; i<array.length; i++){
            if(array[i] % num != 0) return false;
        }
        return true;
    }
    public boolean notDivision(int[] array, int num){
        for(int i=0; i<array.length; i++){
            if(array[i] % num == 0) return false;
        }
        return true;
    }
}
