import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;


public class SeleniumParallelTest {

    protected WebDriver driver;
    protected MutableCapabilities options = new ChromeOptions();
    protected String testUrl = "http://localhost:9292/";

    @BeforeMethod
    public void setUp(){
        options.setCapability("platform", "Windows 10");
        options.setCapability("version", "62.0");
        options.setCapability("build", "java-1");
        options.setCapability("name", "JavaCase");
        options.setCapability("seleniumVersion", "3.4.0");
        options.setCapability("tunnelIdentifier", "default");

        String username = System.getenv("SAUCE_USERNAME");
        String accesskey = System.getenv("SAUCE_ACCESS_KEY");

        try {
            URL url = new URL("http://" + username + ":" + accesskey + "@ondemand.saucelabs.com/wd/hub");
            driver = new RemoteWebDriver(url, new DesiredCapabilities(options));
        } catch (MalformedURLException e){
            System.out.print("Boourns");
        }

        ((JavascriptExecutor) driver).executeScript("sauce:context=START THE TEST!!!!11");

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        ((JavascriptExecutor) driver).executeScript("sauce:context=FINISH THE TEST!!!!11");
        ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        driver.quit();
    }

    @Test
    public void checkInternetTitle(){
        driver.get(testUrl);

        String actual = driver.getTitle();
        String expected = "The Internet";

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void checkTheCheckboxes(){
        driver.get(testUrl + "checkboxes");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("example")));

        String expected = driver.findElement(By.className("example")).getText();

        Assert.assertTrue(expected.contains("Checkboxes"));
    }

    @Test
    public void checkTheForm(){
        driver.get(testUrl + "login");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("example")));

        String expected = driver.findElement(By.className("radius")).getText();

        Assert.assertTrue(expected.contains("Login"));
    }

    @Test
    public void checkTheDropdown(){
        driver.get(testUrl + "dropdown");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdown")));

        Assert.assertTrue(driver.findElements(By.id("dropdown")).size() > 0);
    }


}
