package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountPage {

    //Урл личного кабинета
    public static final String ACCOUNT_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    //Кнопка "История заказов" - как уникальный локатор для верефикации личного кабинета
    @FindBy(how = How.XPATH, using = ".//a[text()='История заказов']")
    private SelenideElement ordersHistoryButton;
    //Кнопка "Выход"
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement logOutButton;
    //Кнопка "Конструктор"
    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    private SelenideElement constructorButton;
    //Логотип бургер
    @FindBy(how = How.XPATH, using = ".//*[@id='root']/div/header/nav/div/a")
    private SelenideElement logoButton;

    //Нажать на кнопку "Выход"
    public void clickTheLogOutButton() {
        logOutButton.click();
    }

    //Нажать на кнопку "Конструктор"
    public void clickTheConstructorButton() {
        constructorButton.click();
    }

    //Нажать на логотип бургер
    public void clickTheLogo() {
        logoButton.click();
    }

    //Ждем загрузку страницы
    public void waitAfterTransition() {
        logOutButton.shouldBe(Condition.visible);
    }

    //Ожидание после выхода
    public void waitAfterLogOut() {
        logOutButton.shouldBe(Condition.disappear);
    }
}
