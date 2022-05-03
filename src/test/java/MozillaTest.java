import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class MozillaTest {
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
        driver.get("https://developer.mozilla.org");
        driver.manage().window().maximize();
    }

    private Boolean webElementExists(By webElementSelector) {
        return driver.findElements(webElementSelector).size() > 0;
    }

    /* Test Case: iFrame locator
     * Steps:
     * 1. Load base site.
     * 2. Navigate to the documentation page for iFrames.
     * 3. Locate iFrame and switch to it.
     * Expected Result:
     * Web element Reset button exists inside the frame.
     */
    @Test
    public void tc1() {
        driver.navigate().to("https://developer.mozilla.org/en-US/docs/Web/HTML/Element/iframe");

        //WebElement iframe1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div>iframe[title='MDN Web Docs Interactive Example']")));
        //driver.switchTo().frame(iframe1);

        // This is an alternate code to combine the 2 lines of code above
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("div>iframe[title='MDN Web Docs Interactive Example']")));

        Assert.assertTrue(webElementExists(By.cssSelector("#reset")));
    }

    /* Negative Test Case: iFrame locator
     * Steps:
     * 1. Load base site.
     * 2. Navigate to the documentation page for iFrames.
     * 3. Intentionally skip the step to switch into the frame.
     * 4. Locate web element Reset button (in the frame).
     * Expected Result:
     * Web element Reset button cannot be located outside the frame.
     */
    @Test
    public void tc2() {
        driver.navigate().to("https://developer.mozilla.org/en-US/docs/Web/HTML/Element/iframe");

        WebElement iframe1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div>iframe[title='MDN Web Docs Interactive Example']")));
        // Intentionally skip to switch into this frame.

        Assert.assertFalse(webElementExists(By.cssSelector("#reset")));
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}
