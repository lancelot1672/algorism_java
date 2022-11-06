package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 같은 알파벳 구성을 갖는 경우
// 한문자 차이가 나는 경우

public class B2607_비슷한단어 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int N = Integer.parseInt(br.readLine());
		
		char[]  firstWord = br.readLine().toCharArray();
		int[] firstWordCnt = new int[26];
		int cnt = 0;
		for(int i=0; i<firstWord.length; i++) {
			if(firstWordCnt[firstWord[i] - 'A'] == 0) {	// 처음 나오는 알파벳
				cnt++;		// 알파벳 갯수
			}
			firstWordCnt[firstWord[i] - 'A']++;
		}
		
		
		for(int n=1; n<N; n++) {
			int[] secondWordCnt = new int[26];
			char[]  secondWord = br.readLine().toCharArray();
			boolean flag = false;
			
			for(int i=0; i<secondWord.length; i++) {
				if(secondWordCnt[secondWord[i] - 'A'] == 0) {	// 처음 나오는 알파벳
					cnt++;		// 알파벳 갯수
				}
				secondWordCnt[secondWord[i] - 'A']++;
				
				//해당 알파벳의 갯수가 원본과 다르면
				
				// 하나만 달라야함.
				if(secondWordCnt[secondWord[i] - 'A']  + 1 < firstWordCnt[secondWord[i] - 'A'] ||
						secondWordCnt[secondWord[i] - 'A']  + 1 > firstWordCnt[secondWord[i] - 'A'] 	) {
					break;
				}
				//하나 차이나면
				// flag => true라는 것은 이미 전에 하나차이나는 것이 있었으므로 
				if(secondWordCnt[secondWord[i] - 'A']  + 1 == firstWordCnt[secondWord[i] - 'A'] && !flag) {
					flag = true;
				}
			}
		}
	}
}
