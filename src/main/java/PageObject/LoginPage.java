package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
    //Урл страницы восстановления пароля
    public static final String FORGOT_PASSWORD_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    //Поле ввода почты
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    public SelenideElement emailField;
    //Поле ввода пароля
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    public SelenideElement passwordField;
    //Кнопка "Войти"
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement enterButton;

    //Кнопка "Войти" в Воостановлении
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement enterButtonInForgot;

    //Ввод почты
    @Step("Set email")
    public void setEmail(String email) {
        emailField.setValue(email);
    }

    //Ввод пароля
    @Step("Set password")
    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    //Нажать кнопку "Войти"
    @Step("Click Enter button")
    public void clickEntryButton() {
        enterButton.click();
    }

    //Нажать кнопку "Войти" в Восстановлении
    @Step("click Enter in forgot")
    public void clickTheEnterButtonInForgot() {
        enterButtonInForgot.click();
    }

    //Вход
    @Step("Enter")
    public void loginUser(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickEntryButton();
        enterButton.shouldBe(Condition.hidden);
    }
}
