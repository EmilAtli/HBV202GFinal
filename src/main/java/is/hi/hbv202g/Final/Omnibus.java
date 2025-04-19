package is.hi.hbv202g.Final;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A composite {@link Book} that bundles multiple volumes together under one
 * title.
 * <p>
 * The omnibus itself counts as one copy but internally tracks individual
 * volumes.
 * Borrowing or returning an omnibus container will delegate handling of each
 * volume
 * via the {@link LibrarySystem} recursive logic.
 */
public class Omnibus extends Book {

  private final List<Book> volumes;

  /**
   * Constructs a new Omnibus containing the specified volumes.
   * <p>
   * The omnibus title inherits the combined authors of all volumes.
   * Exactly one copy of the omnibus is created.
   *
   * @param title   the omnibus title; must not be null or empty
   * @param volumes the list of volume books to include; must not be null or empty
   * @throws EmptyAuthorListException if any volume has no authors
   * @throws IllegalArgumentException if volumes is null or empty
   */
  public Omnibus(String title, List<Book> volumes) throws EmptyAuthorListException {
    super(
        title,
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

  /**
   * Returns an unmodifiable view of the individual volumes in this omnibus.
   *
   * @return read-only list of volume books
   */
  public List<Book> getVolumes() {
    return Collections.unmodifiableList(volumes);
  }

  /**
   * Borrows one copy of the omnibus container itself.
   * Individual volume borrowings are handled by {@link LibrarySystem}.
   */
  @Override
  public void borrowCopy() {
    super.borrowCopy();
  }

  /**
   * Returns one copy of the omnibus container itself.
   * Individual volume returns are handled by {@link LibrarySystem}.
   */
  @Override
  public void returnCopy() {
    super.returnCopy();
  }
}
