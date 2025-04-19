package is.hi.hbv202g.Final.ui;

/**
 * A command in the CLI menu.
 */
public interface Command {

  /**
   * Returns the display name for this command.
   *
   * @return the name shown in the menu
   */
  String name();

  /**
   * Executes the action for this command.
   */
  void execute();
}
