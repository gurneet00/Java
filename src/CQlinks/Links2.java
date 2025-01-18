package CQlinks;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class Links2{

    static public void main(String[] args) throws InterruptedException, IOException {
        WebDriver web = new ChromeDriver();
        int itr,rc=0,dcount;

        //login
        web.get("https://codequotient.com/login");
        web.manage().window().maximize();
        web.switchTo().frame("loginIframe");
        web.findElement(By.xpath("//input[@name='email']") ).sendKeys("arun.goyat@chitkarauniversity.edu.in");
        web.findElement(By.id("password")).sendKeys("Chro#Acker@319");
        web.findElement(By.id("submit")).click();

        //read excel file
        FileInputStream IFstream = new FileInputStream("C:\\Users\\gurha\\OneDrive\\Documents\\ID.xlsx");             // input file
        XSSFWorkbook oexcl = new XSSFWorkbook(IFstream);
        XSSFSheet Sheet = oexcl.getSheetAt(0);
        int nrowcnt = Sheet.getLastRowNum();

        //new file
        FileOutputStream odfstram = new FileOutputStream("C:\\Users\\gurha\\OneDrive\\Documents\\Ques.xlsx");            // output file
        XSSFWorkbook odexcl = new XSSFWorkbook();
        XSSFSheet odsheet = odexcl.createSheet("Sheet1");

        for(int k=0;k<=nrowcnt;k++) {

            String ID = String.valueOf(Sheet.getRow(k).getCell(0));
           web.get("https://codequotient.com/quest/add/" + ID);


            XSSFRow orow = odsheet.createRow(rc++);
            int ccount =0;
            orow.createCell(ccount++).setCellValue(ID);
            if(Objects.equals(web.getCurrentUrl(), "https://codequotient.com/quest/add/" + ID))
            {
                String title = web.findElement(By.xpath("//*[@name=\"txtQuesTitle\"]")).getAttribute("value");
                String score = web.findElement(By.xpath("//*[@name=\"score\"]")).getAttribute("value");
                List<WebElement> key = web.findElements(By.xpath("//*[@class=\"tag-editor ui-sortable\"]//div[2]"));
                String type = web.findElement(By.xpath("//*[@data-id=\"type\"]")).getText();
                String des = web.findElement(By.xpath("//*[@id=\"editorQuil\"]/div[1]")).getText();

                orow.createCell(ccount++).setCellValue(title);
                orow.createCell(ccount++).setCellValue(type);
                orow.createCell(ccount++).setCellValue(score);
                orow.createCell(ccount++).setCellValue("https://codequotient.com/quest/preview/"+ID);
                orow.createCell(ccount++).setCellValue(des);
//                int cor = 0, y=0;
//                if (Objects.equals(type, "MCQ")){
//                    List<WebElement> w1 = web.findElements(By.xpath("//*[@class=\"mcq-option\"]"));
//
//                    for (int z=0 ;z< w1.size();z++){
//                        orow.createCell(ccount++).setCellValue(w1.get(z).getText());
//                        y=z+1;
//                        WebElement w2 = web.findElement(By.xpath("//div[@class='mcq-option']["+y+"]//div[3]//input"));
//                        if (w2.isSelected()){
//                            cor = y;
//                        }
//                    }
//                    orow.createCell(ccount++).setCellValue(cor);
//
//
//
//                }

                // write in excel





              for (WebElement webElement : key) orow.createCell(ccount++).setCellValue(webElement.getText());

            }
            else{
//                web.get("https://course.codequotient.com/tutorial/preview/"+ID);
//                String title = web.findElement(By.xpath("//*[@class=\"question-title\"]//p[@class=\"chapter-text\"]")).getText();
//
//                String des = web.findElement(By.xpath("//*[@id=\"sectionScroll\"]")).getText();
//                orow.createCell(ccount++).setCellValue(title);
//                orow.createCell(ccount++).setCellValue("Tutorial");
//                orow.createCell(ccount++).setCellValue("NA");
//                orow.createCell(ccount++).setCellValue("https://course.codequotient.com/tutorial/preview/"+ID);
//                orow.createCell(ccount++).setCellValue(des);

                web.get("https://codequotient.com/quest/preview/"+ID);
                String s11 =web.findElement(By.xpath("//*[@class=\"question-name\"]")).getText();
                String des1 = web.findElement(By.xpath("//*[@class=\"question_info ql-editor\"]")).getText();
                String T11 = web.findElement(By.xpath("//*[@id=\"question-type-badge\"]")).getText();



                orow.createCell(ccount++).setCellValue(s11);
                orow.createCell(ccount++).setCellValue(T11);
                orow.createCell(ccount++).setCellValue("Locked Question");
                orow.createCell(ccount++).setCellValue("https://codequotient.com/quest/preview/"+ID);
                orow.createCell(ccount++).setCellValue(des1);




            }

        }
        odexcl.write(odfstram);
        odfstram.close();
        web.close();




    }

}
