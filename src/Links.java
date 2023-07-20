import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Links {
    static public void main(String[] args) throws IOException, InterruptedException {
        WebDriver web = new ChromeDriver();
        int itr,rc=0,dcount;

        //login
        web.get("https://codequotient.com/login");
        web.manage().window().maximize();
        web.findElement(By.xpath("//input[@name='email']") ).sendKeys("gurharneet.singh@codequotient.com");
        web.findElement(By.id("password")).sendKeys("Gurneet@08");
        web.findElement(By.id("btnSubmit")).click();

        //read excel file
        FileInputStream IFstream = new FileInputStream("C:\\Users\\Nokia\\Documents\\Links.xlsx");             // input file
        XSSFWorkbook oexcl = new XSSFWorkbook(IFstream);
        XSSFSheet Sheet = oexcl.getSheetAt(0);
        int rowcnt = Sheet.getLastRowNum();

        //new file
        FileOutputStream odfstram = new FileOutputStream("C:\\Users\\Nokia\\Documents\\ID.xlsx");            // output file
        XSSFWorkbook odexcl = new XSSFWorkbook();
        XSSFSheet odsheet = odexcl.createSheet("Sheet1");

        //find ids
        for(int i=0;i<=rowcnt;i++) {
            String url = String.valueOf(Sheet.getRow(i).getCell(0));
            web.get(url);
            Thread.sleep(1000);
            List<WebElement> ques = web.findElements(By.xpath("//div[contains(@class,\"row draggable-content-element table-body  \")]"));
            int count = ques.size();
            for (itr = 0; itr < count; itr++) {
                XSSFRow orow = odsheet.createRow(rc++);
                orow.createCell(0).setCellValue(ques.get(itr).getAttribute("id"));
            }
        }
        odexcl.write(odfstram);
        odfstram.close();
        web.close();

        // ---------------------------------------------------------------------------








    }

}
