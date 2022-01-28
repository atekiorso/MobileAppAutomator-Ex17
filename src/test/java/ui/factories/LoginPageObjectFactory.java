package ui.factories;

import org.openqa.selenium.remote.RemoteWebDriver;
import ui.LoginPageObject;
import ui.mobileweb.MobileWebLoginPageObject;
import utils.Platform;

public class LoginPageObjectFactory {
    public static LoginPageObject get(RemoteWebDriver driver) {
        LoginPageObject loginPageObject;
        if (Platform.getInstance().isWebMobile()) {
            loginPageObject =  new MobileWebLoginPageObject(driver);
        } else {
            loginPageObject = null;
        }

        return loginPageObject;
    }
}
