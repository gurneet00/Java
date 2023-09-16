import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Course_Links {
    static public void main(String[] args) throws IOException, InterruptedException {
        WebDriver web = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nokia\\IdeaProjects\\Intro");
        System.out.println();
        int itr,rc=0,ccount=0;

        //login
        web.get("https://cqtestga.com/login");
        web.manage().window().maximize();
        web.findElement(By.xpath("//input[@name='email']") ).sendKeys("gurharneet.singh@codequotient.com");
        web.findElement(By.id("password")).sendKeys("Holmes@221");
        web.findElement(By.id("btnSubmit")).click();

        //read excel file
        FileInputStream IFstream = new FileInputStream("C:\\Users\\Nokia\\Documents\\Links.xlsx");             // input file
        XSSFWorkbook oexcl = new XSSFWorkbook(IFstream);
        XSSFSheet Sheet = oexcl.getSheetAt(0);
        int rowcnt = Sheet.getLastRowNum();

        //new file
        FileOutputStream odfstram = new FileOutputStream("C:\\Users\\Nokia\\Documents\\Ques.xlsx");            // output file
        XSSFWorkbook odexcl = new XSSFWorkbook();
        XSSFSheet odsheet = odexcl.createSheet("Sheet1");

        //find data
        for(int i=0;i<=rowcnt;i++) {
            String url = String.valueOf(Sheet.getRow(i).getCell(0));
            web.get(url);
            XSSFRow orow = odsheet.createRow(rc++);
            String cTitle = web.findElement(By.xpath("//div[@class=\"addCourseContent-title\"]//h2")).getText();
            orow.createCell(0).setCellValue(cTitle);
          //  System.out.println(cTitle);
             orow = odsheet.createRow(rc++);
            List<WebElement> sections = web.findElements(By.xpath("//p[@class=\"section-title\"]"));

            for(int sitr=1;sitr<= sections.size();sitr++){
                String sName = web.findElement(By.xpath("//div[@class=\"sections\"]["+sitr+"]//p[@class=\"section-title\"]")).getText();
                orow.createCell(0).setCellValue(sName);
            //    System.out.println(sName);
                orow = odsheet.createRow(rc++);
                List<WebElement> qName = web.findElements(By.xpath("//div[@class=\"col-5 question-title\"]//a"));
                List<WebElement> qLink = web.findElements(By.xpath("//div[@class=\"sections\"]["+sitr+"]//div[@class=\"col-5 question-title\"]//a"));
                List<WebElement> qType = web.findElements(By.xpath("//div[@class=\" col-1 q-typee\"]//span"));
                List<WebElement> qScore = web.findElements(By.xpath("//div[@class=\"row draggable-content-element table-body\"]//div[@class=\" col-1\"][2]"));

                for(int z=0;z<qLink.size();z++){
                    ccount =0;
                    String qNtext = (String)((JavascriptExecutor)web).executeScript("return arguments[0].textContent.trim();", qName.get(z));
                  //  System.out.println(qNtext);
                    orow.createCell(ccount++).setCellValue(qNtext);
                 //   System.out.println(qLink.get(z).getAttribute("href"));
                    orow.createCell(ccount++).setCellValue(qLink.get(z).getAttribute("href"));
                    String qtText = (String)((JavascriptExecutor)web).executeScript("return arguments[0].textContent.trim();", qType.get(z));
                 //   System.out.println(qtText);
                    orow.createCell(ccount++).setCellValue(qtText);

                    String qtScore = (String)((JavascriptExecutor)web).executeScript("return arguments[0].textContent.trim();", qScore.get(z));
                 //   System.out.println(qtScore);
                    orow.createCell(ccount++).setCellValue(qtScore);
                    orow = odsheet.createRow(rc++);


                }
                orow = odsheet.createRow(rc++);
            }
            orow = odsheet.createRow(rc++);
            orow = odsheet.createRow(rc++);



    }
        odexcl.write(odfstram);
        odfstram.close();
        web.close();
}}
