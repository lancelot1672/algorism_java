package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class B9519_졸려 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int X = Integer.parseInt(br.readLine());
		String str =  br.readLine();
		char[] input =str.toCharArray();
		LinkedList<Character> list = new LinkedList<>();
		
		for(char c : input) {
			list.add(c);
		}
		int N = input.length;
		ArrayList<String> outputList = new ArrayList<>();
		
		int rotateNum = 100000;
		for(int x=1; x<=X; x++) {
			// 문자열 한번 돌리기
			for(int i=1; i<=N/2; i++) {
				char temp = list.get(i);
				list.add(N-i+1, temp);
				list.remove(i);
			}
			//System.out.println(list.toString());
			
			// String 만들어서 규칙 찾기
			StringBuilder sb = new StringBuilder();
			for(char c : list) {
				sb.append(c);
			}
			String tempStr = sb.toString();
			//System.out.println(tempStr);
			
			if(str.equals(tempStr)) {
				rotateNum = x;
				outputList.add(tempStr);
				break;
			}else {
				outputList.add(tempStr);
			}

		}
		//System.out.println(X % rotateNum);
		if(X % rotateNum == 0) {

			System.out.println(	outputList.get(0));
		}else {
			System.out.println(outputList.get(X % rotateNum -1));
			
		}
	}
}
