package CQlinks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.io.IOException;

public class Ques_list {

    static public void main(String[] args) throws IOException, InterruptedException {
        WebDriver web = new ChromeDriver();

        //login
        web.get("https://cqtestga.com/login");
        web.manage().window().maximize();
        web.switchTo().frame("loginIframe");
        web.findElement(By.xpath("//input[@id='email']") ).sendKeys("girdhar@codequotient.com");
        web.findElement(By.id("password")).sendKeys("Holmes@221");
        web.findElement(By.id("submit")).click();

        web.get("https://cqtestga.com/quest/list");
        Thread.sleep(1000);
        Actions A1 = new Actions(web);
        //WebElement W1 = web.findElement(By.xpath("//button[@class=\"btn-thm under-tooltip mr-4\"]"));
        A1.moveToElement(web.findElement(By.xpath("//div[@class='filter-input-section']"))).click().moveToElement(web.findElement(By.xpath("//li[text()=\"Question Type\"]"))).click().build().perform();
        Thread.sleep(2000);
        A1.moveToElement(web.findElement(By.xpath("//label[normalize-space()='Coding']"))).click().moveToElement(web.findElement(By.xpath("//button[@class=\"btn apply-btn type-apply-btn\"]"))).click().build().perform();


    }
}
