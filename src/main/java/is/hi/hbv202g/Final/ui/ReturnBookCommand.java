package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.LibrarySystem;
import is.hi.hbv202g.Final.User;
import is.hi.hbv202g.Final.Book;
import is.hi.hbv202g.Final.UserOrBookDoesNotExistException;

import java.util.Scanner;

public class ReturnBookCommand implements Command {
  private final LibrarySystem library;
  private final Scanner scanner;

  public ReturnBookCommand(LibrarySystem library, Scanner scanner) {
    this.library = library;
    this.scanner = scanner;
  }

  @Override
  public String name() {
    return "Return Book";
  }

  @Override
  public void execute() {
    try {
      System.out.print("  User name: ");
      User u = library.findUserByName(scanner.nextLine().trim());

      System.out.print("  Book title: ");
      Book b = library.findBookByTitle(scanner.nextLine().trim());

      library.returnBook(u, b);
      System.out.println("Book returned.\n");
    } catch (UserOrBookDoesNotExistException e) {
      System.err.println("! Error: " + e.getMessage() + "\n");
    }
  }
}
