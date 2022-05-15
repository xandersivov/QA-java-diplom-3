package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage {

    //Урл страницы регистрации
    public static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    //Поля: имя
    @FindBy(how = How.XPATH, using = ".//label[text() = 'Имя']/../input")
    private SelenideElement nameField;
    //Поля: почта
    @FindBy(how = How.XPATH, using = ".//label[text() = 'Email']/../input")
    private SelenideElement emailField;
    //Поля: пароль
    @FindBy(how = How.XPATH, using = ".//label[text() = 'Пароль']/../input")
    private SelenideElement passwordField;
    //Кнопка "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;
    //Кнопка "Войти"
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement enterButton;
    //Надпись "Некорректный пароль"
    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    private SelenideElement incorrectPasswordSign;

    //Ввод имени
    @Step("Set name")
    public void setName(String name) {
        nameField.setValue(name);
    }

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

    //Нажатие на кнопку регистрации
    @Step("Click registration button")
    public void clickRegistrationButton() {
        registerButton.click();
    }

    //Регистрация
    @Step("Registration")
    public void registration(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegistrationButton();
    }

    //Проверка надписи некорретного пароля
    @Step("Check incorrect password sign")
    public boolean checkIncorrectPasswordSign() {
        return incorrectPasswordSign.isDisplayed();
    }

    //Ждем пока кнопка регистрации исчезнет после успешной регистрации
    @Step("Wait after registration")
    public void waitAfterRegistration() {
        registerButton.shouldBe(Condition.hidden);
    }

    //Нажать на кнопку "Войти"
    @Step("Click Enter button")
    public void clickTheEntryButton() {
        enterButton.scrollTo().click();
    }
}
