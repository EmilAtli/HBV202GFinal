// src/main/java/is/hi/hbv202g/Final/util/LibraryInitializer.java
package is.hi.hbv202g.Final.util;

import is.hi.hbv202g.Final.Author;
import is.hi.hbv202g.Final.Book;
import is.hi.hbv202g.Final.EmptyAuthorListException;
import is.hi.hbv202g.Final.LibrarySystem;
import is.hi.hbv202g.Final.UserOrBookDoesNotExistException;

import java.util.List;

/**
 * Loads a default set of books, including a Lord of the Rings omnibus, and
 * users into a LibrarySystem.
 */
public class LibraryInitializer {

  /**
   * Initializes a LibrarySystem with some sample books and users. This
   * includes a few single-author books, a multi-author book, and an omnibus
   * containing three volumes of The Lord of the Rings.
   * 
   * @param lib the LibrarySystem to initialize
   */
  public static void seed(LibrarySystem lib) {
    // single‑author picks
    lib.addBookWithTitleAndNameOfSingleAuthor("Effective Java", "Joshua Bloch");
    lib.addBookWithTitleAndNameOfSingleAuthor("Clean Code", "Robert C. Martin");

    // multi‑author pick
    try {
      lib.addBookWithTitleAndAuthorList("Design Patterns",
          List.of(
              new Author("Erich Gamma"),
              new Author("Richard Helm"),
              new Author("Ralph Johnson"),
              new Author("John Vlissides")));
    } catch (EmptyAuthorListException ignored) {
    }

    // LOTR volumes
    lib.addBookWithTitleAndNameOfSingleAuthor("The Fellowship of the Ring", "J.R.R. Tolkien");
    lib.addBookWithTitleAndNameOfSingleAuthor("The Two Towers", "J.R.R. Tolkien");
    lib.addBookWithTitleAndNameOfSingleAuthor("The Return of the King", "J.R.R. Tolkien");

    // users
    lib.addStudentUser("John", true);
    lib.addFacultyMemberUser("Dr. Smith", "Computer Science");
    lib.addAdminUser("Librarian");

    // omnibus: The Lord of the Rings
    try {
      Book vol1 = lib.findBookByTitle("The Fellowship of the Ring");
      Book vol2 = lib.findBookByTitle("The Two Towers");
      Book vol3 = lib.findBookByTitle("The Return of the King");
      lib.addOmnibus("The Lord of the Rings", List.of(vol1, vol2, vol3));
    } catch (EmptyAuthorListException | UserOrBookDoesNotExistException ignored) {
    }
  }
}
