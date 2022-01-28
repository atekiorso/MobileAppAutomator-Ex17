package ui.mobileweb;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.MainPageObject;

public class MobileWebMainPageObject extends MainPageObject {
    static {
        SEARCH_FIELD = "css:button#searchIcon";
        MAIN_MENU_BUTTON = "css:label#mw-mf-main-menu-button";
        LOGIN_BUTTON = "xpath://li/a[@class='menu__item--login']";
        READING_LISTS_BUTTON = "xpath://li/a[@class='menu__item--watchlist']";
    }

    public MobileWebMainPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
