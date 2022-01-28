package tests;

import utils.Platform;
import ui.*;
import ui.factories.*;

public class ReadingListTests extends CoreTestCase {
    private WelcomePageObject welcomePageObject;
    private LoginPageObject loginPageObject;
    private MainPageObject mainPageObject;
    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;
    private ReadingListPageObject readingListPageObject;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        welcomePageObject = WelcomePageObjectFactory.get(this.driver);
        loginPageObject = LoginPageObjectFactory.get(this.driver);
        mainPageObject = MainPageObjectFactory.get(this.driver);
        searchPageObject = SearchPageObjectFactory.get(this.driver);
        articlePageObject = ArticlePageObjectFactory.get(this.driver);
        readingListPageObject = ReadingListPageObjectFactory.get(this.driver);
    }

    public void testAddAndDeleteArticlesInReadingList() throws Exception {
        final String
                userName = "DSGD1GB5Jq2j",
                password = "JG?%`}3Jzpc*",
                searchText = "star wars",
                readingListName = "Star Wars Reading List",
                firstArticleTitle = "Star Wars",
                secondArticleTitle = "Star Wars (film)",
                firstArticleNativeImageFileName = "Star_Wars.png",
                firstArticleWebImageFileName = "Star wars2.svg";

        // Закрываем приветственный экран
        if (Platform.getInstance().isIOS()) {
            welcomePageObject.clickSkipButton();
        }

        // Логинимся
        if (Platform.getInstance().isWebMobile()) {
            mainPageObject.clickMainMenuButton();
            mainPageObject.clickLoginButton();
            loginPageObject.sendKeysUserName(userName);
            loginPageObject.sendKeysPassword(password);
            loginPageObject.clickLogInButton();
        }

        // Выполняем поиск и открываем первую статью
        mainPageObject.clickSearchField();
        searchPageObject.sendKeysToSearchInputField(searchText);
        searchPageObject.clickArticleTitleInSearchResults(firstArticleTitle);

        // Добавляем открытую статью в новый лист чтения
        if (Platform.getInstance().isWebMobile()) {
            // Если в web-версии приложения статья уже добавлена в список чтения, удаляем ее из списка
            articlePageObject.clickRemoveFromReadingListButton();
        }
        articlePageObject.clickSaveToReadingListButton();
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.clickGotItButtonAndroid();
        } else {
            articlePageObject.clickAddToReadingListButton();
            if (Platform.getInstance().isIOS()) {
                readingListPageObject.clickCreateNewButton();
            }
        }
        if (!Platform.getInstance().isWebMobile()) {
            readingListPageObject.sendKeysToReadingListNameInputField(readingListName);
            readingListPageObject.clickConfirmCreationButton();
        }

        // Закрываем открытую статью, повторяем поиск и открываем вторую статью
        if (!Platform.getInstance().isWebMobile()) {
            articlePageObject.clickBackButton();
        }
        if (!Platform.getInstance().isIOS()) {
            // В Android возврат происходит к главному экрану, в iOS - к результатам предыдущешго поиска
            mainPageObject.clickSearchField();
            searchPageObject.sendKeysToSearchInputField(searchText);
        }
        searchPageObject.clickArticleTitleInSearchResults(secondArticleTitle);

        // Добавляем вторую статью в уже созданный лист чтения
        if (Platform.getInstance().isWebMobile()) {
            // Если в web-версии приложения статья уже добавлена в список чтения, удаляем ее из списка
            articlePageObject.clickRemoveFromReadingListButton();
        }
        articlePageObject.clickSaveToReadingListButton();
        if (Platform.getInstance().isIOS()) {
            articlePageObject.clickAddToReadingListButton();
        }
        if (!Platform.getInstance().isWebMobile()) {
            readingListPageObject.clickExistingReadingList(readingListName);
        }

        if (!Platform.getInstance().isWebMobile()) {
            articlePageObject.clickBackButton();
            if (Platform.getInstance().isIOS()) {
                searchPageObject.clickCancelSearchButton();
            }
        }

        // Открываем лист чтения
        if (Platform.getInstance().isWebMobile()) {
            mainPageObject.clickMainMenuButton();
        }
        mainPageObject.clickReadingListsButton();
        if (Platform.getInstance().isIOS()) {
            readingListPageObject.clickSyncScreenCloseButton();
            readingListPageObject.clickReadingListsSectionButton();
        }
        if (!Platform.getInstance().isWebMobile()) {
            readingListPageObject.clickExistingReadingList(readingListName);
        }

        // Удаляем вторую статью из листа чтения
        if (Platform.getInstance().isWebMobile()) {
            readingListPageObject.clickWatchedButton(secondArticleTitle);
        } else {
            readingListPageObject.swipeArticleLeftToDelete(secondArticleTitle);
            if (Platform.getInstance().isIOS()) {
                readingListPageObject.clickSwipeDeleteActionButton();
            }
        }

        // Проверяем, что в нем осталась только первая статья
        assertTrue(readingListPageObject.isArticlePresentInCurrentReadingList(firstArticleTitle));
        if (!Platform.getInstance().isWebMobile()) {
            assertFalse(readingListPageObject.isArticlePresentInCurrentReadingList(secondArticleTitle));
        }

        // Открываем первую статью и проверяем графический элемент с логотипом Star Wars на соответствие заданному изображению
        readingListPageObject.clickArticleInReadingList(firstArticleTitle);
        if (Platform.getInstance().isWebMobile()) {
            articlePageObject.checkImageElementPresent(firstArticleWebImageFileName);
        } else {
            articlePageObject.checkImageElementPresent(firstArticleNativeImageFileName);
        }
    }
}
