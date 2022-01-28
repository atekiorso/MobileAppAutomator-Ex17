package ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class WelcomePageObject extends CorePageObject{
    protected static String SKIP_BUTTON;

    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickSkipButton() {
        this.waitForElementAndClick(SKIP_BUTTON);
    }
}
