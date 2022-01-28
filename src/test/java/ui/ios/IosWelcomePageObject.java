package ui.ios;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.WelcomePageObject;

public class IosWelcomePageObject extends WelcomePageObject {
    static {
        SKIP_BUTTON = "xpath://XCUIElementTypeButton[@name='Skip']";
    }

    public IosWelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
