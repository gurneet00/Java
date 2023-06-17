//Print series
//        input : 1000
//
//1 10 100 1000 101 102 103 104 105 106 107 108 109
//11 110 111 112 113 114 115 116 117 118 119
//12 120 121 122 123 124 125 126 127 128 129
//13 130 131 132 133 134 135 136 137 138 139
//.
//.
//.
//




        package Practice_questions;

import java.util.Scanner;

public class TOp_num {

    static public void main(String[] args) {

        Scanner s1 = new Scanner(System.in);
        int n = s1.nextInt();
        System.out.print("1 ");
        for (int i = 10 ; i<=99 ; i++){
            int k=i;
            while (k<=n){
                System.out.print(k + " ");

                k=k*10;
            }
            k=i;
            while (k<=n){

                k=k*10;
                for (int j = 1;j<=9;j++)
                {   if (k+j <=1000)
                    System.out.print(k+j + " ");
                }

            }




            System.out.print("\n");


        }

    }}
