//input array :
//        remove duplicated and print cnct instead of this number
//        [1,1,1,3,0,8]
//        [3,3,0,8]
//        [2,0,8]


package Practice_questions;

import java.util.Arrays;
import java.util.Scanner;

public class Update_dublicates {
    static public void main(String[] args)  {
        Scanner s1 = new Scanner(System.in);

        StringBuilder str = new StringBuilder();

        int n ;

        n=s1.nextInt();

        for(int i=0;i<n;i++)
            str.append(s1.next());

        D1(str,n);
        System.out.println(str);


    }

public static void D1(StringBuilder str, int n){
    int cnt = 1 , x;
      x=n;
    for (int i=0;i<n;i++){
        for (int j=i+1;j<n;j++){

            if(str.charAt(i)==str.charAt(j)) {
                str.delete(j,j+1);
                n--;
                cnt++;

                j--;
            }

        }
        if (cnt > 1)
            str.replace(i,i+1,Integer.toString(cnt));
        cnt = 1;



    }

    if (x!=n)
        D1(str, n);
}
    }
