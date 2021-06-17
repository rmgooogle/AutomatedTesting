package mail.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProfilePage {

    public WebDriver driver;

    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    /**
     * определение локатора меню пользователя
     */
    @FindBy(xpath = "//*[contains(@class, 'user-account__name')]")
    private WebElement userMenu;
    /**
     * определение локатора кнопки выхода из аккаунта
     */
    @FindBy(xpath = "//*[contains(@class, 'menu__item menu__item_type_link legouser__menu-item legouser__menu-item_action_exit')]")
    private WebElement logoutBtn;
    /**
     * определение локатора почты
     */
    @FindBy(css = "a[href='https://mail.yandex.ru']")
    private WebElement goToMailboxPage;

    /**
     * метод для нажатия кнопки меню пользователя
     */
    @Step("Переход в подменю на главной странице")
    public void entryMenu() {
        userMenu.click();
    }

    @Step("Переход в почту")
    public void goToMailboxPageClick() {
        goToMailboxPage.click();
    }

}
