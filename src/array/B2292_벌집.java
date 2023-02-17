package array;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B2292_벌집 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(br.readLine());

        int num = 1;
        int index = 1;
        while(num < input){
//            for(int i=1; i <= index * 6; i++){
//                num += 1;
//            }
            num += index * 6;
            index++;
        }
        System.out.println(index);
    }

}
