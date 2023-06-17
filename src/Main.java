import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;
import java.time.Duration;
import java.util.Scanner;
// Other imports go here
// Do NOT change the class name
class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        WebDriver w1 = new ChromeDriver();
        w1.get("https://cqtestga.com/login");
        w1.manage().window().maximize();
        w1.findElement(By.xpath("//input[@name='email']") ).sendKeys("gurharneet.singh@codequotient.com");
        w1.findElement(By.id("password")).sendKeys("Tester@123");
        w1.findElement(By.id("btnSubmit")).click();
        Thread.sleep(3000);
        JavascriptExecutor je = (JavascriptExecutor)w1;
        w1.get("https://cqtestga.com/batch/list");
        Thread.sleep(3000);
        WebDriverWait wait=new WebDriverWait(w1,Duration.ofSeconds(5));
        WebElement web1 =  w1.findElement(By.xpath("//button[contains(text(),'+')]"));
        wait.until(ExpectedConditions.visibilityOf(web1));
        w1.findElement(By.xpath("//a[@href=\"delbatch/id:63104926b6414d83290f3b42\"]")).click();
        Thread.sleep(3000);
        Alert a1 = w1.switchTo().alert();
        String s1 = a1.getText();
        System.out.println(s1);



    }
}
