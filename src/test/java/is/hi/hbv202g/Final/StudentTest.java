package is.hi.hbv202g.Final;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {
  private Student student;

  /**
   * Set up a Student instance with initial values for testing.
   * Initializes the student with the name "Charlie" and fee paid.
   */
  @Before
  public void setUp() {
    student = new Student("Charlie", true);
  }

  /**
   * Verifies that the Student object returns the correct name
   * inherited from the User class via the getName() method.
   */
  @Test
  public void testGetName_inheritedFromUser() {
    assertEquals("Charlie", student.getName());
  }

  /**
   * Verifies that the initial value of the feePaid field is true
   * when a Student object is instantiated with the fee paid.
   */
  @Test
  public void testIsFeePaid_initialValue() {
    assertTrue("Fee should be paid initially", student.isFeePaid());
  }

  /**
   * Verifies that the feePaid field can be changed via the setFeePaid
   * method and retrieved correctly via the isFeePaid() method.
   */
  @Test
  public void testSetFeePaid() {
    student.setFeePaid(false);
    assertFalse("Fee paid should now be false", student.isFeePaid());
    student.setFeePaid(true);
    assertTrue("Fee paid should now be true", student.isFeePaid());
  }

  /**
   * Verifies that the Student class is a subclass of the User class.
   */
  @Test
  public void testIsUserSubclass() {
    assertTrue("Student should extend User", student instanceof User);
  }
}
