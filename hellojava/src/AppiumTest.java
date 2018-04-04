import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumTest
{
    String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
    String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");

    String SAUCE_URL = "http://SAUCE_USERNAME:SAUCE_ACCESS_KEY@ondemand.saucelabs.com:80/wd/hub"
            .replace("SAUCE_USERNAME", SAUCE_USERNAME)
            .replace("SAUCE_ACCESS_KEY", SAUCE_ACCESS_KEY);

    long TIMEOUT = 30;

    @Test
    public void basicTest() throws MalformedURLException
    {
        URL url = new URL(SAUCE_URL);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("deviceName","Android GoogleAPI Emulator");
        capabilities.setCapability("deviceOrientation","portrait");
        capabilities.setCapability("app", "https://raw.githubusercontent.com/appium/sample-code/master/sample-code/apps/VodQA.apk");
        capabilities.setCapability("name", "Sample Android Test");

        AndroidDriver android = new AndroidDriver(url, capabilities);
        WebDriverWait wait = new WebDriverWait(android, TIMEOUT);

        By loginButton = By.xpath("//android.view.ViewGroup[@content-desc=\"login\"]/android.widget.Button");

        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

//        Assert.assertFalse(android.findElement(loginButton).isDisplayed());

        android.quit();;
    }
}
