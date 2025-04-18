package is.hi.hbv202g.Final;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
  private User user;

  /**
   * Initializes the test fixture for each test method.
   * Creates an instance of a User using an anonymous subclass
   * with the initial name "InitialName" since User is abstract.
   */

  @Before
  public void setUp() {
    // use an anonymous subclass since User is abstract
    user = new User("InitialName") {
    };
  }

  /**
   * Verifies that the getName() method returns the correct name of a User
   * object.
   */
  @Test
  public void testGetName() {
    assertEquals("InitialName", user.getName());
  }

  /**
   * Verifies that the setName() method sets the correct name
   * of a User object and that the getName() method retrieves
   * the correct name.
   */
  @Test
  public void testSetName() {
    user.setName("UpdatedName");
    assertEquals("UpdatedName", user.getName());
  }

  /**
   * Verifies that the User object can have an empty string as its name, and
   * that the getName() method returns the empty string.
   */
  @Test
  public void testNameCanBeEmptyString() {
    user.setName("");
    assertEquals("", user.getName());
  }
}
