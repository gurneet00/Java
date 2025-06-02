/*
 * ================================================================================
 * TESTPAD QUESTION EXTRACTOR
 * ================================================================================
 *
 * This application automates the extraction of question data from the Testpad
 * assessment platform. It reads URLs from an Excel file, navigates to each URL,
 * extracts question information, and saves the data to an output Excel file.
 *
 * Features:
 * - Automated login to Testpad platform
 * - Bulk processing of question URLs
 * - Support for multiple question types (MCQ, Coding, Multiple Questions)
 * - Handles both editable and locked questions
 * - Comprehensive error handling and logging
 *
 * Author: [Your Name]
 * Version: 2.0
 * Last Modified: [Current Date]
 *
 * ================================================================================
 */

package CQlinks;

// ================================================================================
// IMPORTS SECTION
// ================================================================================

// Apache POI imports for Excel file handling
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// Selenium WebDriver imports for web automation
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

// Java standard library imports
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * ================================================================================
 * MAIN CLASS: Testpadlinkz
 * ================================================================================
 *
 * This class contains all the functionality for extracting question data from
 * the Testpad platform. It uses Selenium WebDriver for web automation and
 * Apache POI for Excel file manipulation.
 */
public class Testpadlinkz {

    // ================================================================================
    // CONFIGURATION CONSTANTS
    // ================================================================================

    /** URL for the Testpad login page */
    private static final String LOGIN_URL = "https://assess.testpad.chitkara.edu.in/login";

    /** Email address for authentication */
    private static final String EMAIL = "gurharneet.singh@codequotient.com";

    /** Password for authentication */
    private static final String PASSWORD = "Gurneet@08";

    /** Path to the input Excel file containing URLs to process */
    private static final String INPUT_FILE_PATH = "C:\\Users\\ASUS\\Documents\\Links.xlsx";

    /** Path to the output Excel file where extracted data will be saved */
    private static final String OUTPUT_FILE_PATH = "C:\\Users\\ASUS\\Documents\\Ques.xlsx";

    /** Base URL for the Testpad platform */
    private static final String BASE_URL = "https://assess.testpad.chitkara.edu.in";

    static public void main(String[] args) {
        WebDriver web = null;
        FileOutputStream outputStream = null;
        XSSFWorkbook inputWorkbook = null;
        XSSFWorkbook outputWorkbook = null;
        int rc = 0;

        try {
            System.out.println("Starting Testpad link extraction...");

            // Initialize WebDriver
            web = new ChromeDriver();
            System.out.println("WebDriver initialized");

            // Login to the platform
            loginToTestpad(web);
            System.out.println("Successfully logged in");

            // Read input Excel file with URLs
            System.out.println("Reading input file: " + INPUT_FILE_PATH);
            inputWorkbook = readInputExcel(INPUT_FILE_PATH);
            XSSFSheet inputSheet = inputWorkbook.getSheetAt(0);
            int rowCount = inputSheet.getLastRowNum();
            System.out.println("Found " + rowCount + " rows in input file");

            // Create output Excel file
            System.out.println("Creating output file: " + OUTPUT_FILE_PATH);
            outputWorkbook = new XSSFWorkbook();
            XSSFSheet outputSheet = outputWorkbook.createSheet("Sheet1");
            outputStream = new FileOutputStream(OUTPUT_FILE_PATH);

            // Process each URL from input Excel
            for (int i = 0; i <= rowCount; i++) {
                XSSFRow row = inputSheet.getRow(i);
                if (row != null) {
                    org.apache.poi.ss.usermodel.Cell cell = row.getCell(0);
                    if (cell != null) {
                        String url = cell.toString();
                        System.out.println("Processing URL: " + url);
                        rc = processUrl(web, url, outputSheet, rc);
                    } else {
                        System.out.println("Warning: Cell at row " + i + ", column 0 is null. Skipping this row.");
                    }
                } else {
                    System.out.println("Warning: Row " + i + " is null. Skipping this row.");
                }
            }

            // Save and close resources
            System.out.println("Writing output to file...");
            outputWorkbook.write(outputStream);
            System.out.println("Data extraction completed successfully!");

        } catch (Exception e) {
            System.err.println("Error occurred during execution: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Clean up resources
            try {
                if (outputStream != null) {
                    outputStream.close();
                    System.out.println("Output stream closed");
                }
                if (inputWorkbook != null) {
                    inputWorkbook.close();
                    System.out.println("Input workbook closed");
                }
                if (outputWorkbook != null) {
                    outputWorkbook.close();
                    System.out.println("Output workbook closed");
                }
                if (web != null) {
                    web.quit();  // Using quit() instead of close() to ensure all browser windows are closed
                    System.out.println("WebDriver closed");
                }
            } catch (Exception e) {
                System.err.println("Error while closing resources: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Login to the Testpad platform
     * @param web WebDriver instance
     * @throws RuntimeException if login fails
     */
    private static void loginToTestpad(WebDriver web) {
        try {
            System.out.println("Navigating to login page: " + LOGIN_URL);
            web.get(LOGIN_URL);
            web.manage().window().maximize();

            // Wait for the login iframe to be available
            Thread.sleep(2000);

            System.out.println("Switching to login iframe");
            web.switchTo().frame("loginIframe");

            System.out.println("Entering credentials");
            web.findElement(By.xpath("//input[@id='email']")).sendKeys(EMAIL);
            web.findElement(By.id("password")).sendKeys(PASSWORD);

            System.out.println("Submitting login form");
            web.findElement(By.id("submit")).click();

            // Wait for login to complete
            Thread.sleep(3000);

            // Verify login was successful
            if (web.getCurrentUrl().contains("login")) {
                throw new RuntimeException("Login failed. Still on login page.");
            }

            System.out.println("Login successful");
        } catch (Exception e) {
            System.err.println("Error during login: " + e.getMessage());
            throw new RuntimeException("Failed to login: " + e.getMessage(), e);
        }
    }

    /**
     * Read the input Excel file
     * @param filePath Path to the input Excel file
     * @return XSSFWorkbook instance
     * @throws IOException If file cannot be read
     */
    private static XSSFWorkbook readInputExcel(String filePath) throws IOException {
        FileInputStream fileInputStream = null;
        try {
            System.out.println("Opening input file: " + filePath);
            fileInputStream = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

            // Verify the workbook has at least one sheet
            if (workbook.getNumberOfSheets() == 0) {
                throw new IOException("Excel file contains no sheets");
            }

            System.out.println("Successfully read Excel file with " + workbook.getNumberOfSheets() + " sheet(s)");
            return workbook;
        } catch (IOException e) {
            System.err.println("Error reading Excel file: " + e.getMessage());
            throw e;
        } finally {
            // Close the input stream if it was opened
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    System.err.println("Error closing file input stream: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Process a URL and extract question IDs
     * @param web WebDriver instance
     * @param url URL to process
     * @param outputSheet Output Excel sheet
     * @param rowCounter Current row counter for output
     * @return Updated row counter
     * @throws InterruptedException If thread sleep is interrupted
     */
    private static int processUrl(WebDriver web, String url, XSSFSheet outputSheet, int rowCounter) throws InterruptedException {
        try {
            web.get(url);
            Thread.sleep(1000);

            // Find all question elements
            List<WebElement> questions = web.findElements(By.xpath("//div[contains(@class,\"row draggable-content-element table-body\")]"));
            System.out.println("Found " + questions.size() + " questions");
            int count = questions.size();

            if (count == 0) {
                System.out.println("No questions found at URL: " + url);
                return rowCounter;
            }

            // Extract question IDs
            String[] questionIds = new String[count];
            for (int g = 0; g < count; g++) {
                WebElement question = questions.get(g);
                if (question != null) {
                    String id = question.getAttribute("id");
                    if (id != null && !id.isEmpty()) {
                        questionIds[g] = id;
                    } else {
                        System.out.println("Warning: Question at index " + g + " has no ID. Skipping.");
                        questionIds[g] = "";
                    }
                }
            }

            // Process each question
            for (int itr = 0; itr < count; itr++) {
                String questionId = questionIds[itr];
                if (questionId != null && !questionId.isEmpty()) {
                    try {
                        rowCounter = processQuestion(web, questionId, outputSheet, rowCounter);
                    } catch (Exception e) {
                        System.out.println("Error processing question ID: " + questionId + ". Error: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }

            return rowCounter;
        } catch (Exception e) {
            System.out.println("Error processing URL: " + url + ". Error: " + e.getMessage());
            e.printStackTrace();
            return rowCounter;
        }
    }

    /**
     * Process a single question
     * @param web WebDriver instance
     * @param questionId Question ID
     * @param outputSheet Output Excel sheet
     * @param rowCounter Current row counter
     * @return Updated row counter
     * @throws InterruptedException If thread sleep is interrupted
     */
    private static int processQuestion(WebDriver web, String questionId, XSSFSheet outputSheet, int rowCounter) throws InterruptedException {
        // Navigate to question edit page
        web.get(BASE_URL + "/quest/add/" + questionId);
        Thread.sleep(1000);

        // Create new row in output Excel
        XSSFRow outputRow = outputSheet.createRow(rowCounter++);
        int columnCount = 0;
        outputRow.createCell(columnCount++).setCellValue(questionId);

        // Check if question is editable
        if (Objects.equals(web.getCurrentUrl(), BASE_URL + "/quest/add/" + questionId)) {
            processEditableQuestion(web, questionId, outputRow, columnCount);
        } else {
            processLockedQuestion(web, questionId, outputRow, columnCount);
        }

        return rowCounter;
    }

    /**
     * Process an editable question
     * @param web WebDriver instance
     * @param questionId Question ID
     * @param outputRow Output Excel row
     * @param columnCount Current column counter
     * @throws InterruptedException If thread sleep is interrupted
     */
    private static void processEditableQuestion(WebDriver web, String questionId, XSSFRow outputRow, int columnCount) {
        // Extract basic question information
        String title = web.findElement(By.xpath("//*[@name=\"txtQuesTitle\"]")).getAttribute("value");
        String score = web.findElement(By.xpath("//*[@name=\"score\"]")).getAttribute("value");
        List<WebElement> keywords = web.findElements(By.xpath("//*[@class=\"tag-editor ui-sortable\"]//div[2]"));
        String type = web.findElement(By.xpath("//*[@data-id=\"type\"]")).getText();
        String description = web.findElement(By.xpath("//*[@id=\"editorQuil\"]/div[1]")).getText();

        // Add basic information to output row
        outputRow.createCell(columnCount++).setCellValue(title);
        outputRow.createCell(columnCount++).setCellValue(type);
        outputRow.createCell(columnCount++).setCellValue(score);
        outputRow.createCell(columnCount++).setCellValue(BASE_URL + "/quest/preview/" + questionId);
        outputRow.createCell(columnCount++).setCellValue(description);

        // Process based on question type
        if (Objects.equals(type, "MCQ")) {
            columnCount = processMCQQuestion(web, outputRow, columnCount);
        } else if (Objects.equals(type, "Coding")) {
            columnCount = processCodingQuestion(web, outputRow, columnCount);
        } else if (Objects.equals(type, "Multiple Questions")) {
            columnCount = processMultipleQuestion(web, outputRow, columnCount);
        }

        // Add keywords
        for (WebElement keyword : keywords) {
            outputRow.createCell(columnCount++).setCellValue(keyword.getText());
        }
    }

    /**
     * Process an MCQ question
     * @param web WebDriver instance
     * @param outputRow Output Excel row
     * @param columnCount Current column counter
     * @return Updated column counter
     */
    private static int processMCQQuestion(WebDriver web, XSSFRow outputRow, int columnCount) {
        List<WebElement> options = web.findElements(By.xpath("//*[@class=\"mcq-option\"]//textarea"));
        int correctOption = 0;

        for (int z = 0; z < options.size(); z++) {
            outputRow.createCell(columnCount++).setCellValue(options.get(z).getText());
            int optionIndex = z + 1;
            WebElement optionCheckbox = web.findElement(By.xpath("//div[@class='mcq-option'][" + optionIndex + "]//div[3]//input"));
            if (optionCheckbox.isSelected()) {
                correctOption = optionIndex;
            }
        }

        outputRow.createCell(columnCount++).setCellValue(correctOption);
        return columnCount;
    }

    /**
     * Process a Coding question
     * @param web WebDriver instance
     * @param outputRow Output Excel row
     * @param columnCount Current column counter
     * @return Updated column counter
     */
    private static int processCodingQuestion(WebDriver web, XSSFRow outputRow, int columnCount) {
        String language = web.findElement(By.xpath("//*[@class=\"change-lang-container row\"]//button//div[@class=\"filter-option-inner-inner\"]")).getText();
        outputRow.createCell(columnCount++).setCellValue(language);
        return columnCount;
    }

    /**
     * Process a Multiple Questions type
     * @param web WebDriver instance
     * @param outputRow Output Excel row
     * @param columnCount Current column counter
     * @return Updated column counter
     */
    private static int processMultipleQuestion(WebDriver web, XSSFRow outputRow, int columnCount) {
        List<WebElement> options = web.findElements(By.xpath("//*[@class=\"code-option code-option-loop\"]"));

        for (int xc = 0; xc < options.size(); xc++) {
            String questionText = options.get(xc).findElement(By.xpath(".//*[@class=\"col-4 input-box\"]")).getText();
            String answerText = options.get(xc).findElement(By.xpath(".//*[@class=\"col-4 input-box input-box-2\"]")).getText();
            String combinedText = "question\n" + questionText + "\nAnswer\n" + answerText;
            outputRow.createCell(columnCount++).setCellValue(combinedText);
        }

        return columnCount;
    }

    /**
     * Process a locked question
     * @param web WebDriver instance
     * @param questionId Question ID
     * @param outputRow Output Excel row
     * @param columnCount Current column counter
     * @throws InterruptedException If thread sleep is interrupted
     */
    private static void processLockedQuestion(WebDriver web, String questionId, XSSFRow outputRow, int columnCount) throws InterruptedException {
        // Navigate to question preview page
        web.get(BASE_URL + "/quest/preview/" + questionId);

        // Extract basic question information
        String title = web.findElement(By.xpath("//*[@class=\"question-name\"]")).getText();
        String description = web.findElement(By.xpath("//*[@class=\"question_info ql-editor\"]")).getText();
        String type = web.findElement(By.xpath("//*[@id=\"question-type-badge\"]")).getText();

        // Add basic information to output row
        outputRow.createCell(columnCount++).setCellValue(title);
        outputRow.createCell(columnCount++).setCellValue(type);
        outputRow.createCell(columnCount++).setCellValue("Locked Question");
        outputRow.createCell(columnCount++).setCellValue(BASE_URL + "/quest/" + questionId);
        outputRow.createCell(columnCount++).setCellValue(description);

        // Process based on question type
        if (type.trim().equals("CODING")) {
            columnCount = processLockedCodingQuestion(web, outputRow, columnCount);
        } else if (type.trim().equals("MCQ")) {
            columnCount = processLockedMCQQuestion(web, outputRow, columnCount);
        }
    }

    /**
     * Process a locked Coding question
     * @param web WebDriver instance
     * @param outputRow Output Excel row
     * @param columnCount Current column counter
     * @return Updated column counter
     */
    private static int processLockedCodingQuestion(WebDriver web, XSSFRow outputRow, int columnCount) {
        web.findElement(By.xpath("//*[@class=\"container-fluid dashboard-container\"]//button[@class=\"btn dropdown-toggle btn-light\"]")).click();
        List<WebElement> languages = web.findElements(By.xpath("//*[@class=\"dropdown-menu inner show\"]//li"));

        StringBuilder languageList = new StringBuilder();
        for (int x = 0; x < languages.size(); x++) {
            if (x > 0) languageList.append(",");
            languageList.append(languages.get(x).getText());
        }

        outputRow.createCell(columnCount++).setCellValue(languageList.toString());
        return columnCount;
    }

    /**
     * Process a locked MCQ question
     * @param web WebDriver instance
     * @param outputRow Output Excel row
     * @param columnCount Current column counter
     * @return Updated column counter
     * @throws InterruptedException If thread sleep is interrupted
     */
    private static int processLockedMCQQuestion(WebDriver web, XSSFRow outputRow, int columnCount) throws InterruptedException {
        int correctOption = 0;
        List<WebElement> options = web.findElements(By.xpath("//*[@class=\"label-choose-answer\"]"));

        for (int gh = 0; gh < options.size(); gh++) {
            String optionText = options.get(gh).findElement(By.xpath(".//*[@class=\"mb-0\"]")).getText();
            outputRow.createCell(columnCount++).setCellValue(optionText);

            // Check if this is the correct option
            options.get(gh).click();
            web.findElement(By.xpath(".//*[@class=\"submit_choices\"]")).click();
            Thread.sleep(1000);

            if (options.get(gh).findElement(By.xpath(".//*[@class=\"mcq-result-text\"]")).getText().trim().equals("correct answer")) {
                correctOption = gh + 1;
            }
        }

        outputRow.createCell(columnCount++).setCellValue(correctOption);
        return columnCount;
    }
}
