package ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Resources;

import java.time.Duration;
import java.util.regex.Pattern;

public class CorePageObject {
    protected RemoteWebDriver driver;
    protected Resources resources;
    public static final long
            DEFAULT_WAITING_TIMEOUT_IN_SECONDS = 5,
            LONG_WAITING_TIMEOUT_IN_SECONDS = 10,
            DEFAULT_SWIPE_DURATION_IN_MILLIS = 500;
    public static final String
            REPLACEABLE_TEMPLATE_SUBSTRING = "SUBSTRING",
            BY_ID = "id",
            BY_ACCESSIBILITY_ID = "accessibility_id",
            BY_XPATH = "xpath",
            BY_CSS = "css";

    public CorePageObject(RemoteWebDriver driver) {
        this.driver = driver;
        resources = new Resources();
    }

    /* TEMPLATES METHODS */
    protected String replaceSubstringInTemplate(String template, String replacement) {
        final String target = "{" + REPLACEABLE_TEMPLATE_SUBSTRING + "}";

        return template.replace(target, replacement);
    }
    /* TEMPLATES METHODS */

    protected void clickOnPoint(Point point) {
        if (driver instanceof AppiumDriver<?>) {
            TouchAction<?> touchAction = new TouchAction<>((AppiumDriver<?>)driver);
            PointOption<?> pointOption = new PointOption<>();

            pointOption.withCoordinates(point);
            touchAction
                    .tap(pointOption)
                    .waitAction()
                    .perform();
        }
    }

    protected boolean isElementPresent(String locatorWithType) {
        return isElementPresent(locatorWithType, DEFAULT_WAITING_TIMEOUT_IN_SECONDS);
    }

    @SuppressWarnings("SameParameterValue")
    protected boolean isElementPresent(String locatorWithType, long timeoutInSeconds) {
        try {
            waitForElementPresent(locatorWithType, timeoutInSeconds);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    protected WebElement waitForElementAndClick(String locatorWithType) {
        return waitForElementAndClick(locatorWithType, DEFAULT_WAITING_TIMEOUT_IN_SECONDS);
    }

    protected WebElement waitForElementAndClick(String locatorWithType, long timeoutInSeconds) {
        WebElement element = waitForElementClickable(locatorWithType, timeoutInSeconds);
        element.click();

        return element;
    }

    private WebElement waitForElementClickable(String locatorWithType, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage("Не найден кликабельный элемент! " + locatorWithType);

        return wait.until(ExpectedConditions.elementToBeClickable(getLocatorByString(locatorWithType)));
    }

    @SuppressWarnings("UnusedReturnValue")
    protected WebElement waitForElementAndSendKeys(String locatorWithType, String charSequences) {
        return waitForElementAndSendKeys(locatorWithType, charSequences, DEFAULT_WAITING_TIMEOUT_IN_SECONDS);
    }

    @SuppressWarnings("SameParameterValue")
    protected WebElement waitForElementAndSendKeys(String locatorWithType, String charSequences, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locatorWithType, timeoutInSeconds);
        element.clear();
        element.sendKeys(charSequences);

        return element;
    }

    protected void waitForElementAndSwipeLeft(String locatorWithType) {
        waitForElementAndSwipeLeft(locatorWithType, DEFAULT_WAITING_TIMEOUT_IN_SECONDS);
    }

    @SuppressWarnings("SameParameterValue")
    protected void waitForElementAndSwipeLeft(String locatorWithType, long timeoutInSeconds) {
        if (driver instanceof AppiumDriver<?>) {
            WebElement element = waitForElementPresent(locatorWithType, timeoutInSeconds);

            Point elementLocation = element.getLocation();
            int elementYCenter = element.getLocation().y + (element.getSize().height / 2);
            new TouchAction<>((AppiumDriver<?>) driver)
                    .press(PointOption.point(elementLocation.x + element.getSize().width, elementYCenter))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(DEFAULT_SWIPE_DURATION_IN_MILLIS)))
                    .moveTo(PointOption.point(elementLocation.x, elementYCenter))
                    .release()
                    .perform();
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    protected WebElement waitForImageElementPresent(String resourcesImageFileName) throws Exception {
        String imageBase64 = resources.getImageBase64(resourcesImageFileName);
        By imageBy = MobileBy.image(imageBase64);

        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAITING_TIMEOUT_IN_SECONDS);
        wait.withMessage("Не найден элемент изображения, соответствующий ресурсному файлу: '" + resourcesImageFileName + "'");

        return wait.until(ExpectedConditions.presenceOfElementLocated(imageBy));
    }

    @SuppressWarnings("UnusedReturnValue")
    protected WebElement waitForElementPresent(String locatorWithType) {
        return waitForElementPresent(locatorWithType, DEFAULT_WAITING_TIMEOUT_IN_SECONDS);
    }

    protected WebElement waitForElementPresent(String locatorWithType, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage("Не найден элемент! " + locatorWithType);

        return wait.until(ExpectedConditions.presenceOfElementLocated(getLocatorByString(locatorWithType)));
    }

    private By getLocatorByString(String locatorWithType) {
        String[] typeAndLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = typeAndLocator[0];
        String locator = typeAndLocator[1];
        By result;

        switch (byType) {
            case BY_ID:
                result = By.id(locator);
                break;
            case BY_ACCESSIBILITY_ID:
                result = MobileBy.AccessibilityId(locator);
                break;
            case BY_XPATH:
                result = By.xpath(locator);
                break;
            case  BY_CSS:
                result = By.cssSelector(locator);
                break;
            default:
                throw new IllegalArgumentException("Не удалось определить тип локатора по строке: '" + locatorWithType + "'");
        }

        return result;
    }
}
