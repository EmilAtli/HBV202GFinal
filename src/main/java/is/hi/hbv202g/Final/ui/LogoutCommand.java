package is.hi.hbv202g.Final.ui;

public class LogoutCommand implements Command {
  private final Session session;

  public LogoutCommand(Session session) {
    this.session = session;
  }

  @Override
  public String name() {
    return "Logout";
  }

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
