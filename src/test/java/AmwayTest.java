import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class AmwayTest {
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
        driver.manage().window().maximize();
        driver.get("https://www.amway.ca/en_CA/Shop/c/1");

        WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        search.sendKeys("glow maker");
        search.sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//a[contains(text(),'Artistry Studio™ Glow-Tini Cocktail Booster Glow M')]")).click();

        // wait until the web element is destroyed from DOM
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]"))));
        // wait until the web element is added back to the DOM and is clickable
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]")))).click();

        WebElement cart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@test-id='cart-button']")));
        Assert.assertTrue(wait.until(ExpectedConditions.attributeToBe(cart, "innerText", "1")));
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}
