package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.LibrarySystem;
import is.hi.hbv202g.Final.Admin;
import java.util.Scanner;

/**
 * Command for adding a book to the library.
 */
public class AddBookCommand implements Command {
  private final LibrarySystem library;
  private final Scanner scanner;
  private final Session session;

  /**
   * Creates a new AddBookCommand.
   *
   * @param library the library system to add books into; must not be null
   * @param scanner the Scanner to read user input; must not be null
   * @param session the current user session, used to check for admin privileges;
   *                must not be null
   */
  public AddBookCommand(LibrarySystem library, Scanner scanner, Session session) {
    this.library = library;
    this.scanner = scanner;
    this.session = session;
  }

  /**
   * @return the command name
   */
  @Override
  public String name() {
    return "Add Book (single author) (admin only)";
  }

  /**
   * Prompts the user for a title, author and number of copies to add a
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

    System.out.print("  Author (single): ");
    String author = scanner.nextLine().trim();

    System.out.print("  Number of copies: ");
    int copies = Integer.parseInt(scanner.nextLine().trim());

    library.addBookWithTitleAndNameOfSingleAuthor(title, author, copies);
    System.out.println("Book added.\n");
  }
}
