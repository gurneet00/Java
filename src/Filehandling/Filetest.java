package Filehandling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Filetest {
    public static void main(String[] args) throws IOException {

        File oFile = new File("file.txt");


//        File nFile =new File("G.txt");
//        nFile.createNewFile();

        Scanner Scn = new Scanner(oFile);

        FileWriter FWriter = new FileWriter("G.txt");
        while (Scn.hasNextLine()){
            FWriter.write(Scn.nextLine() + "\n");
        }





        FWriter.close();

//        FileWriter FW = new FileWriter("file.txt");
//        FW.write("Friestinioiiyhyhyhyhyhyhyhyhyhyhyhyhyhyhtbnitbntgibntgnb");
//        FW.close();
//
//        File f1 =new File("file.txt");
//        Scanner D= new Scanner(f1);
//        System.out.println(D.next());

//        f1.delete();






    }

    }
