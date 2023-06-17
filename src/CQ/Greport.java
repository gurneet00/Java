package CQ;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;

import java.io.File;

public class Greport {
    @AfterSuite
    public static void main(String[] args){
        ChromeDriver d1 = new ChromeDriver();
        d1.manage().window().maximize();
        d1.get("C:\\Users\\Nokia\\IdeaProjects\\Intro\\test-output\\emailable-report.html");
        TakesScreenshot Ts = (TakesScreenshot)d1;
        File F1 = Ts.getScreenshotAs(OutputType.FILE);
        File trg = new File(".\\screenshots\\home.png");
    }

}
