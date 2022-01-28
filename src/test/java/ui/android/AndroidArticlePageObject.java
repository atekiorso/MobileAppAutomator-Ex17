package ui.android;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        BACK_BUTTON = "accessibility_id:Navigate up";
        SAVE_TO_READING_LIST_BUTTON = "accessibility_id:Add this article to a reading list";
        GOT_IT_BUTTON = "id:org.wikipedia:id/onboarding_button";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
