package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.Book;
import is.hi.hbv202g.Final.LibrarySystem;
import is.hi.hbv202g.Final.Lending;
import is.hi.hbv202g.Final.User;
import is.hi.hbv202g.Final.UserOrBookDoesNotExistException;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReturnBookCommand implements Command {
  private final LibrarySystem library;
  private final Scanner scanner;
  private final Session session;

  public ReturnBookCommand(LibrarySystem library, Scanner scanner, Session session) {
    this.library = library;
    this.scanner = scanner;
    this.session = session;
  }

  @Override
  public String name() {
    return "Return Book";
  }

  @Override
  public void execute() {
    User user = session.getCurrentUser();
    if (user == null) {
      System.err.println("! You must log in first.\n");
      return;
    }

    // List current lendings for this user
    List<Lending> myLoans = library.getLendings().stream()
        .filter(l -> l.getUser().equals(user))
        .collect(Collectors.toList());

    if (myLoans.isEmpty()) {
      System.out.println("You have no books to return.\n");
      return;
    }

    System.out.println("Your current loans:");
    for (int i = 0; i < myLoans.size(); i++) {
      Book b = myLoans.get(i).getBook();
      System.out.printf("  %d) %s%n", i + 1, b.getTitle());
    }
    System.out.print("Select number to return: ");
    String choice = scanner.nextLine().trim();

    try {
      int idx = Integer.parseInt(choice) - 1;
      if (idx < 0 || idx >= myLoans.size()) {
        System.err.println("! Invalid selection.\n");
        return;
      }
      Book toReturn = myLoans.get(idx).getBook();
      library.returnBook(user, toReturn);
      System.out.printf("%s returned \"%s\".%n%n", user.getName(), toReturn.getTitle());

    } catch (NumberFormatException ex) {
      System.err.println("! Please enter a number.\n");
    } catch (UserOrBookDoesNotExistException e) {
      // shouldn’t happen, but just in case
      System.err.println("! Error: " + e.getMessage() + "\n");
    }
  }
}
