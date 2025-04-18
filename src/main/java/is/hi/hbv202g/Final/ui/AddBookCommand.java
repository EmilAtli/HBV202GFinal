package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.LibrarySystem;
import is.hi.hbv202g.Final.Admin;
import is.hi.hbv202g.Final.ui.Session;
import java.util.Scanner;

public class AddBookCommand implements Command {
  private final LibrarySystem library;
  private final Scanner scanner;
  private final Session session;

  public AddBookCommand(LibrarySystem library, Scanner scanner, Session session) {
    this.library = library;
    this.scanner = scanner;
    this.session = session;
  }

  @Override
  public String name() {
    return "Add Book";
  }

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

    library.addBookWithTitleAndNameOfSingleAuthor(title, author);
    System.out.println("Book added.\n");
  }
}
