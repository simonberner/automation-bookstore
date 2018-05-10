package base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SearchPage;

public class BaseTests {

    protected static WebDriver driver;
    protected static SearchPage searchPage;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");

        //instantiate the driver
        driver = new ChromeDriver();
        //launch the browser
        driver.get("file:///Users/sibe/IdeaProjects/automation-bookstore/website/index.html");

        searchPage = new SearchPage(driver);

    }

    @AfterAll
    public static void tearDown() {
        //close the browser
        driver.quit();
    }

}
