// src/main/java/is/hi/hbv202g/Final/util/LibraryInitializer.java
package is.hi.hbv202g.Final.util;

import is.hi.hbv202g.Final.LibrarySystem;
import is.hi.hbv202g.Final.Author;
import is.hi.hbv202g.Final.EmptyAuthorListException;

import java.util.List;

/**
 * Loads a default set of books and users into a LibrarySystem.
 */
public class LibraryInitializer {

  /** Add a few sample books & users */
  public static void seed(LibrarySystem lib) {
    // single-author
    lib.addBookWithTitleAndNameOfSingleAuthor("Effective Java", "Joshua Bloch");
    lib.addBookWithTitleAndNameOfSingleAuthor("Clean Code", "Robert C. Martin");

    // multi-author
    try {
      lib.addBookWithTitleAndAuthorList("Design Patterns",
          List.of(new Author("Erich Gamma"),
              new Author("Richard Helm"),
              new Author("Ralph Johnson"),
              new Author("John Vlissides")));
    } catch (EmptyAuthorListException e) {
    }

    // users
    lib.addStudentUser("John", true);
    lib.addFacultyMemberUser("Dr. Smith", "Computer Science");
    lib.addAdminUser("Librarian");
  }
}
