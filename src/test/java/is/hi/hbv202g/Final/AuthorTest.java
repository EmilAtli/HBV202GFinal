package is.hi.hbv202g.Final;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthorTest {
  private Author author;

  /**
   * Sets up the test fixture by creating an Author object with a valid
   * name, to be used in various unit tests.
   */
  @Before
  public void setUp() {
    author = new Author("Tolkien");
  }

  /**
   * Verifies that the getName() method returns the correct name
   * of an Author object.
   */
  @Test
  public void testGetName() {
    assertEquals("Tolkien", author.getName());
  }

  /**
   * Verifies that the setName() method sets the correct name
   * of an Author object.
   */
  @Test
  public void testSetName() {
    author.setName("Lewis");
    assertEquals("Lewis", author.getName());
  }

  /**
   * Verifies that the getName() method never returns null.
   */
  @Test
  public void testNameIsNotNull() {
    assertNotNull("Name should never be null", author.getName());
  }
}
