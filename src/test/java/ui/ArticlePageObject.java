package ui;

import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public abstract class ArticlePageObject extends CorePageObject {
    public static String
            // Android and iOS
            BACK_BUTTON,
            SAVE_TO_READING_LIST_BUTTON,
            // Android only
            GOT_IT_BUTTON,
            // iOS only
            ADD_TO_READING_LIST_BUTTON,
            // MobileWeb only
            REMOVE_FROM_READING_LIST_BUTTON,
            IMAGE_ELEMENT;
    private Point addToReadingListButtonPoint; // iOS only

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickSaveToReadingListButton() {
        // Вначале определяем приблизительные координаты для клика по всплывающему элементу iOS "Add {article_title} to a reading list?",
        // который не успеваем найти по id до его исчезновения
        if ((Platform.getInstance().isIOS()) && (addToReadingListButtonPoint == null)) {
            findAddToReadingListButtonPoint();
        }

        this.waitForElementAndClick(SAVE_TO_READING_LIST_BUTTON, LONG_WAITING_TIMEOUT_IN_SECONDS);
    }

    private void findAddToReadingListButtonPoint() {
        int offsetFromSaveForLaterButton = 40;

        WebElement saveForLaterButton = this.waitForElementPresent(SAVE_TO_READING_LIST_BUTTON);
        addToReadingListButtonPoint = saveForLaterButton.getLocation();
        addToReadingListButtonPoint.y -= offsetFromSaveForLaterButton;
    }

    public void clickBackButton() {
        this.waitForElementAndClick(BACK_BUTTON);
    }

    public void clickGotItButtonAndroid() {
        this.waitForElementAndClick(GOT_IT_BUTTON, LONG_WAITING_TIMEOUT_IN_SECONDS);
    }

    public void clickAddToReadingListButton() throws Exception {
        Thread.sleep(1000);
        this.clickOnPoint(addToReadingListButtonPoint);
    }

    public void checkImageElementPresent(String imageFileName) throws Exception {
        if (Platform.getInstance().isWebMobile()) {
            final String imageElementLocator = getImageElementLocator(imageFileName);
            this.waitForElementPresent(imageElementLocator);
        } else {
            this.waitForImageElementPresent(imageFileName);
        }
    }

    public void clickRemoveFromReadingListButton() {
        if (this.isElementPresent(REMOVE_FROM_READING_LIST_BUTTON, LONG_WAITING_TIMEOUT_IN_SECONDS)) {
            this.waitForElementAndClick(REMOVE_FROM_READING_LIST_BUTTON);
        }
    }

    /* TEMPLATE METHODS */
    private String getImageElementLocator(String imageFileName) {
        return this.replaceSubstringInTemplate(IMAGE_ELEMENT, imageFileName);
    }
    /* TEMPLATE METHODS */
}
