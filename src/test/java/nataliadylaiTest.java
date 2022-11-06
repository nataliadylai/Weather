import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.IAssert;

import javax.swing.plaf.TableHeaderUI;
import java.util.List;
import java.util.Set;

public class nataliadylaiTest {
    //   TC_1_1  - Тест кейс:
    //1. Открыть страницу https://openweathermap.org/
    //2. Набрать в строке поиска город Paris
    //3. Нажать пункт меню Search
    //4. Из выпадающего списка выбрать Paris, FR
    //5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2TagText_WhenSearchCityCountry() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/Nata/Desktop/chromedriver");
        WebDriver driver = new ChromeDriver();


        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";



        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField =
                driver.findElement(
                        By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));

        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton  = driver.findElement(By.xpath("//button[@type = 'submit']"));
        searchButton.click();
        Thread.sleep(2000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

//    TC_11_01
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню Guide
//3.  Подтвердить, что вы перешли на страницу
// со ссылкой https://openweathermap.org/guide и что
// title этой страницы OpenWeatherMap API guide - OpenWeatherMap

    @Test
    public void test_OpenWeatherMapAPIGuide () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Nata/Desktop/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";


        driver.get(url);
        Thread.sleep(5000);
         WebElement guideHeader = driver.findElement(
                 By.xpath("//div/ul/li/a[@href = '/guide']"));
         guideHeader.click();
         Thread.sleep(2000);

         String actualResult1 = driver.getCurrentUrl();

         Assert.assertEquals(actualResult1,expectedResult1);

         String actualResult2 = driver.getTitle();

         Assert.assertEquals(actualResult2,expectedResult2);


        driver.quit();
    }


//    TC_11_02
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//
//3.  Подтвердить, что температура для города показана в Фарингейтах


    @Test
    public void testChangingTempUnitInHeading_WhenSwitchTempUnitButton() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/Nata/Desktop/chromedriver");
        WebDriver driver = new ChromeDriver();


        String url = "https://openweathermap.org/";
//        boolean expectedResult = true;

        driver.get(url);
        Thread.sleep(5000);

        WebElement tempUnit = driver.findElement(
                By.xpath("//div[text()='Imperial: °F, mph']"));

        tempUnit.click();
        Thread.sleep(1000);

        WebElement tempUnitHeading = driver.findElement(
                By.xpath("//div[@class='current-temp']/span"));

        boolean actualResult = tempUnitHeading.getText().contains("°F");
         Thread.sleep(2000);

        Assert.assertTrue(actualResult);

        driver.quit();
    }


//    TC_11_03
//1.  Открыть базовую ссылку
//2. Подтвердить, что внизу страницы есть панель с текстом
//“We use cookies which are essential for
//    the site to work. We also use non-essential cookies
//    to help us improve our services. Any data collected is anonymised.
//    You can allow all cookies or manage them individually.”
//    3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
//

    @Test
    public void test_ConfirmCookiesOnTheFooter() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Nata/Desktop/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String button1 = "Allow";
        String button2 = "Manage cookies";


        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);
        Assert.assertTrue(driver.findElement(By.className("stick-footer-panel__container")).isDisplayed());
//        WebElement cookies = driver.findElement(
//                By.xpath("//div/p[@class = 'stick-footer-panel__description']")
//        );

        Assert.assertEquals(driver.findElements(
                By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*")).size(), 2);
          WebElement cookies = driver.findElement(
                By.className("stick-footer-panel__description"));

        String actualResult = cookies.getText();

        Assert.assertEquals(actualResult,expectedResult);
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(
                By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*[text() = 'Allow all']")).isDisplayed());

        Assert.assertTrue(driver.findElement(
                By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*[text() = ' Manage cookies ']")).isDisplayed());

        driver.quit();
    }


//    TC_11_04
//1.  Открыть базовую ссылку
//2.  Подтвердить, что в меню Support есть
// 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”


    @Test
    public void test_checkSupportDropdownOnPAgeHeader() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/Nata/Desktop/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement support = driver.findElement(
                By.xpath("//div[@id = 'support-dropdown']"));
        Thread.sleep(2000);
        support.click();

        Assert.assertEquals(driver.findElements(By.xpath("//ul[@id = 'support-dropdown-menu']/li")).size(), 3);

        WebElement faq = driver.findElement(
                By.xpath("//ul[@id = 'support-dropdown-menu']//a[@href ='/faq']")
        );

        Thread.sleep(2000);
        String actualResult1 = faq.getText();
        Assert.assertEquals(actualResult1,expectedResult1);

        WebElement start = driver.findElement(
                By.xpath("//ul[@id ='support-dropdown-menu']//a[@href ='/appid']")
        );

        Thread.sleep(2000);
        String actualResult2 = start.getText();
        Assert.assertEquals(actualResult2,expectedResult2);

        WebElement question = driver.findElement(
                By.xpath("//ul[@id ='support-dropdown-menu']//a[@href = 'https://home.openweathermap.org/questions']")
        );
        String actualResult3 = question.getText();
        Assert.assertEquals(actualResult3,expectedResult3);

        driver.quit();
 }
//    TC_11_05
//1. Открыть базовую ссылку
//2. Нажать пункт меню Support → Ask a question
//3. Заполнить поля Email, Subject, Message
//4. Не подтвердив CAPTCHA, нажать кнопку Submit
//5. Подтвердить, что пользователю будет показана ошибка
// “reCAPTCHA verification failed, please try again.”

    @Test

    public void testBoxAskQuestion() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Nata/Desktop/chromedriver");
        WebDriver driver = new ChromeDriver();

        String expectedResult = "reCAPTCHA verification failed, please try again.";
        driver.get("https://openweathermap.org/");
        driver.manage().window().maximize();
        Thread.sleep(5000);



        WebElement headerButtonSuppot = driver.findElement(By.id("support-dropdown"));
        headerButtonSuppot.click();
        Thread.sleep(2000);



//      String parentHandle = driver.getWindowHandle();   // Save parent window
        String parent_window = driver.getWindowHandle();

        WebElement supportMenuAskQuestion = driver.findElement
                (By.xpath("//ul[@id='support-dropdown-menu']//a[@target='_blank']"));
        supportMenuAskQuestion.click();


        WebDriverWait wait = new WebDriverWait(driver, 10);

        Set<String> windows = driver.getWindowHandles();
        for (String child_window : windows) {
            if (!parent_window.equals(child_window)) {
                driver.switchTo().window(child_window);
            }
        }


        WebElement fieldEmail = driver.findElement
                (By.xpath("//input[@id='question_form_email']"));

        fieldEmail.click();
        fieldEmail.sendKeys("4147744@gmail.com");

        WebElement fieldSubject = driver.findElement(By.id("question_form_subject"));
        fieldSubject.click();
        fieldSubject.sendKeys("Other");
        Thread.sleep(2000);
        WebElement message = driver.findElement(By.id("question_form_message"));
        message.click();
        message.sendKeys("Hello world!");
        Thread.sleep(2000);

        WebElement buttonSubmit = driver.findElement(By.xpath("//input[@class='btn btn-default']"));
        buttonSubmit.click();

        WebElement capchamessage = driver.findElement(By.xpath("//div[@class='help-block']"));

        Assert.assertEquals(capchamessage.getText(),expectedResult);

        driver.switchTo().window(parent_window);
        driver.quit();

    }


//    TC_11_06
//1.  Открыть базовую ссылку
//2.  Нажать пункт меню Support → Ask a question
//3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
//4. Оставить пустым поле Email
//5. Заполнить поля  Subject, Message
//6. Подтвердить CAPTCHA
//7. Нажать кнопку Submit
//8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”




    @Test
    public void BlankEmailErrorMessageSupportSupportSection() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Nata/Desktop/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "can't be blank";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);


        WebElement headerButtonSuppot = driver.findElement(By.id("support-dropdown"));
        headerButtonSuppot.click();
        Thread.sleep(2000);

        String parent_window = driver.getWindowHandle();

        WebElement supportMenuAskQuestion = driver.findElement
                (By.xpath("//ul[@id='support-dropdown-menu']//a[@target='_blank']"));
        supportMenuAskQuestion.click();


        WebDriverWait wait = new WebDriverWait(driver, 10);

        Set<String> windows = driver.getWindowHandles();
        for (String child_window : windows) {
            if (!parent_window.equals(child_window)) {
                driver.switchTo().window(child_window);
            }
        }

        WebElement checkbox = driver.findElement(
                By.xpath("//input[@id = 'question_form_is_user_false']")
        );


        boolean actualResult2 = checkbox.isSelected();
        Assert.assertTrue(actualResult2);
        Thread.sleep(2000);


        WebElement fieldEmail = driver.findElement
                (By.xpath("//input[@id='question_form_email']"));
        fieldEmail.click();
        fieldEmail.clear();

        WebElement fieldSubject = driver.findElement(By.id("question_form_subject"));
        fieldSubject.click();
        fieldSubject.sendKeys("Other");
        Thread.sleep(2000);

        WebElement message = driver.findElement(By.id("question_form_message"));
        message.click();
        message.sendKeys("Hello world!");
        Thread.sleep(2000);

        Thread.sleep(10000);

        WebElement submitButton =driver.findElement(
                By.xpath("//input[@data-disable-with = 'Create Question form']")
        );
        submitButton.click();

        WebElement errorMessage = driver.findElement(
                By.xpath( "//span[@class = 'help-block']")
        );

         boolean actualResult = errorMessage.getText().contains("can't be blank");
         
         Assert.assertTrue(actualResult);
         driver.quit();

    }



//    TC_11_07
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//
//3.  Нажать на единицы измерения Metric: °C, m/s
//4.  Подтвердить, что в результате этих действий,
// единицы измерения температуры изменились с F на С

//
    @Test
    public void test_SwitchingMeasurementsBetweenFandC() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/Nata/Desktop/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        boolean expectedResult = true;

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement imperial = driver.findElement(
                By.xpath("//div[@class= 'option'][2]")
        );

        imperial.click();
        Thread.sleep(5000);

        WebElement metric = driver.findElement(
                By.xpath("//div[@class= 'option'][1]")
        );

        metric.click();
        Thread.sleep(5000);

       boolean actualResult = metric.getText().contains("°C");

       Assert.assertEquals(actualResult,expectedResult);

       driver.quit();

    }

//    TC_11_08
//1.  Открыть базовую ссылку
//2.  Нажать на лого компании
//3.  Дождаться, когда произойдет перезагрузка сайта,
// и подтвердить, что текущая ссылка не изменилась

@Test
public void test_CorrectPageUpdatedAfterClickOnLogo() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "/Users/Nata/Desktop/chromedriver");
    WebDriver driver = new ChromeDriver();

    String url = "https://openweathermap.org/";
    String expectedResult = "https://openweathermap.org/";

    driver.get(url);
    driver.manage().window().maximize();
    Thread.sleep(5000);

     WebElement logo = driver.findElement(
             By.xpath("//a[@href]//img[@src = '/themes/openweathermap/assets/img/logo_white_cropped.png']"));
      logo.click();
      Thread.sleep(6000);
      String actualResult = driver.getCurrentUrl();

      Assert.assertEquals(actualResult,expectedResult);
      Thread.sleep(2000);

      driver.quit();

}


//    TC_11_09
//1.  Открыть базовую ссылку
//2.  В строке поиска в навигационной панели набрать “Rome”
//3.  Нажать клавишу Enter
//4.  Подтвердить, что вы перешли на страницу в
// ссылке которой содержатся слова “find” и “Rome”
//5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”


@Test
public void test_FindCityNameInNavigationSectionTopPage() throws InterruptedException {

    System.setProperty("webdriver.chrome.driver", "/Users/Nata/Desktop/chromedriver");
    WebDriver driver = new ChromeDriver();

    String url = "https://openweathermap.org/";
    String cityName = "Rome";
    String actionName = "find";
//    boolean expectedResult1 = true;
//    boolean expectedResult2 = true;
      String expectedResult3 = "Rome";


    driver.get(url);
    driver.manage().window().maximize();
    Thread.sleep(5000);

    WebElement navigationSection = driver.findElement(
            By.xpath("//div//form[@role]//input[@placeholder ='Weather in your city']")
    );
    navigationSection.click();
    Thread.sleep(2000);
    navigationSection.sendKeys("Rome");
    Thread.sleep(2000);
    navigationSection.sendKeys(Keys.ENTER);


    boolean actualResult1 = driver.getCurrentUrl().contains(cityName);
    Thread.sleep(3000);
//    Assert.assertEquals(actualResult1,expectedResult1);
    Assert.assertTrue(actualResult1);

    boolean actualResult2 = driver.getCurrentUrl().contains(actionName);
    Thread.sleep(3000);
//    Assert.assertEquals(actualResult2,expectedResult2);
    Assert.assertTrue(actualResult2);
    Thread.sleep(3000);

    WebElement cityNameResult = driver.findElement(
            By.xpath("//input[@id ='search_str']")
    );


    String actualResult3 = cityNameResult.getAttribute("value");
    Assert.assertEquals(actualResult3,expectedResult3);
    Thread.sleep(2000);

    driver.quit();

}


//    TC_11_10
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню API
//3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок


@Test
public void test_30ApiOrangeButtons() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "/Users/Nata/Desktop/chromedriver");
    WebDriver driver = new ChromeDriver();

    String url = "https://openweathermap.org/";
    int expectedResult = 30;

    driver.get(url);
    driver.manage().window().maximize();
    Thread.sleep(5000);

    WebElement menuApi = driver.findElement(By.xpath("//div//li//a[@href='/api']"));
    menuApi.click();
    Thread.sleep(2000);

//    List<WebElement> elements = driver.findElements(By.xpath("//a[@class ='btn_block orange round']"));
//
//    List<WebElement> oneButton = driver.findElements(By.xpath("//a[@class='ow-btn round btn-orange']"));
//    int count = 0;
//    for (int i = 0; i < elements.size(); i++){
//        for (int j = 0; j < oneButton.size(); j++)
//
//            count = j +1 + i +1 ;
//    }


    List<WebElement> buttons = driver.findElements(
            By.xpath("//a[@type= 'button' and contains(@class,'orange') or contains(@class, 'btn-orange')]")
    );
    int actualResult = buttons.size();


//    System.out.println(buttons);
//
//    Assert.assertEquals(count,30);
    Assert.assertEquals(actualResult, expectedResult);

    driver.quit();
}

}
