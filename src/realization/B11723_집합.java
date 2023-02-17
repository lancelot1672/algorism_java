package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class B11723_집합 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();

        for(int i=0; i<k; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String oper = st.nextToken();

            if(st.hasMoreTokens()){
                int num = Integer.parseInt(st.nextToken());
                if(oper.equals("add")){
                    set.add(num);
                }else if(oper.equals("remove")){
                    set.remove(num);
                }else if(oper.equals("check")){
                    if(set.contains(num)){
                        System.out.println("1");
                    }else{
                        System.out.println("0");
                    }
                }else if(oper.equals("toggle")){
                    if(set.contains(num)){
                        set.remove(num);
                    }else{
                        set.add(num);
                    }
                }
            }
            if(oper.equals("all")){
                for(int s=1; s<=20; s++){
                    set.add(i);
                }
            }
            else if(oper.equals("empty")){
                set.clear();
            }
        }

    }
}
