import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageObject.OrderPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;
import static pageObject.OrderPage.MAIN_PAGE_URL;

@RunWith(Parameterized.class)
public class OrderTest {

    private final String browser;

    public OrderTest(String browser) {
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
    @DisplayName("Тест на проверку, что после перехода в раздел Булки можно добавить элемент из этого раздела в корзину")
    public void checkTransitionBunsButtonTest() {
        OrderPage main = open(MAIN_PAGE_URL, OrderPage.class);
        assertTrue(main.clickBunsButtonCheckTheSign());
    }

    @Test
    @DisplayName("Тест на проверку, что после перехода в раздел Соусы можно добавить элемент из этого раздела в корзину")
    public void checkTransitionSaucesButtonTest() {
        OrderPage main = open(MAIN_PAGE_URL, OrderPage.class);
        assertTrue(main.clickSaucesButtonAndCheckTheSign());
    }

    @Test
    @DisplayName("Тест на проверку, что после перехода в раздел Начинки можно добавить элемент из этого раздела в корзину")
    public void checkTransitionFillingButtonTest() {
        OrderPage main = open(MAIN_PAGE_URL, OrderPage.class);
        assertTrue(main.clickFillingButtonAndCheckTheSign());
    }

}
