package is.hi.hbv202g.Final.ui;

/**
 * Command for exiting the program.
 */
public class ExitCommand implements Command {
  /**
   * @return the command name "Exit"
   */
  @Override
  public String name() {
    return "Exit";
  }

  /**
   * Exits the program.
   */
  @Override
  public void execute() {
    System.out.println("Goodbye!");
    System.exit(0);
  }
}