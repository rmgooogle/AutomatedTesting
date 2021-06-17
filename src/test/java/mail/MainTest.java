package mail;

import io.qameta.allure.Description;
import mail.page.LoginPage;
import mail.page.MailBoxPage;
import mail.page.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MainTest {

    private static WebDriver driver;

    @BeforeClass
    public void setup() throws MalformedURLException {

        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions()
                .addArguments("start-maximized");
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        driver = new RemoteWebDriver(
                new URL(ConfProperties.getProperty("url")),
                capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Description("Тестируем попытку входа на почту Яндекса.")
    @Test
    public void mainTest() {
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        MailBoxPage mailBoxPage = new MailBoxPage(driver);

        String searchLine = "Simbirsoft Тестовое задание";

        //Переход на страницу авторизации
        driver.get(ConfProperties.getProperty("loginpage"));
        //Ввод логина
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        //Нажатие на кнопку входа
        loginPage.clickLoginBtn();
        //Ввод пароля
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        //Нажатие на кнопку входа
        loginPage.clickLoginBtn();
        //Переход в почту
        profilePage.entryMenu();
        profilePage.goToMailboxPageClick();

        //Получение количества писем
        String countOfLetters = mailBoxPage
                .inputInMailBox(searchLine)
                .submitSearchMailBox()
                .getCountFoundedMails();

        //Создание нового письма
        mailBoxPage.newMail();

        //отправляем письмо по адресу = adress,
        // темой = "SimbirSoft Тестовое задание. Кретов",
        // количеством строк = countOfLetters
        mailBoxPage.inputAddressRecipient(ConfProperties.getProperty("adress"))
                .inputMailSubject("SimbirSoft Тестовое задание. Кретов")
                .inputMailText(countOfLetters)
                .sendMailButton();
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}
