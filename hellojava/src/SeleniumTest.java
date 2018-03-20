import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumTest {

    protected WebDriver driver;
    protected DesiredCapabilities caps;

    @BeforeMethod
    public void setUp(){

        caps = DesiredCapabilities.edge();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("version", "16.16299");
        caps.setCapability("build", "java-1");
        caps.setCapability("name", "JavaCase");

        String username = System.getenv("SAUCE_USER");
        String accesskey = System.getenv("SAUCE_KEY");

        try {
            URL url = new URL("http://" + username + ":" + accesskey + "@ondemand.saucelabs.com:80/wd/hub");
            driver = new RemoteWebDriver(url, caps);
        } catch (MalformedURLException e){
            System.out.print("Boourns");
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        driver.quit();
    }

    @Test
    public void checkInternetTitle(){
        driver.get("http://the-internet.herokuapp.com");

        String actual = driver.getTitle();
        String expected = "The Internet";

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void checkTheCheckboxes(){
        driver.get("http://the-internet.herokuapp.com/checkboxes");

        String expected = driver.findElement(By.className("example")).getText();

        Assert.assertTrue(expected.contains("Checkboxes"));
    }

}
