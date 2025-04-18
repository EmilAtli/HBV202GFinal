package is.hi.hbv202g.Final;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class OmnibusTest {
  private Book v1, v2;
  private Omnibus omni;

  /**
   * Sets up the test fixture by creating two individual books, then
   * an omnibus containing both of them.
   * 
   * @throws EmptyAuthorListException should never happen due to the
   *                                  way the test fixture is set up.
   */
  @Before
  public void setUp() throws EmptyAuthorListException {
    v1 = new Book("Vol 1", "Author A", 1);
    v2 = new Book("Vol 2", "Author B", 2);

    omni = new Omnibus("Collected Volumes", List.of(v1, v2));
  }

  /**
   * Tests that the initial availability and total copies of the omnibus
   * and its volumes are correctly set after initialization.
   * Ensures that the omnibus has 1 available copy of 1 total,
   * and that the individual volumes have the expected available copies.
   */
  @Test
  public void testInitialAvailability() {
    assertEquals(1, omni.getAvailableCopies());
    assertEquals(1, omni.getTotalCopies());
    assertEquals(1, v1.getAvailableCopies());
    assertEquals(2, v2.getAvailableCopies());
  }

  /**
   * Tests that borrowing an omnibus only decrements the omnibus' available
   * copies.
   * Verifies that the omnibus' available copies are reduced while the
   * individual volumes' available copies remain unchanged.
   */
  @Test
  public void testBorrowOmnibusOnlyContainer() {
    omni.borrowCopy();
    assertEquals(0, omni.getAvailableCopies());
    assertEquals(1, v1.getAvailableCopies());
    assertEquals(2, v2.getAvailableCopies());
  }

  /**
   * Tests that returning an omnibus only affects its own availability.
   * Verifies that after borrowing and returning the omnibus,
   * its available copies are restored, while the individual
   * volumes' available copies remain unchanged.
   */
  @Test
  public void testReturnOmnibusOnlyContainer() {
    omni.borrowCopy();
    omni.returnCopy();

    assertEquals(1, omni.getAvailableCopies());
    assertEquals(1, v1.getAvailableCopies());
    assertEquals(2, v2.getAvailableCopies());
  }
}
