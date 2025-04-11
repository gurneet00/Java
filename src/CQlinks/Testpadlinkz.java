package CQlinks;

import org.apache.pdfbox.pdmodel.font.encoding.SymbolEncoding;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Testpadlinkz {

    static public void main(String[] args) throws IOException, InterruptedException {
        WebDriver web = new ChromeDriver();
        int itr,rc=0;

        //login
        web.get("https://assess.testpad.chitkara.edu.in/login");
        web.manage().window().maximize();
        web.switchTo().frame("loginIframe");
        web.findElement(By.xpath("//input[@id='email']") ).sendKeys("gurharneet.singh@codequotient.com");
        web.findElement(By.id("password")).sendKeys("Gurneet@08");
        web.findElement(By.id("submit")).click();

        //read excel file
        FileInputStream IFstream = new FileInputStream("C:\\Users\\gurha\\OneDrive\\Documents\\Links.xlsx");             // input file
        XSSFWorkbook oexcl = new XSSFWorkbook(IFstream);
        XSSFSheet Sheet = oexcl.getSheetAt(0);
        int rowcnt = Sheet.getLastRowNum();

        //new file
        FileOutputStream odfstram = new FileOutputStream("C:\\Users\\gurha\\OneDrive\\Documents\\Ques.xlsx");            // output file
        XSSFWorkbook odexcl = new XSSFWorkbook();
        XSSFSheet odsheet = odexcl.createSheet("Sheet1");

        //find ids
        for(int i=0;i<=rowcnt;i++) {
            String url = String.valueOf(Sheet.getRow(i).getCell(0));
            System.out.println(url);
            web.get(url);
            Thread.sleep(1000);
            List<WebElement> ques = web.findElements(By.xpath("//div[contains(@class,\"row draggable-content-element table-body\")]"));
            System.out.println(ques);
            int count = ques.size();
            String[] s1 = new String[count];
            for(int g=0;g<count;g++){
                s1[g]= ques.get(g).getAttribute("id");
            }
            for (itr = 0; itr < count; itr++) {
//                XSSFRow orow = odsheet.createRow(rc++);
//                orow.createCell(0).setCellValue(ques.get(itr).getAttribute("id"));


                String ID = s1[itr];
                web.get("https://assess.testpad.chitkara.edu.in/quest/add/" + ID);
                Thread.sleep(1000);

                XSSFRow orow = odsheet.createRow(rc++);
                int ccount =0;
                orow.createCell(ccount++).setCellValue(ID);
                if(Objects.equals(web.getCurrentUrl(), "https://assess.testpad.chitkara.edu.in/quest/add/" + ID))
                {
                    String title = web.findElement(By.xpath("//*[@name=\"txtQuesTitle\"]")).getAttribute("value");
                    String score = web.findElement(By.xpath("//*[@name=\"score\"]")).getAttribute("value");
                    List<WebElement> key = web.findElements(By.xpath("//*[@class=\"tag-editor ui-sortable\"]//div[2]"));
                    String type = web.findElement(By.xpath("//*[@data-id=\"type\"]")).getText();
                    String des = web.findElement(By.xpath("//*[@id=\"editorQuil\"]/div[1]")).getText();

//                        System.out.println(title + score + type +des);

                    orow.createCell(ccount++).setCellValue(title);
                    orow.createCell(ccount++).setCellValue(type);
                    orow.createCell(ccount++).setCellValue(score);
                    orow.createCell(ccount++).setCellValue("https://assess.testpad.chitkara.edu.in/quest/preview/"+ID);
                    orow.createCell(ccount++).setCellValue(des);
                    int cor = 0, y=0;
                    if (Objects.equals(type, "MCQ")){
                        List<WebElement> w1 = web.findElements(By.xpath("//*[@class=\"mcq-option\"]//textarea"));

                        for (int z=0 ;z< w1.size();z++){
                            orow.createCell(ccount++).setCellValue(w1.get(z).getText());
                            y=z+1;
                            WebElement w2 = web.findElement(By.xpath("//div[@class='mcq-option']["+y+"]//div[3]//input"));
                            if (w2.isSelected()){
                                cor = y;
                            }
                        }
                        orow.createCell(ccount++).setCellValue(cor);



                    }
                    else if(Objects.equals(type, "Coding")){


                        String lang= web.findElement(By.xpath("//*[@class=\"change-lang-container row\"]//button//div[@class=\"filter-option-inner-inner\"]")).getText();
                        orow.createCell(ccount++).setCellValue(lang);

                    }
                    else if(Objects.equals(type, "Multiple Questions")){
                        List<WebElement> opts = web.findElements(By.xpath("//*[@class=\"code-option code-option-loop\"]"));
                        for (int xc = 0;xc<opts.size();xc++ ){
                            String sx = "question\n" + opts.get(xc).findElement(By.xpath(".//*[@class=\"col-4 input-box\"]")).getText() + "\nAnswer\n" + opts.get(xc).findElement(By.xpath(".//*[@class=\"col-4 input-box input-box-2\"]")).getText();
                            orow.createCell(ccount++).setCellValue(sx);
                        }
                    }

                    // write in excel





                    for (WebElement webElement : key) orow.createCell(ccount++).setCellValue(webElement.getText());

                }
                else{
                    //web.get("https://course.codequotient.com/tutorial/preview/"+ID);
//                String title = web.findElement(By.xpath("//*[@class=\"question-title\"]//p[@class=\"chapter-text\"]")).getText();
//
//                String des = web.findElement(By.xpath("//*[@id=\"sectionScroll\"]")).getText();
//                orow.createCell(ccount++).setCellValue(title);
//                orow.createCell(ccount++).setCellValue("Tutorial");
//                orow.createCell(ccount++).setCellValue("NA");
//                orow.createCell(ccount++).setCellValue("https://course.codequotient.com/tutorial/preview/"+ID);
//                orow.createCell(ccount++).setCellValue(des);


                        web.get("https://assess.testpad.chitkara.edu.in/quest/preview/"+ID);
                    String s11 =web.findElement(By.xpath("//*[@class=\"question-name\"]")).getText();
                    String des1 = web.findElement(By.xpath("//*[@class=\"question_info ql-editor\"]")).getText();
                    String T11 = web.findElement(By.xpath("//*[@id=\"question-type-badge\"]")).getText();



                        orow.createCell(ccount++).setCellValue(s11);
                        orow.createCell(ccount++).setCellValue(T11);
                        orow.createCell(ccount++).setCellValue("Locked Question");
                        orow.createCell(ccount++).setCellValue("https://assess.testpad.chitkara.edu.in/quest/"+ID);
                        orow.createCell(ccount++).setCellValue(des1);
                        String sz = web.findElement(By.xpath("//*[@id=\"question-type-badge\"]")).getText();
                        if(sz.trim().equals("CODING")) {
                            web.findElement(By.xpath("//*[@class=\"container-fluid dashboard-container\"]//button[@class=\"btn dropdown-toggle btn-light\"]")).click();
                            List<WebElement> L1 = web.findElements(By.xpath("//*[@class=\"dropdown-menu inner show\"]//li"));
                            String S1 = "";
                            for (int x = 0; x < L1.size(); x++) {
                                S1 = S1 + "," + L1.get(x).getText();
                            }
                            orow.createCell(ccount++).setCellValue(S1);
                        }
                        else if(sz.trim().equals("MCQ")){
                            int sd=0;
                            List<WebElement> opts = web.findElements(By.xpath("//*[@class=\"label-choose-answer\"]"));
                            for( int gh= 0 ;gh< opts.size();gh++){
                                String st = opts.get(gh).findElement(By.xpath(".//*[@class=\"mb-0\"]")).getText();
                                orow.createCell(ccount++).setCellValue(st);
                                opts.get(gh).click();
                                web.findElement(By.xpath(".//*[@class=\"submit_choices\"]")).click();
                                Thread.sleep(1000);
                                if (opts.get(gh).findElement(By.xpath(".//*[@class=\"mcq-result-text\"]")).getText().trim().equals("correct answer")){
                                    sd = gh+1;
                                }

                            }
                            orow.createCell(ccount++).setCellValue(sd);


                        }
                }
            }
        }


        // ---------------------------------------------------------------------------

        odexcl.write(odfstram);
        odfstram.close();
        web.close();






    }


}
