package is.hi.hbv202g.Final;

/**
 * Thrown to indicate that an operation requiring one or more authors
 * was passed an empty or null list of authors.
 */
public class EmptyAuthorListException extends Exception {

  /**
   * Constructs a new EmptyAuthorListException with the specified detail message.
   *
   * @param message the detail message explaining the cause
   */
  public EmptyAuthorListException(String message) {
    super(message);
  }
}
