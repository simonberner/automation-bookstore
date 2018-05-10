package tests;


import base.BaseTests;
import models.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SearchTests extends BaseTests {

    @Test
    public void testSearchByFullTitle() {
        String title = "Agile Testing";
        searchPage.search(title);
        assertTrue(searchPage.isBookVisible(title), "Book not found: " + title);
        // if the assertion is false, log the message
        assertEquals(1, searchPage.getNumberOfVisibleBook(), "Number of visible books is incorrect");
    }

    @Test
    public void testSearchByPartialTitle() {
        String title1 = "Test Automation in the Real World";
        String title2 = "Experiences of Test Automation";

        searchPage.search("Automation");

        assertTrue(searchPage.isBookVisible(title1), "Book nof found: " + title1);

        assertTrue(searchPage.isBookVisible(title2), "Book nof found: " + title1);

        assertEquals(2, searchPage.getNumberOfVisibleBook(), "Number of visible books is incorrect");
    }

    @Test
    public void testSearchByAuthor() {
        String title = "Advanced Selenium in Java";

        searchPage.search("Paul Watson");

        assertTrue(searchPage.isBookVisible(title), "Book not found: " + title);
        assertEquals(1, searchPage.getNumberOfVisibleBook(), "Number of visible books is incorrect");
    }

    @Test
    public void testAllBooksReturned() {
        searchPage.search("a", false);
        assertEquals(8, searchPage.getNumberOfVisibleBook(), "Number of books returned is incorrect");
    }

    @Test
    public void testMultipleMatches() {
        searchPage.search("go");

        String author = "Lisa Crispin and Janet Gregory";
        String title = "How Google Tests Software";

        assertTrue(searchPage.isBookVisible(author), "Author nof found: " + author);
        assertTrue(searchPage.isBookVisible(title), "Book nof found: " + title);

        assertEquals(2, searchPage.getNumberOfVisibleBook(), "Number of books returned is incorrect");

    }

    @Test
    public void testAllAttributes() {
        Book book = new Book("How Google Tests Software", "James A. Whittaker, Jason Arbon, Jeff Carollo", "$31.67", "img/whittaker.jpg");
        searchPage.search(book.getTitle());
        assertTrue(searchPage.isBookVisible(book), "Book not found: " + book.getTitle());
    }
}
