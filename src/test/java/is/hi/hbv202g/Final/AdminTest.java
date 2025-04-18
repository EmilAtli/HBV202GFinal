package is.hi.hbv202g.Final;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminTest {
  private Admin admin;

  /**
   * Set up a test fixture for each test method.
   * 
   */
  @Before
  public void setUp() {
    admin = new Admin("Bob");
  }

  /**
   * Verifies that the Admin object returns the correct name via the getName()
   * method.
   */
  @Test
  public void testGetName() {
    assertEquals("Bob", admin.getName());
  }

  /**
   * Verifies that the Admin object's name can be set via the setName() method
   * and retrieved via the getName() method.
   */
  @Test
  public void testSetName() {
    admin.setName("Alice");
    assertEquals("Alice", admin.getName());
  }

  /**
   * Verifies that the Admin class is a subclass of User.
   */

  @Test
  public void testIsUserSubclass() {
    assertTrue("Admin should be a subclass of User", admin instanceof User);
  }
}
