package ui.android;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INPUT_FIELD = "id:org.wikipedia:id/search_src_text";
        CANCEL_SEARCH_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_TITLE = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']";
        RESULTS_TITLES = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title']";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
