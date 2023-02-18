package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B1157_단어공부 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine().toUpperCase();

        char[] cArr = input.toCharArray();
        int[] alpha = new int[26];

        for (char c : cArr) {

            int char_index = c - 'A';
            if(char_index >= 26){
                alpha[char_index - 26]++;
            }else{
                alpha[char_index]++;
            }
        }
        int max = 0;
        int max_index = 0;
        for (int i=0; i<26; i++) {
            if(max < alpha[i]){
                max = alpha[i];
                max_index = i;
            }
        }

        // max가 두개 있다면 ?
        int check_cnt = 0;
        for (int a : alpha) {
            if(a == max) check_cnt++;
        }

        if(check_cnt >= 2){
            System.out.println("?");
            return;
        }
        System.out.println((char)(65 + max_index));
    }
}
