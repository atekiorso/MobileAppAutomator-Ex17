package ui.factories;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.mobileweb.MobileWebArticlePageObject;
import utils.Platform;
import ui.ArticlePageObject;
import ui.android.AndroidArticlePageObject;
import ui.ios.IosArticlePageObject;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(RemoteWebDriver driver) {
        final String platformNameFromEnv = Platform.getInstance().getPlatformNameFromEnv();
        ArticlePageObject articlePageObject;

        switch (platformNameFromEnv) {
            case Platform.ANDROID_PLATFORM:
                articlePageObject = new AndroidArticlePageObject(driver);
                break;
            case Platform.IOS_PLATFORM:
                articlePageObject = new IosArticlePageObject(driver);
                break;
            case Platform.MOBILE_WEB_PLATFORM:
                articlePageObject = new MobileWebArticlePageObject(driver);
                break;
            default:
                articlePageObject = null;
                break;
        }

        return articlePageObject;
    }
}
