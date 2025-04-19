package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.LibrarySystem;
import is.hi.hbv202g.Final.User;
import is.hi.hbv202g.Final.UserOrBookDoesNotExistException;

import java.util.Scanner;

/**
 * Command for logging in a user.
 */
public class LoginCommand implements Command {
  private final LibrarySystem library;
  private final Session session;
  private final Scanner scanner;

  /**
   * Creates a new LoginCommand.
   * 
   * @param library The library system.
   * @param session The session.
   * @param scanner The scanner.
   */
  public LoginCommand(LibrarySystem library, Session session, Scanner scanner) {
    this.library = library;
    this.session = session;
    this.scanner = scanner;
  }

  /**
   * Returns the command name based on the current user's session.
   * 
   * @return "Login" if no user is logged in, otherwise "Switch User".
   */
  @Override
  public String name() {
    return session.getCurrentUser() == null ? "Login" : "Switch User";
  }

  /**
   * Prompts the user for a username to log in. If the username matches a user
   * in the library, the user is logged in and the session is updated.
   * Otherwise, an error message is printed.
   */
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
