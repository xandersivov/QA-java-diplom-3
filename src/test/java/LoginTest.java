import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageObject.LoginPage;
import pageObject.OrderPage;
import pageObject.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
import static pageObject.LoginPage.FORGOT_PASSWORD_URL;
import static pageObject.OrderPage.MAIN_PAGE_URL;
import static pageObject.RegistrationPage.REGISTER_PAGE_URL;

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
                //{"Edge"}
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

    @Test
    @DisplayName("Тест на корректный вход с главной страницы через кнопку Войти в аккаунт")
    public void checkLoginMainPageEntryButtonTest() {
        OrderPage main = open(MAIN_PAGE_URL, OrderPage.class);
        main.clickAccountEntryButton();
        LoginPage login = page(LoginPage.class);
        login.loginUser(User.EMAIL, User.PASSWORD);
        assertEquals(url(), MAIN_PAGE_URL);
    }

    @Test
    @DisplayName("Тест на Корректный вход с главной страницы через кнопку Личный кабинет")
    public void checkLoginMainPagePersonalAccountButtonTest() {
        OrderPage main = open(MAIN_PAGE_URL, OrderPage.class);
        main.clickPersonalAccountButton();
        LoginPage login = page(LoginPage.class);
        login.loginUser(User.EMAIL, User.PASSWORD);
        assertEquals(url(), MAIN_PAGE_URL);
    }

    @Test
    @DisplayName("Тест на Корректный вход со страницы регистрации через кнопку Войти")
    public void checkLoginRegistrationPagePersonalAccountButtonTest() {
        RegistrationPage register = open(REGISTER_PAGE_URL, RegistrationPage.class);
        register.clickTheEntryButton();
        LoginPage login = page(LoginPage.class);
        login.loginUser(User.EMAIL, User.PASSWORD);
        assertEquals(url(), MAIN_PAGE_URL);
    }

    @Test
    @DisplayName("Тест на Переход со страницы восстановления пароля через кнопку Войти")
    public void checkLoginForgotPasswordPageEnterButtonTest() {
        LoginPage forgotPasswordPage = open(FORGOT_PASSWORD_URL, LoginPage.class);
        forgotPasswordPage.clickTheEnterButtonInForgot();
        LoginPage login = page(LoginPage.class);
        login.loginUser(User.EMAIL, User.PASSWORD);
        assertEquals(url(), MAIN_PAGE_URL);
    }

}
