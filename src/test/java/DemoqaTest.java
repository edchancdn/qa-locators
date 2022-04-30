import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class DemoqaTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeSuite
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void tc1() throws InterruptedException {
        driver.get("https://demoqa.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='home-body']//div[2]//div[1]//div[2]//*[name()='svg']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Practice Form']"))).click();
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Doe");
        driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("johndoe@mail.com");

        Thread.sleep(3000);
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}
