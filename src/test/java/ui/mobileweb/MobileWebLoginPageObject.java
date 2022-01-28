package ui.mobileweb;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.LoginPageObject;

public class MobileWebLoginPageObject extends LoginPageObject {
    static {
        USER_NAME = "css:input#wpName1";
        PASSWORD = "css:input#wpPassword1";
        LOG_IN = "css:button#wpLoginAttempt";
    }

    public MobileWebLoginPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
