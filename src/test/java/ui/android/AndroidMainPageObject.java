package ui.android;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.MainPageObject;

public class AndroidMainPageObject extends MainPageObject {
    static {
        SEARCH_FIELD = "id:org.wikipedia:id/search_container";
        READING_LISTS_BUTTON = "accessibility_id:My lists";
    }

    public AndroidMainPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
