package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.LibrarySystem;
import is.hi.hbv202g.Final.EmptyAuthorListException;
import is.hi.hbv202g.Final.Admin;
import is.hi.hbv202g.Final.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Command for adding a book to the library.
 */
public class AddBookMultiAuthorsCommand implements Command {
  private final LibrarySystem library;
  private final Scanner scanner;
  private final Session session;

  /**
   * Creates a new AddBookMultiAuthorsCommand.
   *
   * @param library the library system to add books into; must not be null
   * @param scanner the Scanner to read user input; must not be null
   * @param session the current user session, used to check for admin privileges;
   *                must not be null
   */
  public AddBookMultiAuthorsCommand(LibrarySystem library, Scanner scanner, Session session) {
    this.library = library;
    this.scanner = scanner;
    this.session = session;
  }

  /**
   * @return the command name
   */
  @Override
  public String name() {
    return "Add Book (multiple authors) (admin only)";
  }

  /**
   * Prompts the user for a title, author list, and number of copies to add a
   * book to the library. Only admins can add books.
   */
  @Override
  public void execute() {
    if (!(session.getCurrentUser() instanceof Admin)) {
      System.err.println("! Only admins can add books.\n");
      return;
    }
    System.out.print("  Title: ");
    String title = scanner.nextLine().trim();

    System.out.print("  Authors (comma-separated (,)): ");
    String line = scanner.nextLine().trim();
    List<Author> authors = new ArrayList<>();
    if (!line.isEmpty()) {
      authors = List.of(line.split(","))
          .stream()
          .map(String::trim)
          .filter(s -> !s.isEmpty())
          .map(Author::new)
          .collect(Collectors.toList());
    }
    System.out.print("  Number of copies: ");
    int copies = Integer.parseInt(scanner.nextLine().trim());

    try {
      library.addBookWithTitleAndAuthorList(title, authors, copies);
      System.out.println("Book added with authors: " +
          authors.stream().map(Author::getName).collect(Collectors.joining(", "))
          + "\n");
    } catch (EmptyAuthorListException e) {
      System.err.println("! Error: " + e.getMessage() + "\n");
    }
  }
}
