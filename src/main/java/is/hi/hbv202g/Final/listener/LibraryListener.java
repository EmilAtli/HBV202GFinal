package is.hi.hbv202g.Final.listener;

import is.hi.hbv202g.Final.Lending;

/**
 * A listener that keeps track of the availability of books in the library.
 */
public interface LibraryListener {
  /**
   * When a book is borrowed
   * 
   * @param lending The newly-created Lending that records the borrowing
   */
  void onBookBorrowed(Lending lending);

  /**
   * When a book is returned
   * 
   * @param lending The Lending record associated with the returned book
   */
  void onBookReturned(Lending lending);
}
