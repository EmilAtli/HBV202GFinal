package is.hi.hbv202g.Final.ui;

public class ExitCommand implements Command {
  @Override
  public String name() {
    return "Exit";
  }

  @Override
  public void execute() {
    System.out.println("Goodbye!");
    System.exit(0);
  }
}