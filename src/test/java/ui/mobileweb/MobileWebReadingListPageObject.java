package ui.mobileweb;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.ReadingListPageObject;

public class MobileWebReadingListPageObject extends ReadingListPageObject {
    static {
        ARTICLE_IN_READING_LIST_TEMPLATE = "css:li[class='page-summary with-watchstar'][title='{SUBSTRING}']";
        WATCHED_BUTTON = "css:li[title='Star Wars (film)'] a.mw-ui-icon";
    }

    public MobileWebReadingListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
