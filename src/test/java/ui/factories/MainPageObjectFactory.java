package ui.factories;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.mobileweb.MobileWebMainPageObject;
import utils.Platform;
import ui.MainPageObject;
import ui.android.AndroidMainPageObject;
import ui.ios.IosMainPageObject;

public class MainPageObjectFactory {
    public static MainPageObject get(RemoteWebDriver driver) {
        MainPageObject mainPageObject;
        String platformNameFromEnv = Platform.getInstance().getPlatformNameFromEnv();

        switch (platformNameFromEnv) {
            case Platform.ANDROID_PLATFORM:
                mainPageObject = new AndroidMainPageObject(driver);
                break;
            case Platform.IOS_PLATFORM:
                mainPageObject = new IosMainPageObject(driver);
                break;
            case Platform.MOBILE_WEB_PLATFORM:
                mainPageObject = new MobileWebMainPageObject(driver);
                break;
            default:
                mainPageObject = null;
                break;
        }

        return mainPageObject;
    }
}
