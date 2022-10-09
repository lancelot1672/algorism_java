package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B3954_인터프리터 {
	static int M, C, I;
	static int[] memory;
	static char[] program;
	static char[] programInput;
	static boolean isLoop;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			I = Integer.parseInt(st.nextToken());
			
			memory = new int[M];	// 메모리 배열
			program = br.readLine().toCharArray();
			programInput = br.readLine().toCharArray();
			isLoop = true;
			int pointer = 0;	// 포인터
			int operIndex = 0; 	//연산 인덱스
			int inputCnt = 0;
			int cnt = 0;	//연산 횟수
			
			Stack<Integer> loopIndexStack = new Stack<>();
			//무한 루프가 돈다면 어디서 도나
			int loopIdxFront = 0;
			int loopIdxBack = 0;
			// 프로그램 배열을 하나씩 읽어가며 실행
			while(isLoop) {
				
				switch (program[operIndex]) {
				case '+':
					memory[pointer]++;
					if(memory[pointer] >= 256) {
						memory[pointer] = 0;
					}
					operIndex++;
					break;
					
				case '-':
					if(memory[pointer] > 0) {
						memory[pointer]--;
					}
					operIndex++;
					break;
				case '<':
					if(pointer == 0) {
						pointer = M-1;
					}else {
						pointer--;
					}
					operIndex++;
					break;
				case '>':
					if(pointer == M-1) {
						pointer = 0;
					}else {
						pointer++;
					}
					operIndex++;
					break;
				case '[':
					
					if(memory[pointer] == 0) {
						int nextIdx = findNextIndex(operIndex);
						operIndex = nextIdx;
					}else {
						// 루프
						loopIndexStack.push(operIndex);
					}
					operIndex++;
					break;
				case ']':
					
					if(memory[pointer] != 0) {
						//무한루프 시작
						loopIdxBack = operIndex;
						
						// 앞 괄호로 돌아가기
						int beforeIdx = findBeforeIndex(operIndex);
						operIndex = beforeIdx;
						
						loopIndexStack.push(beforeIdx);
						
						if(cnt >= 50000000) {
							System.out.println("Loops " + loopIndexStack.pop() + " " + loopIdxBack);
							isLoop = false;
						}
					}else {
						//탈출
						if(!loopIndexStack.isEmpty()) {
							loopIndexStack.pop();
						}

					}
					operIndex++;
					break;
					
				case '.':
					operIndex++;
					break;
				case ',':
					// 입력의 마지막인 경우
					if(inputCnt == I) {
						//255 저장
						memory[pointer] = 255;
					}else {
						// 문자를 하나 읽고 가리키는 곳에 저장
						char c = programInput[inputCnt];
						memory[pointer] = c;
						inputCnt++;
					}
					operIndex++;
					break;
				}// end switch
				
				cnt++;	// 연산 횟수 증가
			
				if(operIndex >= C && isLoop) {
					System.out.println("Terminates");
					break;
				}
			}// end While
		}
	}
	private static int findNextIndex(int pointer) {
		int index = 0;
		while(true) {
			pointer++;
			if(program[pointer] == ']') {
				index = pointer;
				break;
			}
			
		}
		return index;
	}
	private static int findBeforeIndex(int pointer) {
		int index = 0;
		while(true) {
			pointer--;
			if(program[pointer] == '[') {
				index = pointer;
				break;
			}
			
		}
		return index;
	}
}
