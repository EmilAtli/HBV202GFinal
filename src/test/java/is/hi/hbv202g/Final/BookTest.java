package is.hi.hbv202g.Final;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class BookTest {
  private Book single;
  private Book multi;

  /**
   * Sets up the test fixture by creating two books, one with a single author and
   * one with multiple authors.
   * 
   * @throws EmptyAuthorListException should never happen due to the way the test
   *                                  fixture is set up.
   */
  @Before
  public void setUp() throws EmptyAuthorListException {
    single = new Book("One", "A");
    multi = new Book("Many", List.of(new Author("X"), new Author("Y")), 2);
  }

  /**
   * Verifies that the single-author constructor sets up the book properly.
   */
  @Test
  public void testSingleAuthorDefaults() throws EmptyAuthorListException {
    assertEquals("One", single.getTitle());
    assertEquals(1, single.getTotalCopies());
    assertEquals(1, single.getAvailableCopies());
    List<Author> authors = single.getAuthors();
    assertEquals(1, authors.size());
    assertEquals("A", authors.get(0).getName());
  }

  /**
   * Verifies that the multi-author constructor sets up the book properly.
   */
  @Test
  public void testMultiAuthorAndCopies() {
    assertEquals("Many", multi.getTitle());
    assertEquals(2, multi.getTotalCopies());
    assertEquals(2, multi.getAvailableCopies());
    List<String> names = multi.getAuthors().stream()
        .map(Author::getName).toList();
    assertTrue(names.containsAll(List.of("X", "Y")));
  }

  /**
   * Tests that creating a Book with an empty list of authors
   * throws an EmptyAuthorListException.
   */

  @Test(expected = EmptyAuthorListException.class)
  public void testConstructorEmptyAuthorsThrows() throws EmptyAuthorListException {
    new Book("Bad", Collections.emptyList(), 1);
  }

  /**
   * Tests that creating a Book with an invalid number of copies
   * (i.e. zero or negative) throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidCopiesThrows() throws EmptyAuthorListException {
    new Book("Bad", List.of(new Author("Z")), 0);
  }

  /**
   * Tests that borrowing and returning a Book works as expected.
   * Verifies that borrowing a book with multiple copies reduces the available
   * count, that borrowing when no copies are left throws
   * IllegalStateException, and that returning a book restores the available
   * count, up to the total number of copies.
   */
  @Test
  public void testBorrowAndReturnCopy() throws EmptyAuthorListException {
    Book b = new Book("C", "A", 2);
    b.borrowCopy();
    assertEquals(1, b.getAvailableCopies());
    b.borrowCopy();
    assertEquals(0, b.getAvailableCopies());
    // borrow beyond should throw
    try {
      b.borrowCopy();
      fail("Expected IllegalStateException when no copies left");
    } catch (IllegalStateException ignored) {
    }

    // return copy restores up to totalCopies
    b.returnCopy();
    assertEquals(1, b.getAvailableCopies());
    b.returnCopy();
    assertEquals(2, b.getAvailableCopies());
    // extra return does nothing
    b.returnCopy();
    assertEquals(2, b.getAvailableCopies());
  }

  /**
   * Tests setting and adding authors to a Book.
   * Verifies that the setAuthors method correctly replaces
   * the existing author list, and that addAuthor appends
   * a new author to the list.
   */
  @Test
  public void testSetAuthorsAndAddAuthor() throws EmptyAuthorListException {
    Book b = new Book("D", "A");
    b.setAuthors(List.of(new Author("M")));
    assertEquals(1, b.getAuthors().size());
    b.addAuthor(new Author("N"));
    assertEquals(2, b.getAuthors().size());
  }

  /**
   * Tests that setting an empty author list throws an EmptyAuthorListException
   */
  @Test(expected = EmptyAuthorListException.class)
  public void testSetAuthorsEmptyThrows() throws EmptyAuthorListException {
    Book b = new Book("E", "A");
    b.setAuthors(Collections.emptyList());
  }
}