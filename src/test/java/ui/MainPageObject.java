package ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class MainPageObject extends CorePageObject {
    protected static String
            SEARCH_FIELD,
            READING_LISTS_BUTTON,
            // MobileWeb only
            MAIN_MENU_BUTTON,
            LOGIN_BUTTON;

    public MainPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickSearchField() {
        this.waitForElementAndClick(SEARCH_FIELD);
    }

    public void clickReadingListsButton() {
        this.waitForElementAndClick(READING_LISTS_BUTTON);
    }

    // MobileWeb only
    public void clickMainMenuButton() {
        this.waitForElementAndClick(MAIN_MENU_BUTTON);
    }

    public void clickLoginButton() {
        this.waitForElementAndClick(LOGIN_BUTTON);
    }
}
