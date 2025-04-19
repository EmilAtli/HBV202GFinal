package is.hi.hbv202g.Final.ui;

/**
 * Command for logging out a user.
 */
public class LogoutCommand implements Command {
  private final Session session;

  /**
   * Creates a new LogoutCommand.
   * 
   * @param session The session.
   */
  public LogoutCommand(Session session) {
    this.session = session;
  }

  /**
   * @return the command name "Logout"
   */
  @Override
  public String name() {
    return "Logout";
  }

  /**
   * Logs out the user. If a user is logged in, the user is logged out and
   * the session is updated. If no user is logged in, an error message is
   * printed.
   */
  @Override
  public void execute() {
    if (session.getCurrentUser() != null) {
      System.out.println("Logged out: " + session.getCurrentUser().getName() + "\n");
      session.setCurrentUser(null);
    } else {
      System.out.println("No user is logged in.\n");
    }
  }
}
