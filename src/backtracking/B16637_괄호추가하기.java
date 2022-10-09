package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;

public class B16637_괄호추가하기 {
	static int[] numArr;
	static char[] oper;
	static int[] output;
	static Queue<Integer> q;
	static ArrayList<Integer> numList;
	static int N;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		N = Integer.parseInt(br.readLine());
		
	
		
		char[] arr = br.readLine().toCharArray();
		
		//만약 숫자가 1개거나 
		if(N == 1) {
			System.out.println(arr[0]);
			System.exit(0);
		}
		// 숫자가 2개거나
		if(N == 3) {
			int n1 = arr[0] - '0';
			int n2 = arr[2] - '0';
			char oper = arr[1];
			
			int result = calc(n1, n2, oper);
			System.out.println(result);
			System.exit(0);
		}
		numArr = new int[N/2 + 1];

		oper = new char[N/2];	// 1개면 더해서 출력 끝
		output = new int[oper.length - 1];
		for(int c = 0; c<N; c++) {
			if(c % 2 == 0) {	// 숫자
				numArr[c / 2] = arr[c] - '0';
			}else {	// 연산자
				oper[c / 2] = arr[c];
			}
		}

//		System.out.println(Arrays.toString(numArr));
//		System.out.println(Arrays.toString(oper));
	
		dfs(0, 0, false);
		System.out.println(max);
	}
	// 연산자 기준으로 괄호를 묶고 연속된 괄호를 방지
	static void dfs(int idx, int cnt, boolean before) {
		if(idx == oper.length) {
			//System.out.println(Arrays.toString(output));
			
			numList = new ArrayList<>();
			for(int n : numArr) {
				numList.add(n);
			}
			ArrayList<Integer> operIdx = new ArrayList<>();
			ArrayList<Character> otherOper = new ArrayList<>();
			for(int i=0; i<output.length; i++) {
				if(output[i] != 0) {
					operIdx.add(output[i]);
					
				}
			}
			//남은 연산자
			for(int i=1; i<=oper.length; i++) {
				if(!operIdx.contains(i)) {
					otherOper.add(oper[i - 1]);
				}
			}
			// 뒤에서 부터 실행
			for(int i=operIdx.size()-1; i>= 0; i--) {
				int index = operIdx.get(i);
				char c = oper[ index - 1];	// 연산자
				int n1 = numList.remove(index - 1);
				int n2 = numList.remove(index - 1);
				
				int result = calc(n1, n2, c);
				
				numList.add(index - 1, result);
			}
//			System.out.println(numList.toString());
//			System.out.println(otherOper.toString());
			
			// 남은 연산자 가지고 연산 수행
			int sum = numList.get(0);
			for(int i=0; i<otherOper.size(); i++) {
				sum = calc(sum, numList.get(i+1), otherOper.get(i));
			}
			max = Math.max(max, sum);
			return;
		}
		
		if(!before) {	// 전에 안 뽑고왔다면
			output[cnt] = idx+1;
			dfs(idx+1, cnt+1, true);	 //이번건 뽑고가
			output[cnt] = 0;
		}
		dfs(idx+1, cnt, false);	//안뽑고가
	}
	static int calc(int n1, int n2, char c) {
		int num = 0;
		switch (c) {
		case '+':
			num =  n1+n2;
			break;
		case '-':
			num =  n1-n2;
			break;
		case '*':
			num =  n1*n2;
			break;
		}
		return num;
	}
}	
