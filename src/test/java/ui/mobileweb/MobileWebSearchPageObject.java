package ui.mobileweb;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.SearchPageObject;

public class MobileWebSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INPUT_FIELD = "xpath://form/input[@name='search']";
        SEARCH_RESULT_TITLE = "css:li[title='{SUBSTRING}']";
    }

    public MobileWebSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
