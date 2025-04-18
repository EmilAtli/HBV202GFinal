package is.hi.hbv202g.Final;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FacultyMemberTest {
  private FacultyMember faculty;

  /**
   * Sets up a FacultyMember instance with initial values for testing.
   * Initializes the faculty member with the name "Dr. Jones" and
   * department "Physics".
   */
  @Before
  public void setUp() {
    faculty = new FacultyMember("Dr. Jones", "Physics");
  }

  /**
   * Verifies that the FacultyMember object returns the correct name
   * inherited from the User class via the getName() method.
   */
  @Test
  public void testGetName_inheritedFromUser() {
    assertEquals("Dr. Jones", faculty.getName());
  }

  /**
   * Verifies that the FacultyMember object's name can be set via the setName()
   * method and retrieved correctly via the getName() method,
   * inheriting behavior from the User class.
   */
  @Test
  public void testSetName_inheritedFromUser() {
    faculty.setName("Dr. Smith");
    assertEquals("Dr. Smith", faculty.getName());
  }

  /**
   * Verifies that the getDepartment() method returns the correct department
   * of the FacultyMember object.
   */
  @Test
  public void testGetDepartment() {
    assertEquals("Physics", faculty.getDepartment());
  }

  /**
   * Verifies that the FacultyMember object's department can be set via the
   * setDepartment() method and retrieved correctly via the getDepartment()
   * method.
   */
  @Test
  public void testSetDepartment() {
    faculty.setDepartment("Mathematics");
    assertEquals("Mathematics", faculty.getDepartment());
  }

  /**
   * Verifies that the FacultyMember class is a subclass of the User class.
   */
  @Test
  public void testIsUserSubclass() {
    assertTrue("FacultyMember should extend User", faculty instanceof User);
  }
}