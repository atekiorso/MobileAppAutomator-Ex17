package ui.ios;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.ArticlePageObject;

public class IosArticlePageObject extends ArticlePageObject {
    static {
        BACK_BUTTON = "id:Search";
        SAVE_TO_READING_LIST_BUTTON = "id:Save for later";
        ADD_TO_READING_LIST_BUTTON = "id:Add “{SUBSTRING}” to a reading list?";
    }

    public IosArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
