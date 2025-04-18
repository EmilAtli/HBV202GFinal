// src/main/java/is/hi/hbv202g/Final/Omnibus.java
package is.hi.hbv202g.Final;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A composite Book that bundles multiple volumes together.
 * Borrowing an Omnibus only borrows the omnibus container itself;
 * the LibrarySystem recursion will handle borrowing each volume.
 */
public class Omnibus extends Book {
  private final List<Book> volumes;

  public Omnibus(String title, List<Book> volumes) throws EmptyAuthorListException {
    super(
        title,
        // combine all authors from the volumes
        volumes.stream()
            .flatMap(v -> v.getAuthors().stream())
            .distinct()
            .collect(Collectors.toList()),
        1);
    if (volumes == null || volumes.isEmpty()) {
      throw new IllegalArgumentException("Omnibus must contain at least one volume.");
    }
    this.volumes = new ArrayList<>(volumes);
  }

  /** The individual volumes collected in this omnibus. */
  public List<Book> getVolumes() {
    return Collections.unmodifiableList(volumes);
  }

  @Override
  public void borrowCopy() {
    // only decrement omnibus itself
    super.borrowCopy();
  }

  @Override
  public void returnCopy() {
    // only return omnibus itself
    super.returnCopy();
  }
}
