package is.hi.hbv202g.Final;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OmnibusTest {
  private Book v1, v2;
  private Omnibus omni;

  @Before
  public void setUp() throws EmptyAuthorListException {
    // two simple volumes, each with 1 copy
    v1 = new Book("Vol 1", "Author A", 1);
    v2 = new Book("Vol 2", "Author B", 2);

    // omnibus bundles them, defaulting to 1 copy
    omni = new Omnibus("Collected Volumes", List.of(v1, v2));
  }

  @Test
  public void testInitialAvailability() {
    // omnibus has 1/1, v1 has 1/1, v2 has 2/2
    assertEquals(1, omni.getAvailableCopies());
    assertEquals(1, omni.getTotalCopies());
    assertEquals(1, v1.getAvailableCopies());
    assertEquals(1, v1.getTotalCopies());
    assertEquals(2, v2.getAvailableCopies());
    assertEquals(2, v2.getTotalCopies());
  }

  @Test
  public void testBorrowOmnibusForwardsToVolumes() {
    // borrow the omnibus
    omni.borrowCopy();

    // omnibus now 0/1, v1 0/1, v2 1/2
    assertEquals(0, omni.getAvailableCopies());
    assertEquals(0, v1.getAvailableCopies());
    assertEquals(1, v2.getAvailableCopies());
  }

  @Test
  public void testReturnOmnibusRestoresVolumes() {
    // first borrow then return
    omni.borrowCopy();
    omni.returnCopy();

    // all back to original
    assertEquals(1, omni.getAvailableCopies());
    assertEquals(1, v1.getAvailableCopies());
    assertEquals(2, v2.getAvailableCopies());
  }
}
