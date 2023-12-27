package CQlinks;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


import java.io.FileInputStream;
import java.io.IOException;

public class add_keyword {
    static public void main(String[] args) throws IOException, InterruptedException {
        WebDriver web = new ChromeDriver();

        web.get("https://codequotient.com/login");
        web.manage().window().maximize();
        web.findElement(By.xpath("//input[@name='email']") ).sendKeys("paras.khurana@codequotient.com");
        web.findElement(By.id("password")).sendKeys("Test@0439");
        web.findElement(By.id("btnSubmit")).click();

        FileInputStream IFstream = new FileInputStream("C:\\Users\\gurha\\Documents\\ID.xlsx");             // input file
        XSSFWorkbook oexcl = new XSSFWorkbook(IFstream);
        XSSFSheet Sheet = oexcl.getSheetAt(0);
        int nrowcnt = Sheet.getLastRowNum();

        for(int itr =0;itr<=nrowcnt;itr++){
            String ID = String.valueOf(Sheet.getRow(itr).getCell(0));
            web.get("https://test.codequotient.com/quest/add/" + ID);
            Actions action = new Actions(web);
            try {
            WebElement keyword = web.findElement(By.xpath("//*[@class=\"tag-editor ui-sortable\"]"));

            action.moveToElement(keyword).click().sendKeys(Keys.ENTER).sendKeys("ete-set1aaa-java").sendKeys(Keys.ENTER).build().perform();
            }catch (NoSuchElementException e){
                System.out.println(ID+ " Locked question");
                continue;
            }
            try {
                web.findElement(By.xpath("//*[@class=\"save-btn\"]")).click();
            }catch (NoSuchElementException e){
                System.out.println(ID+ "  dont have permission");
            }
            Thread.sleep(1000);

        }
        web.close();


    }
}
