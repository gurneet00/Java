package Filehandling;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;



public class LoginPages {
    static WebDriver driver;

    public static void main(String[] arg) throws IOException {
         
        FileReader reader=new FileReader("C:\\Users\\pkhur\\frameworkTestcases\\CodeQuestionTestCases\\src\\Pages\\Configurations.properties");  
            Properties prop=new Properties();  
            prop.load(reader);  
        System.setProperty("webdriver.chrome.driver",prop.getProperty("DriverPath"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
}