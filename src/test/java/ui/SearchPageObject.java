package ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class SearchPageObject extends CorePageObject {
    protected static String
            SEARCH_INPUT_FIELD,
            CANCEL_SEARCH_BUTTON,
            SEARCH_RESULT_TITLE,
            RESULTS_TITLES;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void sendKeysToSearchInputField(String searchText) {
        this.waitForElementAndSendKeys(SEARCH_INPUT_FIELD, searchText);
    }

    public void clickCancelSearchButton() {
        this.waitForElementAndClick(CANCEL_SEARCH_BUTTON);
    }

    public void clickArticleTitleInSearchResults(String title) {
        final String searchResultXpath = getArticleTitleInSearchResultsLocator(title);
        waitForElementAndClick(searchResultXpath);
    }

    /* TEMPLATES METHODS */
    private String getArticleTitleInSearchResultsLocator(String title) {
        return this.replaceSubstringInTemplate(SEARCH_RESULT_TITLE, title);
    }
    /* TEMPLATES METHODS */
}
