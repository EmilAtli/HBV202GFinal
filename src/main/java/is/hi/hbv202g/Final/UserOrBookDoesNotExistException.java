package is.hi.hbv202g.Final;

/**
 * Thrown when a requested user or book cannot be found in the system.
 */
public class UserOrBookDoesNotExistException extends Exception {

  /**
   * Constructs a new exception with the specified detail message.
   *
   * @param message the detail message explaining which user or book was not found
   */
  public UserOrBookDoesNotExistException(String message) {
    super(message);
  }
}
