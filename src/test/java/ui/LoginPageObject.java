package ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class LoginPageObject extends CorePageObject {
    protected static String
            USER_NAME,
            PASSWORD,
            LOG_IN;

    public LoginPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void sendKeysUserName(String userName) {
        this.waitForElementAndSendKeys(USER_NAME, userName);
    }

    public void sendKeysPassword(String password) {
        this.waitForElementAndSendKeys(PASSWORD, password);
    }

    public void clickLogInButton() {
        this.waitForElementAndClick(LOG_IN);
    }
}
