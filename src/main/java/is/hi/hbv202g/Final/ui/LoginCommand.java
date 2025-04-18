package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.LibrarySystem;
import is.hi.hbv202g.Final.User;
import is.hi.hbv202g.Final.UserOrBookDoesNotExistException;

import java.util.Scanner;

public class LoginCommand implements Command {
  private final LibrarySystem library;
  private final Session session;
  private final Scanner scanner;

  public LoginCommand(LibrarySystem library, Session session, Scanner scanner) {
    this.library = library;
    this.session = session;
    this.scanner = scanner;
  }

  @Override
  public String name() {
    return session.getCurrentUser() == null ? "Login" : "Switch User";
  }

  @Override
  public void execute() {
    System.out.print("Username: ");
    String name = scanner.nextLine().trim();
    try {
      User user = library.findUserByName(name);
      session.setCurrentUser(user);
      System.out.println("Logged in as: " + user.getName() + "\n");
    } catch (UserOrBookDoesNotExistException e) {
      System.err.println("! User not found.\n");
    }
  }
}
