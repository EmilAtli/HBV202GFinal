package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.Book;
import is.hi.hbv202g.Final.LibrarySystem;
import is.hi.hbv202g.Final.Lending;
import is.hi.hbv202g.Final.User;
import is.hi.hbv202g.Final.UserOrBookDoesNotExistException;

import java.util.Scanner;

public class BorrowBookCommand implements Command {
  private final LibrarySystem library;
  private final Scanner scanner;
  private final Session session;

  public BorrowBookCommand(LibrarySystem library, Scanner scanner, Session session) {
    this.library = library;
    this.scanner = scanner;
    this.session = session;
  }

  @Override
  public String name() {
    return "Borrow Book";
  }

  @Override
  public void execute() {
    User user = session.getCurrentUser();
    if (user == null) {
      System.err.println("! You must log in first.\n");
      return;
    }

    try {
      System.out.print("  Book title: ");
      String title = scanner.nextLine().trim();
      Book book = library.findBookByTitle(title);

      library.borrowBook(user, book);
      System.out.printf("%s borrowed \"%s\".%n%n", user.getName(), book.getTitle());

    } catch (UserOrBookDoesNotExistException e) {
      System.err.println("! Error: " + e.getMessage() + "\n");
    }
  }
}
