import pageObject.AccountPage;
import pageObject.LoginPage;
import pageObject.OrderPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static pageObject.AccountPage.ACCOUNT_PAGE_URL;
import static pageObject.LoginPage.LOGIN_PAGE_URL;
import static pageObject.OrderPage.MAIN_PAGE_URL;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AccountActionTest {

    private final String browser;

    public AccountActionTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "{0}-browser")
    public static Object[][] browserForTest() {
        return new Object[][]{
                {"Chrome"},
                //{"Edge"}
        };
    }

    //Логинимся перед тестом
    @Before
    public void setUp() {
        closeWebDriver();
        Configuration.browser = browser;
        OrderPage main = open(MAIN_PAGE_URL, OrderPage.class);
        main.clickAccountEntryButton();
        LoginPage login = page(LoginPage.class);
        login.loginUser(User.EMAIL, User.PASSWORD);
    }

    @After
    public void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    //Переход в личный кабинет с главной страницы
    @Test
    public void checkTransitionToAccountPageTest() {
        OrderPage main = page(OrderPage.class);
        main.clickPersonalAccountButton();
        AccountPage accountPage = page(AccountPage.class);
        accountPage.waitAfterTransition();
        assertEquals(url(), ACCOUNT_PAGE_URL);
    }

    //Переход из личного кабинета в конструктор по нажатию на кнопку "Конструктор"
    @Test
    public void checkTransitionAfterClickConstructButtonTest() {
        OrderPage main = page(OrderPage.class);
        main.clickPersonalAccountButton();
        AccountPage accountPage = page(AccountPage.class);
        accountPage.waitAfterTransition();
        accountPage.clickTheConstructorButton();
        assertEquals(url(), MAIN_PAGE_URL);
    }

    //Переход из личного кабинета в конструктор по нажатию на лого бургера
    @Test
    public void checkTransitionAfterClickLogoTest() {
        OrderPage main = page(OrderPage.class);
        main.clickPersonalAccountButton();
        AccountPage accountPage = page(AccountPage.class);
        accountPage.waitAfterTransition();
        accountPage.clickTheLogo();
        assertEquals(url(), MAIN_PAGE_URL);
    }

    //Переход из личного кабинета на страницу логина после выхода
    @Test
    public void checkTransitionAfterLogOutTest() {
        OrderPage main = page(OrderPage.class);
        main.clickPersonalAccountButton();
        AccountPage accountPage = page(AccountPage.class);
        accountPage.waitAfterTransition();
        accountPage.clickTheLogOutButton();
        accountPage.waitAfterLogOut();
        assertEquals(url(), LOGIN_PAGE_URL);
    }
}
