package is.hi.hbv202g.Final;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class LibrarySystemTest {
  private LibrarySystem lib;
  private Student student;
  private FacultyMember faculty;
  private Book book1;
  private Book book2;
  private Book omnibus;

  /**
   * Set up a LibrarySystem with 3 users and 3 books: one single-author book,
   * one multi-author book, and an omnibus containing both books. The users are
   * one student, one faculty member, and one admin.
   * 
   * @throws Exception if LibrarySystem initialization fails
   */
  @Before
  public void setUp() throws Exception {
    lib = new LibrarySystem();

    // users
    lib.addStudentUser("Alice", true);
    lib.addFacultyMemberUser("Prof", "Dept");
    lib.addAdminUser("Admin");
    student = (Student) lib.findUserByName("Alice");
    faculty = (FacultyMember) lib.findUserByName("Prof");
    admin = (Admin) lib.findUserByName("Admin");

    // books
    lib.addBookWithTitleAndNameOfSingleAuthor("Book One", "Auth1");
    lib.addBookWithTitleAndAuthorList(
        "Multi Book",
        List.of(new Author("AuthA"), new Author("AuthB")),
        2);
    book1 = lib.findBookByTitle("Book One");
    book2 = lib.findBookByTitle("Multi Book");

    // omnibus
    lib.addOmnibus("Omni", List.of(book1, book2));
    omnibus = lib.findBookByTitle("Omni");
  }

  /**
   * Tests that searching for a non-existing book by title
   * throws a UserOrBookDoesNotExistException with an appropriate message.
   */

  @Test
  public void testFindBookByTitleNotFound() {
    try {
      lib.findBookByTitle("No Such Book");
      fail("Expected UserOrBookDoesNotExistException for missing book");
    } catch (UserOrBookDoesNotExistException e) {
      assertTrue(e.getMessage().contains("does not exist"));
    }
  }

  /**
   * Tests that searching for a non-existing user by name
   * throws a UserOrBookDoesNotExistException with an appropriate message.
   */
  @Test
  public void testFindUserByNameNotFound() {
    try {
      lib.findUserByName("Nobody");
      fail("Expected UserOrBookDoesNotExistException for missing user");
    } catch (UserOrBookDoesNotExistException e) {
      assertTrue(e.getMessage().contains("does not exist"));
    }
  }

  /**
   * Tests that borrowing a book from the library and then returning it
   * leaves the library in the same state as initially.
   * This test verifies that the number of lendings changes correctly, and
   * that the number of available copies of the book changes correctly.
   *
   * The test does not verify that the correct user and book are associated
   * with the lending; it only verifies that the lending is added and removed
   * from the library's list of lendings.
   */
  @Test
  public void testBorrowAndReturnBook() throws Exception {
    // initial state
    assertEquals(0, lib.getLendings().size());
    assertEquals(1, book1.getAvailableCopies());

    // borrow
    lib.borrowBook(student, book1);
    assertEquals(1, lib.getLendings().size());
    assertEquals(0, book1.getAvailableCopies());

    // return
    lib.returnBook(student, book1);
    assertEquals(0, lib.getLendings().size());
    assertEquals(1, book1.getAvailableCopies());
  }

  /**
   * Tests that borrowing a book with a user that is not registered
   * throws a UserOrBookDoesNotExistException with an appropriate message.
   */
  @Test
  public void testBorrowNonRegisteredUserThrows() throws Exception {
    User fake = new Student("Fake", false);
    try {
      lib.borrowBook(fake, book1);
      fail("Expected UserOrBookDoesNotExistException for unregistered user");
    } catch (UserOrBookDoesNotExistException e) {
      assertTrue(e.getMessage().contains("not registered"));
    }
  }

  /**
   * Tests that borrowing a non-existent book
   * throws a UserOrBookDoesNotExistException with an appropriate message.
   */
  @Test
  public void testBorrowNonexistentBookThrows() throws Exception {
    Book fakeBook = new Book("Ghost", "G", 1);
    try {
      lib.borrowBook(student, fakeBook);
      fail("Expected UserOrBookDoesNotExistException for missing book");
    } catch (UserOrBookDoesNotExistException e) {
      assertTrue(e.getMessage().contains("not in the catalog"));
    }
  }

  /**
   * Tests that the extendLending method extends the due date of a Lending for a
   * faculty member. Verifies that the due date is indeed changed.
   */
  @Test
  public void testExtendLending() throws Exception {
    // only faculty can extend via method signature
    lib.borrowBook(faculty, book2);
    Lending l = lib.getLendings().stream()
        .filter(x -> x.getBook().equals(book2))
        .findFirst().get();
    LocalDate originalDue = l.getDueDate();

    LocalDate newDue = originalDue.plusDays(10);
    lib.extendLending(faculty, book2, newDue);

    assertEquals(newDue, l.getDueDate());
  }

  /**
   * Tests that calling extendLending with a book/faculty combination
   * that doesn't correspond to any existing lending throws a
   * UserOrBookDoesNotExistException with an appropriate message.
   */
  @Test
  public void testExtendLendingNotFoundThrows() throws Exception {
    try {
      lib.extendLending(faculty, book1, LocalDate.now());
      fail("Expected UserOrBookDoesNotExistException for no lending");
    } catch (UserOrBookDoesNotExistException e) {
      assertTrue(e.getMessage().contains("No lending"));
    }
  }

  /**
   * Tests that adding an omnibus to the library and then borrowing it
   * propagates the borrow operation to its volumes.
   * Verifies that the number of lendings increases by 3 (1 for the omnibus
   * and 2 for its volumes), and that the available copies of the
   * omnibus and its volumes are correctly updated.
   */
  @Test
  public void testAddOmnibusAndBorrowPropagation() throws Exception {
    // omnibus and two volumes present
    assertTrue(omnibus instanceof Omnibus);
    assertEquals(3, lib.getBooks().size());

    // borrow omnibus
    lib.borrowBook(student, omnibus);

    // expect 1 omnibus lending + 2 volume lendings
    assertEquals(3, lib.getLendings().size());

    // availability: omnibus 0, book1 0, book2 1 (was 2 copies)
    assertEquals(0, omnibus.getAvailableCopies());
    assertEquals(0, book1.getAvailableCopies());
    assertEquals(1, book2.getAvailableCopies());
  }
}
