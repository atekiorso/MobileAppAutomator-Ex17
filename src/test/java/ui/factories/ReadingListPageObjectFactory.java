package ui.factories;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.mobileweb.MobileWebReadingListPageObject;
import utils.Platform;
import ui.ReadingListPageObject;
import ui.android.AndroidReadingListPageObject;
import ui.ios.IosReadingListPageObject;

public class ReadingListPageObjectFactory {
    public static ReadingListPageObject get(RemoteWebDriver driver) {
        final String platformNameFromEnv = Platform.getInstance().getPlatformNameFromEnv();
        ReadingListPageObject readingListPageObject;

        switch (platformNameFromEnv) {
            case Platform.ANDROID_PLATFORM:
                readingListPageObject = new AndroidReadingListPageObject(driver);
                break;
            case Platform.IOS_PLATFORM:
                readingListPageObject = new IosReadingListPageObject(driver);
                break;
            case Platform.MOBILE_WEB_PLATFORM:
                readingListPageObject = new MobileWebReadingListPageObject(driver);
                break;
            default:
                readingListPageObject = null;
                break;
        }

        return readingListPageObject;
    }
}
