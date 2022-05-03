import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class PragraTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeSuite
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void loadBaseUrl() {
        driver.get("https://pragra.io/");
        driver.manage().window().maximize();
    }

    @Test
    public void tc1() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#__next a[href='/apply']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#first_name"))).sendKeys("John");
        driver.findElement(By.cssSelector("#last_name")).sendKeys("Deer");
        driver.findElement(By.cssSelector("#email")).sendKeys("johndeer@mail.com");
        driver.findElement(By.cssSelector("#program")).sendKeys("QA Automation");
        driver.findElement(By.cssSelector("#phone")).sendKeys("4161234567");
        driver.findElement(By.cssSelector("#msg")).sendKeys("Test message");
        driver.findElement(By.cssSelector("#source_type")).sendKeys("Top reviews");
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}
