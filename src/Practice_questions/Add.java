//Add two arrays
//        input : [9,9,9,2,8]  , [9,8,1]                  99928
//        Output : 00909                                    981
//                                                      -----------
//                                                        00909
//




package Practice_questions;

import java.util.Scanner;

public class Add {

    static public void main(String[] args) {

        // get size and create array
        int size1, size2, arr1[], arr2[], itr;
        Scanner scan = new Scanner(System.in);
        size1 = scan.nextInt();
        arr1=new int[size1];
        size2=scan.nextInt();
        arr2=new int[size2];
        

        //input array
        for ( itr = 0 ; itr<size1;itr++)
            arr1[itr]=scan.nextInt();
        for ( itr = 0 ; itr<size2;itr++)
            arr2[itr]=scan.nextInt();

        for (itr = 0 ; itr <size2;itr++){
            arr1[size1-itr-1]=arr1[size1-itr-1]+arr2[size2-itr-1];
            if (arr1[size1-itr-1]>9){
                arr1[size1-itr-2]=arr1[size1-itr-2]+(arr1[size1-itr-1]/10);
                arr1[size1-itr-1]=arr1[size1-itr-1]%10;
            }

        }
//        for (itr = size2 ; itr <size1;itr++){
//            if(itr!=size1-1) {
//                arr1[size1 - itr - 2] = arr1[size1 - itr - 2] + (arr1[size1 - itr - 1] / 10);
//                arr1[size1 - itr - 1] = arr1[size1 - itr - 1] % 10;
//            }
//            else
//                arr1[size1 - itr - 1] = arr1[size1 - itr - 1] % 10;
//        }

        for ( itr = 0 ; itr<size1;itr++)
            System.out.print(arr1[itr] + " ");



    }
}
