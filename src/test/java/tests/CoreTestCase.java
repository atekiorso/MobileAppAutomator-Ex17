package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.Setting;
import junit.framework.TestCase;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Platform;

public class CoreTestCase extends TestCase {
    protected RemoteWebDriver driver;

    protected void setUp() throws Exception {
        driver = Platform.getInstance().getDriverByPlatformFromEnv();

        if (driver instanceof AppiumDriver<?>) {
            ((AppiumDriver<?>) driver).setSetting(Setting.FIX_IMAGE_TEMPLATE_SIZE, true);
        } else {
            driver.get("https://en.m.wikipedia.org");
        }
    }

    protected void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
