package is.hi.hbv202g.Final;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmptyAuthorListExceptionTest {
  /**
   * Tests that the exception message is set correctly when an
   * EmptyAuthorListException is created with a message.
   */
  @Test
  public void testExceptionMessage() {
    String msg = "No authors!";
    EmptyAuthorListException ex = new EmptyAuthorListException(msg);
    assertEquals(msg, ex.getMessage());
  }
}
