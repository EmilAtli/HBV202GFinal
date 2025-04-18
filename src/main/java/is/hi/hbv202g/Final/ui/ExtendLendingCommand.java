package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.Book;
import is.hi.hbv202g.Final.FacultyMember;
import is.hi.hbv202g.Final.LibrarySystem;
import is.hi.hbv202g.Final.User;
import is.hi.hbv202g.Final.UserOrBookDoesNotExistException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ExtendLendingCommand implements Command {
  private final LibrarySystem library;
  private final Scanner scanner;
  private final Session session;

  public ExtendLendingCommand(LibrarySystem library, Scanner scanner, Session session) {
    this.library = library;
    this.scanner = scanner;
    this.session = session;
  }

  @Override
  public String name() {
    return "Extend Lending (faculty only)";
  }

  @Override
  public void execute() {
    User u = session.getCurrentUser();
    if (!(u instanceof FacultyMember)) {
      System.err.println("! Only faculty can extend lendings.\n");
      return;
    }
    FacultyMember fm = (FacultyMember) u;

    try {
      System.out.print("  Book title: ");
      Book b = library.findBookByTitle(scanner.nextLine().trim());

      System.out.print("  New due date (YYYY-MM-DD): ");
      String dateStr = scanner.nextLine().trim();
      LocalDate newDue;
      try {
        newDue = LocalDate.parse(dateStr);
      } catch (DateTimeParseException ex) {
        System.err.println("! Invalid date format.\n");
        return;
      }

      library.extendLending(fm, b, newDue);
      System.out.printf("Lending extended to %s%n%n", newDue);

    } catch (UserOrBookDoesNotExistException e) {
      System.err.println("! Error: " + e.getMessage() + "\n");
    }
  }
}
