package is.hi.hbv202g.Final.listener;

import is.hi.hbv202g.Final.Lending;
import is.hi.hbv202g.Final.Book;
import java.util.Locale;

public class AvailabilityListener implements LibraryListener {
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

  @Override
  public void onBookReturned(Lending lending) {
    Book b = lending.getBook();
    System.out.printf(Locale.US,
        "** %d/%d copies of \"%s\" available.%n",
        b.getAvailableCopies(), b.getTotalCopies(), b.getTitle());
  }
}
