import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageObject.RegistrationPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pageObject.LoginPage.LOGIN_PAGE_URL;
import static pageObject.RegistrationPage.REGISTER_PAGE_URL;

@RunWith(Parameterized.class)
public class RegistrationTest {

    private final String browser;

    public RegistrationTest(String browser) {
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
    @DisplayName("Тест на проверку при успешной регистрации переход на страницу входа")
    public void checkCorrectRegistrationTest() {
        RegistrationPage registrationPage = open(REGISTER_PAGE_URL, RegistrationPage.class);
        User user = new User();
        registrationPage.registration(user.RANDOM_NAME, user.RANDOM_EMAIL, user.PASSWORD);
        registrationPage.waitAfterRegistration();
        assertEquals(url(), LOGIN_PAGE_URL);
    }

    @Test
    @DisplayName("Тест на Регистрацию с коротким паролем и отображение ошибки")
    public void checkRegistrationIncorrectPasswordTest() {
        RegistrationPage registrationPage = open(REGISTER_PAGE_URL, RegistrationPage.class);
        User user = new User();
        registrationPage.registration(user.RANDOM_NAME, user.RANDOM_EMAIL, "12345");
        assertTrue(registrationPage.checkIncorrectPasswordSign());
    }
}
