import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

public class LoginAutomationTest {



        public static void main(String[] args) throws InterruptedException {
            // Set ChromeDriver path
            System.setProperty("webdriver.chrome.driver", "D:\\selenium web driver\\chromedriver\\chromedriver-win64\\chromedriver.exe");

            // Initialize WebDriver
            WebDriver driver = new ChromeDriver();

            // Open OrangeHRM login page
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            driver.manage().window().maximize();
            Thread.sleep(2000); // Wait for page to load

            // **Force English Language Selection**
            try {
                WebElement languageDropdown = driver.findElement(By.xpath("//i[contains(@class,'bi-translate')]"));
                languageDropdown.click();
                Thread.sleep(1000);

                WebElement englishOption = driver.findElement(By.xpath("//span[contains(text(),'English')]"));
                englishOption.click();
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("⚠️ Language selection dropdown not found, skipping...");
            }

            // Perform valid login
            testValidLogin(driver);
            Thread.sleep(3000); // Wait for dashboard to load

            // Close the browser
            driver.quit();
        }

        public static void testValidLogin(WebDriver driver) throws InterruptedException {
            // Enter valid credentials
            driver.findElement(By.name("username")).sendKeys("Admin");
            driver.findElement(By.name("password")).sendKeys("admin123");
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Thread.sleep(2000);

            // Verify login success
            if (Objects.requireNonNull(driver.getCurrentUrl()).contains("dashboard")) {
                System.out.println("✅ Valid login successful");
            } else {
                System.out.println("❌ Valid login failed");
            }
        }


}
