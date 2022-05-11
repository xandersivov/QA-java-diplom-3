package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage {

    //Урл страницы регистрации
    public static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    //Поля: имя, почта, пароль
    @FindBy(how = How.XPATH, using = ".//input[@class='text input__textfield text_type_main-default']")
    public ElementsCollection nameEmailPasswordFields;
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
    public void setName(String name) {
        nameEmailPasswordFields.get(0).setValue(name);
    }

    //Ввод почты
    public void setEmail(String email) {
        nameEmailPasswordFields.get(1).setValue(email);
    }

    //Ввод пароля
    public void setPassword(String password) {
        nameEmailPasswordFields.get(2).setValue(password);
    }

    //Нажатие на кнопку регистрации
    public void clickRegistrationButton() {
        registerButton.click();
    }

    //Регистрация
    public void registration(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegistrationButton();
    }

    //Проверка надписи некорретного пароля
    public boolean checkIncorrectPasswordSign() {
        return incorrectPasswordSign.isDisplayed();
    }

    public void waitAfterRegistration() {
        registerButton.shouldBe(Condition.hidden);
    }

    //Нажать на кнопку "Войти"
    public void clickTheEntryButton() {
        enterButton.scrollTo().click();
    }

}
