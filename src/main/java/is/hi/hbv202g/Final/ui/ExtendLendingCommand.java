package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.LibrarySystem;
import is.hi.hbv202g.Final.UserOrBookDoesNotExistException;
import is.hi.hbv202g.Final.Book;
import is.hi.hbv202g.Final.User;
import is.hi.hbv202g.Final.FacultyMember;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ExtendLendingCommand implements Command {
  private final LibrarySystem library;
  private final Scanner scanner;

  public ExtendLendingCommand(LibrarySystem library, Scanner scanner) {
    this.library = library;
    this.scanner = scanner;
  }

  @Override
  public String name() {
    return "Extend Lending (faculty only)";
  }

  @Override
  public void execute() {
    try {
      System.out.print("  Faculty name: ");
      User u = library.findUserByName(scanner.nextLine().trim());
      if (!(u instanceof FacultyMember)) {
        System.err.println("! Only faculty can extend lendings.\n");
        return;
      }
      FacultyMember fm = (FacultyMember) u;

      System.out.print("  Book title: ");
      Book b = library.findBookByTitle(scanner.nextLine().trim());

      System.out.print("  New due date (YYYY‑MM‑DD): ");
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
