package is.hi.hbv202g.Final.listener;

import is.hi.hbv202g.Final.Lending;
import is.hi.hbv202g.Final.Book;
import java.util.Locale;

/**
 * A listener that keeps track of the availability of books in the library.
 */
public class AvailabilityListener implements LibraryListener {
  /**
   * Called when a book is borrowed from the library.
   * 
   * @param lending The newly-created Lending that records the borrowing.
   */
  @Override
  public void onBookBorrowed(Lending lending) {
    Book b = lending.getBook();
    int left = b.getAvailableCopies();
    if (left == 0) {
      System.out.printf(Locale.US,
          "** \"%s\" is now out of stock!%n", b.getTitle());
    } else {
      System.out.printf(Locale.US,
          "** %d copies of \"%s\" remaining.%n",
          left, b.getTitle());
    }
  }

  /**
   * Called when a book is returned to the library.
   * 
   * @param lending The Lending record associated with the returned book.
   *                Displays the current number of available copies of the book in
   *                the library.
   */
  @Override
  public void onBookReturned(Lending lending) {
    Book b = lending.getBook();
    System.out.printf(Locale.US,
        "** %d/%d copies of \"%s\" available.%n",
        b.getAvailableCopies(), b.getTotalCopies(), b.getTitle());
  }
}
