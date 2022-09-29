package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class B16637_괄호추가하기 {
	static int N;
	static int C; // 뽑아야 하는 수
	static char[] oper;
	static int[] num;
	static int[] output;
	static ArrayList<String> list = new ArrayList<>();
	static ArrayDeque<Integer> numQ = new ArrayDeque<>();
	static ArrayDeque<Character> operQ = new ArrayDeque<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		oper = new char[N/2];
		num = new int[N/2 + 1];
		
		String input = br.readLine();
		
		for(int i=0; i<input.length(); i++) {
			int idx = 0;
			if(i % 2 == 0) {
				numQ.add(input.charAt(i) - '0');
			}else {
				operQ.add(input.charAt(i));
				idx++;
			}
		}
		if(oper.length % 2 == 0) {
			C = oper.length / 2;	//연산자 갯수 / 2 만큼 괄호 가능.
		}else {
			C = oper.length / 2 + 1;
		}
		num = new int[oper.length];
		output = new int[C];
		for(int i=0; i<oper.length; i++) {
			num[i] = i;
		}
		comb(0,0);
	}
	static Queue<String> q = new LinkedList<>();
	static void comb(int idx, int cnt) {
		if(cnt == C) {
			System.out.println(Arrays.toString(output));
			char[] cArr = new char[oper.length];
			
			for(int i=0; i<oper.length; i++) {
				cArr[i] = '0';
			}
			for(int i=0; i<C; i++) {
				cArr[output[i]] = '1';
			}
			StringBuilder sb = new StringBuilder();
			String s = sb.append(cArr).toString();
			System.out.println(s);
			
			if(s.contains("11")) {
				return;
			}
			Queue<Integer> q1 = new LinkedList<>();
			Queue<Character> q2 = new LinkedList<>();
			for(int i=0; i<s.length(); i++) {
				if(s.charAt(i) == '1') {
					int n1 = numQ.poll();
					int n2 = numQ.poll();
					char o = operQ.poll();
					int result = operation(n1, n2, o);
					numQ.addFirst(result);
					
				}else {
					int n1 = numQ.poll();
					char o = operQ.poll();
					q1.add(n1);
					q2.add(o);
				}
			}
			
			System.out.println(q2.size());
			return;
		}
		if(idx == oper.length) {
			
			return;
		}
		
		output[cnt] = num[idx];
		comb(idx+1, cnt+1);
		comb(idx+1, cnt);
	}
	static int operation(int n1, int n2, char oper) {
		int result = 0;
		switch (oper) {
		case '+':
			result = n1 + n2;
			break;
		case '-':
			result = n1 - n2;
			break;
		case '*':
			result = n1 * n2;
			break;

		}
		return result;
	}
}
