package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.Book;
import is.hi.hbv202g.Final.Omnibus;
import is.hi.hbv202g.Final.LibrarySystem;
import is.hi.hbv202g.Final.Author;

import java.util.stream.Collectors;

public class ListBooksCommand implements Command {
  private final LibrarySystem library;

  public ListBooksCommand(LibrarySystem library) {
    this.library = library;
  }

  /**
   * @return the command name
   */
  @Override
  public String name() {
    return "List Books";
  }

  /**
   * Prints a list of all books in the library to the console.
   * The format is "Title -- Author(s) -- Available: <num> / <total>".
   * If the book is an Omnibus, it prints "Title (Set) -- Contains: [list of
   * titles] -- Available: <num> / <total>".
   */
  @Override
  public void execute() {
    var books = library.getBooks();
    if (books.isEmpty()) {
      System.out.println("No books in the system.\n");
      return;
    }
    System.out.println("Books:");
    for (Book b : books) {
      String authors = b.getAuthors().stream()
          .map(Author::getName)
          .collect(Collectors.joining(", "));
      // Check for composite sets
      if (b instanceof Omnibus) {
        Omnibus set = (Omnibus) b;
        String volumes = set.getVolumes().stream()
            .map(Book::getTitle)
            .collect(Collectors.joining(", "));
        System.out.printf(
            "* Title: %s (Set) -- Contains: [%s] -- Available: %d/%d%n",
            set.getTitle(),
            volumes,
            set.getAvailableCopies(),
            set.getTotalCopies());
      } else {
        System.out.printf(
            "* Title: %s -- Author(s): %s -- Available: %d/%d%n",
            b.getTitle(),
            authors,
            b.getAvailableCopies(),
            b.getTotalCopies());
      }
    }
    System.out.println();
  }
}
