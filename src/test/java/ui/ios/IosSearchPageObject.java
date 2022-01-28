package ui.ios;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.SearchPageObject;

public class IosSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INPUT_FIELD = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia' and (following-sibling::XCUIElementTypeButton[@name='Cancel'])]";
        CANCEL_SEARCH_BUTTON = "id:Cancel";
        SEARCH_RESULT_TITLE = "id:{SUBSTRING}";
    }

    public IosSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
