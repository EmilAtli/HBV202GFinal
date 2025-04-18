package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.LibrarySystem;
import is.hi.hbv202g.Final.User;
import is.hi.hbv202g.Final.Book;
import is.hi.hbv202g.Final.UserOrBookDoesNotExistException;

import java.util.Scanner;

public class BorrowBookCommand implements Command {
  private final LibrarySystem library;
  private final Scanner scanner;

  public BorrowBookCommand(LibrarySystem library, Scanner scanner) {
    this.library = library;
    this.scanner = scanner;
  }

  @Override
  public String name() {
    return "Borrow Book";
  }

  @Override
  public void execute() {
    try {
      System.out.print("  User name: ");
      User u = library.findUserByName(scanner.nextLine().trim());

      System.out.print("  Book title: ");
      Book b = library.findBookByTitle(scanner.nextLine().trim());

      library.borrowBook(u, b);
      System.out.println("Book borrowed.\n");
    } catch (UserOrBookDoesNotExistException e) {
      System.err.println("! Error: " + e.getMessage() + "\n");
    }
  }
}