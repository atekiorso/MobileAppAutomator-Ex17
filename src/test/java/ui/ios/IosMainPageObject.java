package ui.ios;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.MainPageObject;

public class IosMainPageObject extends MainPageObject {
    static {
        SEARCH_FIELD = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia' and not(following-sibling::*)]";
        READING_LISTS_BUTTON = "xpath://XCUIElementTypeButton[@name='Saved']";
    }

    public IosMainPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
