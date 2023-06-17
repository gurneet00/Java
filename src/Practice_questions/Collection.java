//Input : ABc1AB248DZXY            Show all 3 outputs
//        then spit into 2 :  ABcABDZXY        12348
//        Sort             :  AABBDXYZc        12348
//        Remove duplicates:  ABDXYZc          12348



package Practice_questions;

import io.netty.util.internal.StringUtil;
import org.apache.commons.exec.util.StringUtils;

import java.util.Scanner;
import java.util.*;
public class Collection {
    static public void main(String[] args){
        Scanner Scan_Obj = new Scanner(System.in);
        StringBuilder strObj = new StringBuilder();
        int StringCapacity = strObj.capacity();
        int itr=0;

        // input full string
        while (itr<=StringCapacity){
            String scan_char = Scan_Obj.next();
            if (Objects.equals(scan_char, "."))
                break;
            else
                strObj.append(scan_char);
            itr++;
        }
        List<Character> num_List = new ArrayList<>();
        List<Character> Aplha_List = new ArrayList<>();

        //Split into two Array list
        for (int p_itr = 0 ; p_itr<itr ; p_itr++)
        {
              if (Character.isDigit(strObj.charAt(p_itr)))
                  num_List.add(strObj.charAt(p_itr));

              else
                  Aplha_List.add(strObj.charAt(p_itr));
              }

        //Diffrenciated lists
        System.out.println(Aplha_List);
        System.out.println(num_List);

        // Sort both lists
        Collections.sort(num_List);
        Collections.sort(Aplha_List);

        //Sorted lists
        System.out.println(Aplha_List);
        System.out.println(num_List);

        //remove dublicates
        for (int p_itr = 0 ; p_itr<Aplha_List.size() ; p_itr++){
            for (itr = p_itr+1 ; itr < Aplha_List.size();itr++){
                if (Aplha_List.get(p_itr)==Aplha_List.get(itr))
                    Aplha_List.remove(itr);
            }
        }
        for (int p_itr = 0 ; p_itr<num_List.size() ; p_itr++){
            for (itr = p_itr+1 ; itr < num_List.size();itr++){
                if (num_List.get(p_itr)==num_List.get(itr))
                    num_List.remove(itr);
            }
        }

        //final output
        System.out.println(Aplha_List);
        System.out.println(num_List);




    }

    }

