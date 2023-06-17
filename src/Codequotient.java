import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Codequotient {
    public static ChromeDriver d1 = new ChromeDriver();

@Test
    void f1(){
        d1.get("https://cqtestga.com/login");
        d1.findElement(By.xpath("//input[@name='email']") ).sendKeys("gurharneet.singh@codequotient.com");
        d1.findElement(By.id("password")).sendKeys("Tester@123");
        d1.findElement(By.id("btnSubmit")).click();
    }






}
