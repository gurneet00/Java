import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CQ {
    static public void main(String[] args) throws InterruptedException, IOException {
        Try1 T = new Try1();
        WebDriver web = new ChromeDriver();
        web.get("https://cqtestga.com/");
        web.findElement(By.xpath("//a[@class='login__link']")).click();
        Dimension d1 = new Dimension(1280, 1024);
        web.manage().window().setSize( d1 );
        web.findElement(By.xpath("//input[@name='email']") ).sendKeys(T.Email);
        web.findElement(By.id("password")).sendKeys(T.Password);
        web.findElement(By.id("btnSubmit")).click();
        web.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS) ;
        web.findElement(By.xpath("//button[contains(text(),'+')]")).click();
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime Date1 = LocalDateTime.now();
        String crdate = date.format(Date1);
        web.findElement(By.xpath("//input[@id=\"batch-title\"]")).sendKeys(T.Batch_name);
        web.findElement(By.xpath("//input[@id=\"batch-description\"]")).sendKeys(T.Batch_Description);
        web.findElement(By.xpath("//div[@class='dropdown bootstrap-select show-tick form-control dropup']")).click();
        web.findElement(By.xpath(" //input[@placeholder=\"Search batch administrator\"]")).sendKeys(T.batch_administrator);
        web.findElement(By.xpath("//span[text()=" + "'" + T.batch_administrator +"'"+ "]")).click();
        web.findElement(By.xpath("//label//*[text()=\"Leads\"]")).click();
        web.findElement(By.xpath("//button[@id=\"submitBatchBtnContainer\"]")).click();
        web.findElement(By.xpath("//input[@id=\"input-search-header\"]")).sendKeys(T.Batch_name);
        Thread.sleep(1000);
        List<WebElement> elements = web.findElements(By.xpath("//tbody//tr[@role=\"row\"]//td[5]"));
        int a = elements.size();
        for (int i=0; i<a;i++)
        {
            String S1 = elements.get(i).getText();
            String[] arr = S1.split("\n");
            String Updated_date = arr[0] + " " + arr[1];
            if (Updated_date.equals(crdate)){
                web.findElement(By.xpath("//tbody//tr[@role='row'][" + (i+1) + "]//td[4]")).click();
                break;
            }

        }
        ArrayList<String> wid = new ArrayList<String>(web.getWindowHandles());
        web.switchTo().window(wid.get(1));
        web.findElement(By.xpath("//button[@data-customtooltip='Bulk add leads']")).click();
        web.findElement(By.xpath("//label[text()=\"Choose file \"]")).click();
        Runtime.getRuntime().exec("C:\\Users\\Nokia\\IdeaProjects\\Intro\\FileUpload.exe");
        Thread.sleep(5000);
        web.findElement(By.xpath("//button[contains(text(),'Upload')]")).click();
        web.navigate().refresh();
        List<WebElement> even = web.findElements(By.xpath("//tr[@class=\"even\"]//td[@class=\"sorting_1 dtfc-fixed-left\"]"));
        System.out.println(even.size());
        for(int j = 0 ; j< even.size();j++){
            even.get(j).click();
        }
        web.findElement(By.xpath("//img[@src=\"/icons/actions-fixed.svg\"]")).click();
        web.findElement(By.xpath("//div[text()=\"Update Assign to\"]")).click();
        Thread.sleep(1000);
        web.findElement(By.xpath("//button[@data-id=\"updateAssignTo\"]")).click();
        web.findElement(By.xpath("//li[@title="+"'" + T.caller +"'" +" ]")).click();
        Thread.sleep((1000));
        web.findElement(By.xpath("//button[@onclick=\"updateStatus(true)\"]")).click();
        Thread.sleep((1000));
        for(int j = 0 ; j< even.size();j++){
            even.get(j).click();
        }
        web.findElement(By.xpath("//img[@src=\"/icons/actions-fixed.svg\"]")).click();
        Thread.sleep((1000));
        web.findElement(By.xpath("//div[text()=\"Update status & comment\"]")).click();
        Thread.sleep((1000));
        web.findElement(By.xpath("//button[@data-id=\"updatedStage\"]")).click();
        Thread.sleep((1000));
        web.findElement(By.xpath("//li[contains(@title,"+"'" + T.stage +"'" + ")]")).click();
        Thread.sleep((1000));
        web.findElement(By.xpath("//li[contains(@title,"+"'" + T.status +"'" +")]")).click();
        Thread.sleep((1000));
        web.findElement(By.xpath("//button[@onclick=\"updateStatus()\"]")).click();


    }

}

