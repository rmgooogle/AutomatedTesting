package mail.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MailBoxPage {

    public WebDriver driver;
    /**
     * Написать новое письмо
     */
    @FindBy(css = "a[class='mail-ComposeButton js-main-action-compose']")
    private WebElement newMailSend;
    /**
     * ввести адрес
     */
    @FindBy(css = "div[class='composeYabbles']")
    private WebElement addressRecipient;
    /**
     * ввести тему
     */
    @FindBy(css = "input[class='composeTextField ComposeSubject-TextField']")
    private WebElement mailSubject;
    /**
     * ввести текст
     */
    @FindBy(css = "div[placeholder='Напишите что-нибудь']>div")
    private WebElement mailField;
    /**
     * кнопка отправки письма
     */
    @FindBy(css = "button[class='control button2 button2_view_default button2_tone_default button2_size_l button2_theme_action button2_pin_circle-circle ComposeControlPanelButton-Button ComposeControlPanelButton-Button_action']")
    private WebElement sendButton;

    /**
     * поле "Поиск"
     */
    @FindBy(css = "input[placeholder='Поиск']")
    private WebElement searchInMailbox;

    /**
     * Кнопка "Найти" в поиске писем
     */
    @FindBy(css = "button[class='control button2 button2_view_default button2_tone_mail-suggest-themed button2_size_n button2_theme_normal button2_pin_clear-round button2_type_submit search-input__form-button']")
    private WebElement submitSearchInMailbox;

    /**
     * Кнопка "Папки" после поиска
     */

    @FindBy(xpath = "//*[@id=\"nb-1\"]/body/div[2]/div[7]/div/div[3]/div[3]/div[1]/div/div/button[3]")
    private WebElement folders;

    /**
     * Кнопка "Входящие" в меню "Папки"
     */
    @FindBy(css = "[@id=\"nb-1\"]/body/div[9]/div/div/div[1]/span" )
    private WebElement inputFolder;

    /**
     * Количество найденных писем
     */
    @FindBy(css = "span[class='mail-MessagesSearchInfo-Title_misc nb-with-xs-left-gap']")
    private WebElement messageCountBySearch;

    public MailBoxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("Создание нового письма")
    public mail.page.MailBoxPage newMail() {
        newMailSend.click();
        return this;
    }

    @Step("Ввод адреса {0} кому отправляется письмо")
    public mail.page.MailBoxPage inputAddressRecipient(String address) {
        addressRecipient.sendKeys(address);
        return this;
    }

    @Step("Ввод темы {0} письма")
    public mail.page.MailBoxPage inputMailSubject(String subject) {
        mailSubject.sendKeys(subject);
        return this;
    }

    @Step("Ввод текста {0} сообщения")
    public mail.page.MailBoxPage inputMailText(String mailText) {
        mailField.sendKeys(mailText);
        return this;
    }

    @Step("Кнопка отправки письма")
    public mail.page.MailBoxPage sendMailButton() {
        sendButton.click();
        return this;
    }

    @Step("Ввод строки {0} для поиска")
    public mail.page.MailBoxPage inputInMailBox(String line) {
        searchInMailbox.sendKeys(line);
        return this;
    }

    @Step("Кнопка поиска")
    public mail.page.MailBoxPage submitSearchMailBox() {
        submitSearchInMailbox.click();
        return this;
    }

    @Step("Кнопка папки")
    public mail.page.MailBoxPage submitMailBox() {
        folders.click();
        return this;
    }
//<div data-lego="react" aria-selected="false" aria-disabled="false" role="option" class="control menu__item menu__item_type_option"><span data-lego="react" class="menu__text">Входящие</span></div>
    @Step("Кнопка входящие папки")
    public mail.page.MailBoxPage submitInputMailBox() {
             driver.findElement(By.xpath(".//div[@class='menu menu_size_s menu_theme_normal menu_view_classic menu_type_radio']")).findElement(By.xpath(".//*[text()='Входящие']")).click();
        return this;
    }

    @Step("Число писем")
    public String getCountFoundedMails() {
        return messageCountBySearch.getText();
    }

}



