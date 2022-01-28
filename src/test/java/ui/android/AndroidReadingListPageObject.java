package ui.android;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.ReadingListPageObject;

public class AndroidReadingListPageObject extends ReadingListPageObject {
    static {
        CREATE_NEW_BUTTON = "id:org.wikipedia:id/create_button";
        READING_LIST_NAME_FIELD = "id:org.wikipedia:id/text_input";
        CONFIRM_CREATION_BUTTON = "id:android:id/button1";
        EXISTING_READING_LIST_TEMPLATE = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/item_title'][@text='{SUBSTRING}']";
        ARTICLE_IN_READING_LIST_TEMPLATE = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']/..";
    }

    public AndroidReadingListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
