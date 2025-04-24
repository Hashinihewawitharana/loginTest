import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginAutomationTest {

    WebDriver driver;
    @BeforeMethod
    public void LoginTest(){
        System.setProperty("webDriver.chrome.driver","D:\\selenium web driver\\chromedriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");

    }

@Test
    public void positiveLoginTest() {
        // Fill in valid credentials
        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click();

        // Assert URL is the dashboard URL
        String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(actualUrl, expectedUrl, "User is not redirected to dashboard!");

        //  Assert some text is visible
        WebElement successMsg = driver.findElement(By.tagName("h1"));
        assertEquals(successMsg.getText(), "Logged In Successfully", "Success message not found!");

        // Click the Logout button
        WebElement logoutButton = driver.findElement(By.xpath("//a[text()='Log out']"));
        assertTrue(logoutButton.isDisplayed(), "Log out button is not visible on the page.");
        logoutButton.click();

        //  Verify redirection back to login page
        String afterLogoutUrl = driver.getCurrentUrl();
        assertEquals(afterLogoutUrl, "https://practicetestautomation.com/practice-test-login/", "Did not return to login page after logout. Current URL: " + afterLogoutUrl);

    }

    @Test
    public void negativeUsernameTest() {
        driver.findElement(By.id("username")).sendKeys("incorrectUser");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click();

        WebElement error = driver.findElement(By.id("error"));
        assertTrue(error.isDisplayed(), "Error message not displayed.");
        assertEquals(error.getText().trim(), "Your username is invalid!", "Unexpected error message text.");
    }

    @Test
    public void negativePasswordTest() {
        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("incorrectPassword");
        driver.findElement(By.id("submit")).click();

        WebElement error = driver.findElement(By.id("error"));
        assertTrue(error.isDisplayed(), "Error message not displayed.");
        assertEquals(error.getText().trim(), "Your password is invalid!", "Unexpected error message text.");
    }


}



