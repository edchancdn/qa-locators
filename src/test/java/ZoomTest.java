import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class ZoomTest {

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
        driver.get("https://zoom.us/");
        driver.manage().window().maximize();
    }

    @Test
    public void tc1() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.top-contactsales.top-sales[href='https://explore.zoom.us/contactsales']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#email"))).sendKeys("johndoe@mail.com");
        driver.findElement(By.cssSelector("#company")).sendKeys("Acme Inc");
        driver.findElement(By.cssSelector("#first_name")).sendKeys("John");
        driver.findElement(By.cssSelector("#last_name")).sendKeys("Doe");

        Select drpEmployeeCnt = new Select(driver.findElement(By.cssSelector("#employee_count")));
        drpEmployeeCnt.selectByVisibleText("51-250");

        Assert.assertEquals(driver.getTitle(), "Contact Sales | Zoom");
    }

    @Test
    public void tc2() {
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btnJoinMeeting"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#join-confno"))).sendKeys("123456789");

        Assert.assertEquals(driver.getTitle(), "Join Meeting - Zoom");
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}
