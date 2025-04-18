package is.hi.hbv202g.Final;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A composite Book that bundles multiple volumes together.
 * Borrowing an Omnibus borrows one copy of the omnibus itself
 * and then forwards the borrow to each contained volume.
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
    // borrow the omnibus container
    super.borrowCopy();
    // forward to each child volume
    for (Book vol : volumes) {
      vol.borrowCopy();
    }
  }

  @Override
  public void returnCopy() {
    super.returnCopy();
    for (Book vol : volumes) {
      vol.returnCopy();
    }
  }
}
