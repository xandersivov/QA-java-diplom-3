import PageObject.LoginPage;
import PageObject.OrderPage;
import PageObject.RegistrationPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static PageObject.LoginPage.FORGOT_PASSWORD_URL;
import static PageObject.OrderPage.MAIN_PAGE_URL;
import static PageObject.RegistrationPage.REGISTER_PAGE_URL;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LoginTest {

    private final String browser;

    public LoginTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "{0}-browser")
    public static Object[][] browserForTest() {
        return new Object[][]{
                {"Chrome"},
                {"Edge"}
        };
    }

    @Before
    public void setUp() {
        closeWebDriver();
        Configuration.browser = browser;
    }

    @After
    public void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    //Корректный вход с главной страницы через кнопку "Войти в аккаунт"
    @Test
    public void checkLoginMainPageEntryButtonTest() {
        OrderPage main = open(MAIN_PAGE_URL, OrderPage.class);
        main.clickAccountEntryButton();
        LoginPage login = page(LoginPage.class);
        login.loginUser(User.EMAIL, User.PASSWORD);
        assertEquals(url(), MAIN_PAGE_URL);
    }

    //Корректный вход с главной страницы через кнопку "Личный кабинет"
    @Test
    public void checkLoginMainPagePersonalAccountButtonTest() {
        OrderPage main = open(MAIN_PAGE_URL, OrderPage.class);
        main.clickPersonalAccountButton();
        LoginPage login = page(LoginPage.class);
        login.loginUser(User.EMAIL, User.PASSWORD);
        assertEquals(url(), MAIN_PAGE_URL);
    }

    //Корректный вход со страницы регистрации через кнопку "Войти"
    @Test
    public void checkLoginRegistrationPagePersonalAccountButtonTest() {
        RegistrationPage register = open(REGISTER_PAGE_URL, RegistrationPage.class);
        register.clickTheEntryButton();
        LoginPage login = page(LoginPage.class);
        login.loginUser(User.EMAIL, User.PASSWORD);
        assertEquals(url(), MAIN_PAGE_URL);
    }

    //Переход со страницы восстановления пароля через кнопку "Войти"
    @Test
    public void checkLoginForgotPasswordPageEnterButtonTest() {
        LoginPage forgotPasswordPage = open(FORGOT_PASSWORD_URL, LoginPage.class);
        forgotPasswordPage.clickTheEnterButtonInForgot();
        LoginPage login = page(LoginPage.class);
        login.loginUser(User.EMAIL, User.PASSWORD);
        assertEquals(url(), MAIN_PAGE_URL);
    }

}
