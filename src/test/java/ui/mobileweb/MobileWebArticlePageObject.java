package ui.mobileweb;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.ArticlePageObject;

public class MobileWebArticlePageObject extends ArticlePageObject {
    static {
        SAVE_TO_READING_LIST_BUTTON = "css:a#ca-watch[title='Watch'],[title='Add this page to your watchlist']";
        REMOVE_FROM_READING_LIST_BUTTON = "css:a#ca-watch[title='Unwatch'],[title='Remove this page from your watchlist']";
        IMAGE_ELEMENT = "css:td.infobox-image a.image img[alt='{SUBSTRING}']";
    }

    public MobileWebArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
