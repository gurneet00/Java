package CQ;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class TestNg {
     public static ChromeDriver d1 = new ChromeDriver();
    int httpcode;
    String CR;
    GetHttpResponse G1 = new GetHttpResponse();

@Test
public void test1() throws  IOException {
    d1.get("https://codequotient.com/");
    httpcode = G1.code("https://codequotient.com/");
    Assert.assertEquals(httpcode,200);
}

@Test
    public void test2() throws IOException {
    d1.findElement(By.xpath("//a[@class='login__link']")).click();
    CR = d1.getCurrentUrl();
    httpcode = G1.code(CR);
    Assert.assertEquals(httpcode,200);
}

@Test
    public void test3()   {
    String Login = "https://codequotient.com/login";
    Assert.assertEquals(Login,d1.getCurrentUrl());
}

@Test
    public void test4()  {
        Login_page_Alerts L = new Login_page_Alerts();


    }

    }