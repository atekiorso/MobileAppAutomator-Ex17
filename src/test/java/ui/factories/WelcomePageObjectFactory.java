package ui.factories;

import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Platform;
import ui.WelcomePageObject;
import ui.ios.IosWelcomePageObject;

public class WelcomePageObjectFactory {
    public static WelcomePageObject get(RemoteWebDriver driver) throws Exception {
        final String platformNameFromEnv = Platform.getInstance().getPlatformNameFromEnv();
        WelcomePageObject welcomePageObject;

        if (platformNameFromEnv.equals(Platform.IOS_PLATFORM)) {
            welcomePageObject = new IosWelcomePageObject(driver);
        } else {
            welcomePageObject = null;
        }

        return welcomePageObject;
    }
}
