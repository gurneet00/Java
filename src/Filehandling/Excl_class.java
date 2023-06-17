package Filehandling;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Excl_class {
    public static void main(String[] args) throws IOException {


            int rowcnt , colcnt;
        FileInputStream IFstream = new FileInputStream("C:\\Users\\Nokia\\Downloads\\GGG.xlsx");
        XSSFWorkbook oexcl = new XSSFWorkbook(IFstream);
        XSSFSheet Sheet = oexcl.getSheetAt(0);
        rowcnt = Sheet.getLastRowNum();
        colcnt = Sheet.getRow(0).getLastCellNum();


        FileOutputStream odfstram = new FileOutputStream("C:\\Users\\Nokia\\Downloads\\G2.xlsx");
        XSSFWorkbook odexcl = new XSSFWorkbook();
        XSSFSheet odsheet = odexcl.createSheet("Sheet1");



        FileOutputStream efstram = new FileOutputStream("C:\\Users\\Nokia\\Downloads\\G3.xlsx");
        XSSFWorkbook eexcl = new XSSFWorkbook();
        XSSFSheet esheet = eexcl.createSheet("Sheet1");


        for (int i=0;i<=rowcnt;i++ ){
            int evecol = 0;
            int oddcol=0;

            XSSFRow orow = odsheet.createRow(i);
            XSSFRow erow = esheet.createRow(i);
            for (int j=0;j<colcnt;j++){
                if (j%2==0) {

                    erow.createCell(evecol).setCellValue(String.valueOf(Sheet.getRow(i).getCell(j)));
                    evecol++;
                }
                else {
                    orow.createCell(oddcol).setCellValue(String.valueOf(Sheet.getRow(i).getCell(j)));
                    oddcol++;
                }

            }

        }

        odexcl.write(odfstram);
        odfstram.close();

        eexcl.write(efstram);
        efstram.close();







    }
}
