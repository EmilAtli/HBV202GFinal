package is.hi.hbv202g.Final;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class LendingTest {
  private Lending lending;
  private Book book;
  private User user;
  private LocalDate today;

  /**
   * Sets up the test fixture by creating a Book, a User and a Lending.
   * The Lending is created with the Book and the User.
   * The today variable is set to the current date.
   * 
   * @throws EmptyAuthorListException should never happen due to the way the test
   *                                  fixture is set up.
   */
  @Before
  public void setUp() throws EmptyAuthorListException {
    today = LocalDate.now();
    book = new Book("Test Book", "Author");
    user = new Student("Alice", true);
    lending = new Lending(book, user);
  }

  /**
   * Tests that the initial due date of a Lending is set to 30 days
   * from the current date upon creation.
   */

  @Test
  public void testInitialDueDateIs30DaysAhead() {
    assertEquals("Due date should be 30 days from now",
        today.plusDays(30),
        lending.getDueDate());
  }

  /**
   * Tests that the due date of a Lending can be changed by
   * calling setDueDate on the Lending.
   * Verifies that the due date is indeed changed to the
   * new date.
   */
  @Test
  public void testSetDueDate() {
    LocalDate newDue = today.plusDays(10);
    lending.setDueDate(newDue);
    assertEquals(newDue, lending.getDueDate());
  }

  /**
   * Tests that the getBook and getUser methods of a Lending instance
   * return the correct Book and User objects that were initially set
   * during the Lending's creation.
   */

  @Test
  public void testGetBookAndUser() {
    assertSame("Book should match the one passed in", book, lending.getBook());
    assertSame("User should match the one passed in", user, lending.getUser());
  }

  /**
   * Tests that the setBook and setUser methods of a Lending instance
   * replace the existing Book and User objects with new ones.
   * Verifies that the new objects are indeed set by calling getBook and
   * getUser afterwards.
   */
  @Test
  public void testSetBookAndUser() throws EmptyAuthorListException {
    Book newBook = new Book("Another Book", "Writer");
    User newUser = new FacultyMember("Prof X", "Dept");
    lending.setBook(newBook);
    lending.setUser(newUser);
    assertSame(newBook, lending.getBook());
    assertSame(newUser, lending.getUser());
  }
}