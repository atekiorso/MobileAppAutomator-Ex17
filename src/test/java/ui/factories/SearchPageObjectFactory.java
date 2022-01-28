package ui.factories;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.mobileweb.MobileWebSearchPageObject;
import utils.Platform;
import ui.SearchPageObject;
import ui.android.AndroidSearchPageObject;
import ui.ios.IosSearchPageObject;

public class SearchPageObjectFactory {
    public static SearchPageObject get(RemoteWebDriver driver) throws Exception {
        SearchPageObject searchPageObject;
        final String platformNameFromEnv = Platform.getInstance().getPlatformNameFromEnv();

        switch (platformNameFromEnv) {
            case Platform.ANDROID_PLATFORM:
                searchPageObject = new AndroidSearchPageObject(driver);
                break;
            case Platform.IOS_PLATFORM:
                searchPageObject = new IosSearchPageObject(driver);
                break;
            case Platform.MOBILE_WEB_PLATFORM:
                searchPageObject = new MobileWebSearchPageObject(driver);
                break;
            default:
                searchPageObject = null;
                break;
        }

        return searchPageObject;
    }
}
