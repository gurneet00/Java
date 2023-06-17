//Find highest number of consicutive # in 2d array
//     4,11
//        ...##...###
//        ##..#####..
//        ####.##.#..
//        .#.#.##....
//   Output : 5
//

package Practice_questions;

import java.util.Objects;
import java.util.Scanner;

public class find_consequitive {
    static public void main(String[] args) {
        int  row , col , itr1 , itr2 , totalcnt=0 , temp=1;
        String arr[][];
        Scanner scan = new Scanner(System.in);
        System.out.println("Rows ?");
        row = scan.nextInt();
        System.out.println("cols ?");
        col = scan.nextInt();
        arr = new String[row][col];
        System.out.println("values ?" );
        for (itr1 = 0 ; itr1 < row ; itr1++)
            for (itr2 = 0 ; itr2 < col ; itr2++)
                arr[itr1][itr2]=scan.next();

        for (itr1 = 0 ; itr1 < row ; itr1++)
            for (itr2 = 0 ; itr2 < col-1 ; itr2++){
                if ((Objects.equals(arr[itr1][itr2], "#")) && (Objects.equals(arr[itr1][itr2+1], "#") )) {
                    temp++;
                    if (temp>totalcnt)
                       totalcnt = temp;
                }
                else
                    temp=1;



            }

        System.out.println(totalcnt);



}}
