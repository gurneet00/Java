package CQ;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class Login_page_Alerts
{
    static WebDriver d;
@Test
   static void f1(ChromeDriver d1) throws InterruptedException {
   d=d1;
}
@Test
    void f2() throws InterruptedException {
    d.findElement(By.id("btnSubmit")).click();
    Thread.sleep(1000);
    String Alert = "Please enter email address.";
    SoftAssert SA = new SoftAssert();
    Assert.assertEquals(Alert,d.findElement(By.xpath("//p[@id=\"alert-message\"]")).getText());
}

    @Test
    void f3() throws InterruptedException {

        d.findElement(By.xpath("//input[@name='email']") ).sendKeys("newl1@gmail.vv");
        d.findElement(By.id("btnSubmit")).click();
        Thread.sleep(1000);
        String Alert = "Please enter password.";
        SoftAssert SA = new SoftAssert();
        SA.assertEquals(Alert,d.findElement(By.xpath("//p[@id=\"alert-message\"]")).getText());
    }


    @Test
    void f4() throws InterruptedException {
        d.findElement(By.xpath("//input[@name='email']") ).clear();
       d.findElement(By.id("password")).sendKeys("Tessssssssster@123");
        d.findElement(By.id("btnSubmit")).click();
        Thread.sleep(1000);
        String Alert = "Please enter password.";
        SoftAssert SA = new SoftAssert();
        SA.assertEquals(Alert,d.findElement(By.xpath("//p[@id=\"alert-message\"]")).getText());
   }

}
