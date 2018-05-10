package pages;

import models.Book;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

// Page Object of the Search Page
public class SearchPage {

    private WebDriver driver;
    // Tell Selenium to go into the DOM and search for the element with the id=searchBar
    private By searchBar = By.id("searchBar");
    // Tell Selenium to go into the DOM and search for the button element by title
    private By clearButton = By.xpath("//a[contains(@title, Clear text");
    // Tell Selenium to go into the DOM and search for all elements which contain the class
    private By visibleBooks = By.xpath("//li[not(contains(@class, 'ui-screen-hidden'))]");
    private By hiddenBooks = By.xpath("//li[contains(@class, 'ui-screen-hidden')]");
    // ..// look from the book element down
    private By titleAttribute = By.xpath(".//h2[contains(@id, '_title')]");
    private By authorAttribute = By.xpath(".//p[contains(@id, '_author')]");
    private By priceAttribute = By.xpath(".//p[contains(@id, '_price')]");
    private By imageAttribute = By.xpath(".//img[contains(@id, '_thumb')]");

    // constructor will be executed when an instance of this class is created
    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }


    public void search(String searchString) {
        search(searchString, true);

    }

    public void search(String searchString, boolean waitForHidden) {
        //clickClearButton();
        clearSearch();

        driver.findElement(searchBar).sendKeys(searchString);

        if (waitForHidden) {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.presenceOfElementLocated(hiddenBooks));
        }
    }

    public int getNumberOfVisibleBook() {
        return findVisibleBooks().size();
    }

    public boolean isBookVisible(String title) {
        List<WebElement> books = findVisibleBooks();
        for (WebElement book : books) {
            if (title.equalsIgnoreCase(book.findElement(titleAttribute).getText())) {
                return true;
            }
        }
        return false;
    }

    // overload the already existing method
    public boolean isBookVisible(Book book) {
        List<WebElement> books = findVisibleBooks();
        for (WebElement b : books) {
            if (book.getTitle().equalsIgnoreCase(b.findElement(titleAttribute).getText())
                    && book.getAuthor().equalsIgnoreCase(b.findElement(authorAttribute).getText())
                    && book.getPrice().equalsIgnoreCase(b.findElement(priceAttribute).getText())
                    && b.findElement(imageAttribute).getAttribute("src").endsWith(book.getImage()))
                // getAttribute returns the absolute path
                System.out.print(b.findElement(imageAttribute).getAttribute("src"));
            {
                return true;
            }
        }
        return false;
    }

    public boolean isAuthorVisible(String title) {
        List<WebElement> books = findVisibleBooks();
        for (WebElement book : books) {
            if (title.equalsIgnoreCase(book.findElement(titleAttribute).getText())) {
                return true;
            }
        }
        return false;
    }

    public void clearSearch() {
        driver.findElement(searchBar).clear();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.numberOfElementsToBe(hiddenBooks, 0));
    }

    public void clickClearButton() {

        driver.findElement(clearButton).click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.numberOfElementsToBe(hiddenBooks, 0));
    }

    private List<WebElement> findVisibleBooks() {
        return driver.findElements(visibleBooks);
    }
}



